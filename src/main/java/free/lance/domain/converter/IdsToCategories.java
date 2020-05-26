package free.lance.domain.converter;

import free.lance.domain.model.Category;
import free.lance.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.Set;

public class IdsToCategories implements Converter<Set<Long>, Set<Category>>{
    @Autowired
    private CategoryRepository categoryRepository;

    public Set<Category> convert( Set<Long> ids ){
        return this.categoryRepository.findAllByIdIn( ids );
    }
}
