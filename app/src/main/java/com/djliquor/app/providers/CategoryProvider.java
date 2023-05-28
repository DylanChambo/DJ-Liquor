package com.djliquor.app.providers;

import com.djliquor.app.models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CategoryProvider {
    private static List<String> categories = new ArrayList<String>() {
        {
            add("beer");
            add("wine");
            add("cider");
            add("spirits");
            add("rtds");
            add("liqueur");
        }
    };

    public static List<Category> getCategories() {
        List<Category> categoryList = new ArrayList<Category>();
        for (String category: categories)
        {
            String name = category.toUpperCase(Locale.ROOT);
            String fileName = "icon_" + category;
            categoryList.add(new Category(name, fileName));
        }
        return categoryList;
    }
}
