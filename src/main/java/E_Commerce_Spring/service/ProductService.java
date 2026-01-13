package E_Commerce_Spring.service;

import E_Commerce_Spring.exception.ResourceNotFoundException;
import E_Commerce_Spring.model.Category;
import E_Commerce_Spring.model.Product;
import E_Commerce_Spring.repository.CategoryRepository;
import E_Commerce_Spring.repository.ProductRepository;
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
