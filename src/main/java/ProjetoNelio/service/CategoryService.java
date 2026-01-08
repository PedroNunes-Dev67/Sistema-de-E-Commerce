package ProjetoNelio.service;

import ProjetoNelio.exception.ResourceNotFoundException;
import ProjetoNelio.model.Category;
import ProjetoNelio.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> findAll(){

        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category findById(Long id){

        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
