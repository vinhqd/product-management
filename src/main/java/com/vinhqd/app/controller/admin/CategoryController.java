package com.vinhqd.app.controller.admin;

import com.vinhqd.app.dto.CategoryDTO;
import com.vinhqd.app.service.ICategoryService;
import com.vinhqd.app.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String categoryPage(Model model) {
        String data = JsonUtil.listToJson(categoryService.findAll());
        model.addAttribute("categories", data);
        return "admin/categories/category";
    }

    @PostMapping("addNew")
    public String addNew(CategoryDTO dto) {
        categoryService.save(dto);
        return "redirect:/admin/category";
    }

    @RequestMapping("findById")
    @ResponseBody
    public CategoryDTO findById(long id) {
        return categoryService.findById(id);
    }

    @RequestMapping(value = "edit", method = {RequestMethod.PUT, RequestMethod.GET})
    public String edit(CategoryDTO dto) {
        categoryService.save(dto);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(long[] ids) {
        categoryService.delete(ids);
        return "redirect:/admin/category";
    }
}
