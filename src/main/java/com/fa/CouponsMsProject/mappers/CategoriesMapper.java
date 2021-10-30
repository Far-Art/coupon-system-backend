package com.fa.CouponsMsProject.mappers;

import com.fa.CouponsMsProject.beans.Category;
import com.fa.CouponsMsProject.exceptions.CustomException;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class CategoriesMapper {

    public Category stringToCategory(String category) throws CustomException {
        try {
            return Category.valueOf(category.replaceAll(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e){
            throw new CustomException("Such category not found");
        }
    }

    public String categoryToString(Category category){
        return category.toString().charAt(0) + category.toString().substring(1).toLowerCase().replaceAll("_", " ");
    }

    public List<String> categoryToString(List<Category> categories){
        return categories.stream().map(this::categoryToString).collect(Collectors.toList());
    }
}