package com.vinhqd.app.controller.admin;

import com.vinhqd.app.dto.BrandDTO;
import com.vinhqd.app.service.IBrandService;
import com.vinhqd.app.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping
    public String categoryPage(Model model) {
        String data = JsonUtil.listToJson(brandService.findAll());
        model.addAttribute("brands", data);
        return "admin/brands/brand";
    }

    @PostMapping("edit")
    public String editPostAction(BrandDTO dto) {
        brandService.save(dto);
        return "redirect:/admin/brand";
    }

    @RequestMapping("/findById")
    @ResponseBody
    public BrandDTO findById(long id) {
        return brandService.findById(id);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteBrands(long[] ids) {
        brandService.delete(ids);
        return "redirect:/admin/brand";
    }


}
