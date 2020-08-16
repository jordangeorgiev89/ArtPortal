package com.example.project_def.enums;

public enum CategoryName {

    CATEGORY_BLACK("Black", "Some description"),
    CATEGORY_YELLOW("White", "Some description");



    private String name;
    private String description;

    CategoryName(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
