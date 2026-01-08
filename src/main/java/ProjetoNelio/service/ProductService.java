package ProjetoNelio.service;

import ProjetoNelio.exception.ResourceNotFoundException;
import ProjetoNelio.model.Category;
import ProjetoNelio.model.Product;
import ProjetoNelio.repository.CategoryRepository;
import ProjetoNelio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll(){

        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id){

        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void delete(Long id){

        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        for (Category category : product.getCategories()){

            category.getProducts().remove(product);
        }

        product.getCategories().clear();

        productRepository.delete(product);
    }
}
