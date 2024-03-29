package com.example.gasip.category.controller;

import com.example.gasip.category.model.Category;
import com.example.gasip.category.service.CategoryService;
import com.example.gasip.global.api.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("all-colleges")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/college")
    public ResponseEntity<?> findCollege() {
        return ResponseEntity
                .ok()
                .body(
                        ApiUtils.success(
                                categoryService.findCollege()
                        )
                );
    }

    @GetMapping("/categories")
    public ResponseEntity<?> findCategory() {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    categoryService.findCategory()
                )
            );
    }

    @GetMapping("/categories/{parentCategory_id}")
    public ResponseEntity<?> findCategoryByParentCategory(@PathVariable Category parentCategory_id) {
        return ResponseEntity
            .ok()
            .body(
                ApiUtils.success(
                    categoryService.findCategoryByParentCategory(parentCategory_id)
                )
            );
    }

//    @GetMapping("/categories/{parentCategory_id}/{major_id}")
//    public ResponseEntity<?> findCategoryByMajorId(@PathVariable Long major_id) {
//        return ResponseEntity
//                .ok()
//                .body(
//                        ApiUtils.success(
//                                categoryService.findCategoryByMajorId(major_id)
//                        )
//                );
//    }
}
