package com.spring.restfinder.rest_finder.dto.categoryDto;

public class CategoryDTO {
    private Long id;
    private String name;
    private String itName;

    public CategoryDTO(Long id, String name, String itName) {
        this.id = id;
        this.name = name;
        this.itName = itName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItName() {
        return this.itName;
    }

    public void setItName(String itName) {
        this.itName = itName;
    }

}
