package cms.controller;

import cms.model.Blog;
import cms.model.Category;
import cms.service.BlogService;
import cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/*
Em chưa sort được ngày tháng
 */
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }
    @GetMapping("/create-blog")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/Blog/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog Blog){
        blogService.save(Blog);

        ModelAndView modelAndView = new ModelAndView("/Blog/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message", "New Blog created successfully");
        return modelAndView;
    }
    @GetMapping("/blog")
    public ModelAndView listBlogs(Pageable pageable, @RequestParam("s")Optional<String> s){
        Page<Blog> blogs;
        if(s.isPresent()){
            blogs = blogService.findAllByAuthorContaining(s.get(),pageable);
        }else {
            blogs = blogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/Blog/list");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Blog Blog = blogService.findById(id);
        if(Blog != null) {
            ModelAndView modelAndView = new ModelAndView("/Blog/edit");
            modelAndView.addObject("blog", Blog);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog Blog){
        blogService.save(Blog);
        ModelAndView modelAndView = new ModelAndView("/Blog/edit");
        modelAndView.addObject("blog", Blog);
        modelAndView.addObject("message", "Blog updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Blog Blog = blogService.findById(id);
        if(Blog != null) {
            ModelAndView modelAndView = new ModelAndView("/Blog/delete");
            modelAndView.addObject("blog", Blog);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog Blog){
        blogService.remove(Blog.getId());
        return "redirect:/blog";
    
    }@GetMapping("/view-blog/{id}")
    public String view (@PathVariable Long id, Model model){
        model.addAttribute("Blog",blogService.findById(id));
        return "/Blog/view";
    }
    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Blog> blogs = blogService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }
}
