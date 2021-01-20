package com.vinhqd.app.controller.admin;

import com.vinhqd.app.dto.ProductDTO;
import com.vinhqd.app.service.IBrandService;
import com.vinhqd.app.service.ICategoryService;
import com.vinhqd.app.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private IBrandService brandService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @GetMapping
    public String productPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/products/product";
    }

    @GetMapping ("edit")
    public String editPage(Model model, @ModelAttribute("product") ProductDTO productDTO, @RequestParam(required = false) Long id) {
        if (id != null) {
            productDTO = productService.findById(id);
        }
        model.addAttribute("product", productDTO);
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/products/edit";
    }

    @PostMapping("edit")
    public String postEdit(Model model, @ModelAttribute(name = "product") ProductDTO productDTO) {
        productService.save(productDTO);
        return "redirect:/admin/product";
    }


}
