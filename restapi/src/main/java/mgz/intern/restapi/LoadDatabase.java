package mgz.intern.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import mgz.intern.restapi.model.Product;
import mgz.intern.restapi.repository.ProductRepository;

public class LoadDatabase {
	 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	  @Bean
	  CommandLineRunner initDatabase(ProductRepository repository) {

	    return args -> {
	      log.info("Preloading " + repository.save(new Product("Ao coc tay", "day la ao mau", 3200000)));
	      log.info("Preloading " + repository.save(new Product("Ao dai tay", "day la ao demo", 4200000)));
	    };
	  }
}
