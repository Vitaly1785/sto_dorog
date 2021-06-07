package ru.petukhov.sto_dorog.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.petukhov.sto_dorog.dto.CommentDto;
import ru.petukhov.sto_dorog.entities.Comment;
import ru.petukhov.sto_dorog.entities.NewsItem;
import ru.petukhov.sto_dorog.services.CommentService;
import ru.petukhov.sto_dorog.services.NewsItemService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/news/{id}/comments")
public class CommentsController {

    private String sortMethod = "ASC";
    private final CommentService commentService;
    private final NewsItemService newsItemService;

    public CommentsController(CommentService commentService, NewsItemService newsItemService) {
        this.commentService = commentService;
        this.newsItemService = newsItemService;
    }

    @GetMapping
    public String showComments(@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
        NewsItem newsItem = newsItemService.findById(id);
        Page<Comment> comments = sortComment(id, pageable);
        model.addAttribute("newsItem", newsItem);
        model.addAttribute("page", comments);
        return "/comments";
    }

    @GetMapping("/add")
    public String addComment(Model model, @PathVariable Long id){
        model.addAttribute("newsItem", newsItemService.findById(id));
        model.addAttribute("addComment", new Comment());
        return "/addComment";
    }

    @PostMapping
    public String createComment(@ModelAttribute("comment")@Valid CommentDto commentDto, BindingResult result,
                                Principal principal, @PathVariable Long id){
        if(result.hasErrors()){
            return "/addComment";
        }
        commentService.createComment(commentDto,principal, id);
        return "redirect:/news/{id}/comments";
    }

    @PostMapping("/{sorted}")
    public String getSortMethod(@PathVariable Long id,@PathVariable("sorted") String sorted) {
        sortMethod = sorted;
        return "redirect:/news/{id}/comments";
    }
    public Page<Comment> sortComment(Long postId, Pageable pageable) {
        Page<Comment> comments;

        switch (sortMethod) {
            case "ASC":
                comments = commentService.findOldComments(newsItemService.findById(postId), pageable);
                break;
            case "DESC":
                comments = commentService.findRecentComment(newsItemService.findById(postId), pageable);
                break;
            default:
                comments = commentService.findAllComments(newsItemService.findById(postId), pageable);
                break;
        }
        return comments;
    }
}
