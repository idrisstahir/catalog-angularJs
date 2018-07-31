package fr.itahir.catalog.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.itahir.catalog.entities.Produit;

@RestController
public class ProduitRestService {
	
	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value="/produits", method=RequestMethod.GET)
	public Page<Produit> listProduit(int page, int size){
		return produitRepository.findAll(new PageRequest(page, size));
	}
	
	@RequestMapping(value="/chercherProduits", method=RequestMethod.GET)
	public Page<Produit> chercher(
			String mc, 
			@RequestParam(name="page", defaultValue="0") int page, 
			@RequestParam(name="size", defaultValue="5") int size){
		return produitRepository.chercherProduits("%"+mc+"%", new PageRequest(page, size));
	}
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.GET)
	public Optional<Produit> getProduit(@PathVariable ("id")Long id){
		return produitRepository.findById(id);
	}
	
	@RequestMapping(value="/produits", method=RequestMethod.POST)
	public Produit update(@RequestBody Produit p){
		return produitRepository.save(p);
	}
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.PUT)
	public Produit update(@RequestBody Produit p, @PathVariable Long id){
		p.setId(id);
		return produitRepository.saveAndFlush(p);
	}
	
	@RequestMapping(value="/produits/{id}", method=RequestMethod.DELETE)
	public void detele(@PathVariable ("id")Long id){
		produitRepository.deleteById(id);
	}

}
