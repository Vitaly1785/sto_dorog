package ru.petukhov.sto_dorog.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.petukhov.sto_dorog.entities.Comment;
import ru.petukhov.sto_dorog.entities.Person;
import ru.petukhov.sto_dorog.services.CommentService;

@Controller
@RequestMapping("comment")
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment, @AuthenticationPrincipal Person person){
        return commentService.createComment(comment, person);
    }
}
