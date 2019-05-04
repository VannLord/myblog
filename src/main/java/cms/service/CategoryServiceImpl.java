package cms.service;

import cms.model.Category;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private cms.repository.CategoryRepository CategoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return CategoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return CategoryRepository.findOne(id);
    }

    @Override
    public void save(Category Category) {
        CategoryRepository.save(Category);
    }

    @Override
    public void remove(Long id) {
        CategoryRepository.delete(id);
    }
}
