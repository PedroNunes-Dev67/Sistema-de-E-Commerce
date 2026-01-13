package E_Commerce_Spring.config;

import E_Commerce_Spring.model.*;
import E_Commerce_Spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        Category categoria1 = new Category(null,"Eletronics");
        Category categoria2 = new Category(null,"Books");
        Category categoria3 = new Category(null,"Computers");

        categoryRepository.saveAll(Arrays.asList(categoria1,categoria2,categoria3));
    }
}
