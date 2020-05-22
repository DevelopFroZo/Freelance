package free.lance.domain.service;

import free.lance.domain.model.Category;
import free.lance.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return this.categoryRepository.findAll();
    }
}
