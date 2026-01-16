package E_Commerce_Spring.config;

import E_Commerce_Spring.model.*;
import E_Commerce_Spring.model.enums.UserRole;
import E_Commerce_Spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Category categoria1 = new Category("Eletronics");
        Category categoria2 = new Category("Books");
        Category categoria3 = new Category("Computers");
        Category categoria4 = new Category("Games");
        categoryRepository.saveAll(Arrays.asList(categoria1,categoria2,categoria3,categoria4));

        Product product1 = new Product("Macbook air", "Notebook by apple", 4000.37, "");
        Product product2 = new Product("Ryzen 7 5700x", "Process by AMD", 1300.0, "");
        Product product3 = new Product("Harry Potter the Philosopher's stone", "Book by J.K.Rowling", 55.0, "");
        Product product4 = new Product("Air Fryer", "steam cooking appliance", 400.37, "");
        Product product5 = new Product("Aoc Monitor", "Monitor by Aoc", 660.0, "");
        Product product6 = new Product("Sherlock Holmes", "Book by sir Arthut Conan Doyle", 30.73, "");
        Product product7 = new Product("Iphone 14", "Phone by apple", 3400.60, "");
        Product product8 = new Product("Dark Souls Remastered", "Game by FromSoftware", 60.20, "");

        product1.getCategories().add(categoria1);
        product1.getCategories().add(categoria2);
        product2.getCategories().add(categoria1);
        product3.getCategories().add(categoria2);
        product4.getCategories().add(categoria1);
        product5.getCategories().add(categoria1);
        product5.getCategories().add(categoria3);
        product6.getCategories().add(categoria2);
        product7.getCategories().add(categoria1);
        product8.getCategories().add(categoria4);

        productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5,product6,product7,product8));

        String senha = passwordEncoder.encode("1234");

        User user = new User("Pedro Nunes", "pedro@gmail.com","81991024299",senha);
        user.getRoles().addAll(List.of(UserRole.ROLE_USER, UserRole.ROLE_ADMIN));

        userRepository.save(user);
    }
}
