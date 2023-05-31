package com.djliquor.app.providers;

import com.djliquor.app.models.Category;
import com.djliquor.app.models.Type;

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
        Type[] values = Type.values();
        List<Category> categoryList = new ArrayList<Category>();
        for (int i = 0; i < categories.size(); i++)
        {
            String name = categories.get(i).toUpperCase(Locale.ROOT);
            String fileName = "icon_" + categories.get(i);
            categoryList.add(new Category(name, fileName, values[i+1]));
        }
        return categoryList;
    }
}
