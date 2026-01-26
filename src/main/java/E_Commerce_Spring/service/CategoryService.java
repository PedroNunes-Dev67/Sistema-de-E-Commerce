package E_Commerce_Spring.service;

import E_Commerce_Spring.dto.request.CategoryDtoRequest;
import E_Commerce_Spring.dto.response.CategoryDtoResponse;
import E_Commerce_Spring.exception.ConflictUserResource;
import E_Commerce_Spring.exception.ResourceNotFoundException;
import E_Commerce_Spring.model.Category;
import E_Commerce_Spring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDtoResponse> findAll(){

        return categoryRepository.findAll()
                .stream()
                .map(category -> {
                    return new CategoryDtoResponse(category.getId(), category.getName());
                }).toList();
    }

    @Transactional(readOnly = true)
    public Category findById(Long id){

        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public CategoryDtoResponse createCategory(CategoryDtoRequest categoryDtoRequest){

        if (categoryRepository.findByName(categoryDtoRequest.getName()).isPresent()) throw new ConflictUserResource("Categoria já cadastrada");

        Category category = new Category(categoryDtoRequest.getName());

        categoryRepository.save(category);

        return new CategoryDtoResponse(category.getId(), category.getName());
    }
}
