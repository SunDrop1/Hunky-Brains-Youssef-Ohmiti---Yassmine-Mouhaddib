package com.exam.examportal.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examportal.model.exam.Question;
import com.exam.examportal.model.exam.Quiz;
import com.exam.examportal.service.QuestionService;
import com.exam.examportal.service.QuizService;



@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add Question
	@PostMapping("/")
	public ResponseEntity<?> addQuestion(@RequestBody Question question){
		Question question1 = this.questionService.addQuestion(question);
		return ResponseEntity.ok(question1);
	}
	
	
	//get all Questions
	@GetMapping("/")
	public ResponseEntity<?> getQuestions(){
		return ResponseEntity.ok(this.questionService.getQuestion());
		
	}
	
	//get all question of any quiz
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid){
//		Quiz quiz = new Quiz();
//		quiz.setqId(qid);
//		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
//		return ResponseEntity.ok(questionOfQuiz);
		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList<Question>(questions);
		if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
			list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
		
	}
	//get all question of any quiz
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizaAdmin(@PathVariable("qid") Long qid){
		Quiz quiz = new Quiz();
		quiz.setqId(qid);
		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);
		
		
	}
	
	
	//get Question
	@GetMapping("/{QuestionId}")
	public Question getQuestion(@PathVariable("QuestionId") Long questionId) {
		return this.questionService.getQuestion(questionId);
	}
	
	//update Question
	@PutMapping("/")
	public Question updateQuestion(@RequestBody Question question) {
		return this.questionService.updateQuestion(question);
	}
	
	//delete Question
	@DeleteMapping("/{QuestionId}")
	public void deleteQuestion(@PathVariable("QuestionId") Long questionId) {
		this.questionService.deleteQuestion(questionId);
	}
	
	
	//eval quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		System.out.println(questions);
		double marksGot=0;
		int  correctAnswer=0;
		int  attempted=0;
		for(Question q: questions) {
			Question question = this.questionService.get(q.getQuesId());
			if(question.getAnswer().trim().equals(q.getGiveAsnwer())) {
				//correct
				correctAnswer++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				
				marksGot += marksSingle;
			
			}
			if(q.getGiveAsnwer() != null) {
				attempted++;
			}
			
			
		}	
		
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
}
