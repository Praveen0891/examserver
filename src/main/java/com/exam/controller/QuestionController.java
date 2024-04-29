package com.exam.controller;

import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question

    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question)
    {
        System.out.println("Please Add Question");
        System.out.println(question);
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    //update question
    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get quiz wise questions
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getAllQuestionsOfQuiz(@PathVariable("qid") Long qid)
    {
        Quiz quiz=this.quizService.getQuiz(qid);
        Set<Question> questions=quiz.getQuestions();
        int totalQuestions=Integer.parseInt(quiz.getNumberOfQuestions());
        List list=new ArrayList(questions);
        if(list.size()>totalQuestions)
        {
            list=list.subList(0,totalQuestions+1);
        }
        Collections.shuffle(list);

        return ResponseEntity.ok(list);
    }


    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getAllQuestionsOfQuizAdmin(@PathVariable("qid") Long qid)
    {
        Quiz quiz=new Quiz();
        quiz.setqId(qid);
        Set<Question>list=this.questionService.getQuestionOfQuiz(quiz);

        return ResponseEntity.ok(list);
    }


    //get a single question
    @GetMapping("/{qid}")
    public Question getQuestion(@PathVariable("qid") Long qid)
    {
        return this.questionService.getQuestion(qid);
    }
    //delete a single question
    @DeleteMapping("{qid}")
    public void deleteQuestion(@PathVariable("qid") Long qid)
    {
        this.questionService.deleteQuestion(qid);
    }
}







//=============================================================================================
//package com.exam.controller;
//
//import com.exam.model.exam.Question;
//import com.exam.model.exam.Quiz;
//import com.exam.service.QuestionService;
//import com.exam.service.QuizService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.management.Query;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@CrossOrigin(
//        origins = {
//                "http://localhost:4200"
//        },
//        methods = {
//                RequestMethod.OPTIONS,
//                RequestMethod.GET,
//                RequestMethod.PUT,
//                RequestMethod.DELETE,
//                RequestMethod.POST
//        })
//@RequestMapping("/question")
//public class QuestionController {
//    @Autowired
//    private QuestionService service;
//
//    @Autowired
//    private QuizService quizService;
//
//    //add question
//    @PostMapping("/")
//    public ResponseEntity<Question> add(@RequestBody Question question) {
//        return ResponseEntity.ok(this.service.addQuestion(question));
//    }
//
//    //update the question
//    @PutMapping("/")
//    public ResponseEntity<Question> update(@RequestBody Question question) {
//        return ResponseEntity.ok(this.service.updateQuestion(question));
//    }
//
//    //get all question of any quid
//    @GetMapping("/quiz/{qid}")
//    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
////        Quiz quiz = new Quiz();
////        quiz.setqId(qid);
////        Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuiz(quiz);
////        return ResponseEntity.ok(questionsOfQuiz);
//
//        Quiz quiz = this.quizService.getQuiz(qid);
//        Set<Question> questions = quiz.getQuestions();
//        List list = new ArrayList(questions);
//        if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
//            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
//        }
//        Collections.shuffle(list);
//        return ResponseEntity.ok(list);
//
//
//    }
//
//
//    @GetMapping("/quiz/all/{qid}")
//    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
//        Quiz quiz = new Quiz();
//        quiz.setqId(qid);
//        Set<Question> questionsOfQuiz = this.service.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
//
////        return ResponseEntity.ok(list);
//
//
//    }
//
//
//    //get single question
//    @GetMapping("/{quesId}")
//    public Question get(@PathVariable("quesId") Long quesId) {
//        return this.service.getQuestion(quesId);
//    }
//
//    //delete question
//    @DeleteMapping("/{quesId}")
//    public void delete(@PathVariable("quesId") Long quesId) {
//        this.service.deleteQuestion(quesId);
//    }
//
//}
