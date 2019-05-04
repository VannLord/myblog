package cms.service;

import cms.model.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category findById(Long id);

    void save(Category Category);

    void remove(Long id);
}
