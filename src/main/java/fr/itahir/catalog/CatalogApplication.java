package fr.itahir.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;

import fr.itahir.catalog.dao.ProduitRepository;
import fr.itahir.catalog.entities.Produit;

@SpringBootApplication
public class CatalogApplication implements CommandLineRunner{
	
	@Autowired
	private ProduitRepository prouduitRepository;

	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//ProduitRepository prouduitRepository = ctx.getBean(ProduitRepository.class);
		prouduitRepository.save(new Produit("Asus", 450.00, 12));
		prouduitRepository.save(new Produit("Dell", 650.00, 8));
		prouduitRepository.save(new Produit("Mac", 990.00, 3));
		
		List<Produit> prods = prouduitRepository.findAll();
		
		prods.forEach(p->System.out.println(p.getDesignation()));
	}
}
