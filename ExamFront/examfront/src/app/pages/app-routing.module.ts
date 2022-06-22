import { RouterModule, Routes } from '@angular/router';
import { Component, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';
import { AdminGuard } from '../services/admin.guard';
import { NormalGuard } from '../services/normal.guard';
import { ProfileComponent } from './profile/profile.component';
import { WelcomeComponent } from './admin/welcome/welcome.component';
import { ViewCategoriesComponent } from './admin/view-categories/view-categories.component';
import { AddCategoryComponent } from './admin/add-category/add-category.component';
import { ViewQuizzesComponent } from './admin/view-quizzes/view-quizzes.component';
import { AddQuizComponent } from './admin/add-quiz/add-quiz.component';
import { UpdateQuizComponent } from './admin/update-quiz/update-quiz.component';
import { ViewQuizQuestionsComponent } from './admin/view-quiz-questions/view-quiz-questions.component';
import { AddQuestionComponent } from './admin/add-question/add-question.component';
import { LoadQuizComponent } from './user/load-quiz/load-quiz.component';
import { InstructionsComponent } from './user/instructions/instructions.component';
import { StartComponent } from './user/start/start.component';


const routes: Routes = [
 {
    path: 'home',
    component: HomeComponent,
    pathMatch: "full"
 },
 {
  path: 'signup',
  component: SignupComponent,
  pathMatch: "full",
 },
 {
     path: 'login',
     component: LoginComponent,
     pathMatch: "full"
 },
 {
    path: 'admin',
    component: DashboardComponent,
    canActivate:[AdminGuard],
    children: [
      {
         path:'',
         component: WelcomeComponent,
      },
       {
          path:'profile',
          component: ProfileComponent,
       },
       {
         path:'categories',
         component: ViewCategoriesComponent,
       },
       {
          path:'add-category',
          component: AddCategoryComponent
       },
       {
         path:'quizzes',
         component: ViewQuizzesComponent,
       },
       {
         path:'add-quiz',
         component: AddQuizComponent,
       },
       {
         path:'quiz/:qid',
         component: UpdateQuizComponent
       },
       {
          path:'view-questions/:qid/:title',
          component: ViewQuizQuestionsComponent
       },
       {
          path: 'add-question/:qid/:title',
          component: AddQuestionComponent
       }
    ],
 }, 
 {
    path: 'user-dashboard',
    component: UserDashboardComponent,
    canActivate: [NormalGuard],
    children:[
      {
         path:':catId',
         component: LoadQuizComponent,
      },{
         path:'instructions/:qid',
         component: InstructionsComponent,
      }
    ],
 },
 {
   path:'start/:qid',
   component: StartComponent,
   canActivate: [NormalGuard],
}
];



@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
  
})
export class AppRoutingModule { }
