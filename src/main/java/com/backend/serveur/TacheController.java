package com.backend.serveur;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TacheController {
    private ArrayList<Tache> allTaches = new ArrayList<Tache>() {{
    	 add(new Tache(1, "Une premier Tache"));
    	 add(new Tache(2, "Une deuxième Tache"));
    	
    	}};
	
	
	
	Tache findTaskById(int id){    
	    for (Tache tache : allTaches) {
	        if (tache.getId() == id) {
	            return tache;
	        }
	    }
	    return null; 
	}
	
	@GetMapping("/taches")
	public List retrieveAllTasks() {
		return this.allTaches;
	}
	
	
	@GetMapping("/taches/{id}")
	public Tache retrieveOneTask(@PathVariable int id) {
		Optional<Tache> task = Optional.ofNullable(findTaskById(id));

		if (!task.isPresent())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "entity not found"
					);

		return task.get();
	}
	
	@PostMapping("/taches")
	  Tache newEmployee(@RequestBody Tache newTask) {
		
		newTask.setId(allTaches.get(allTaches.size()-1).getId()+1);
	    this.allTaches.add(newTask);
	    return newTask;
	  }
	
	@PutMapping("/taches/{id}")
	  Tache replaceTask(@RequestBody Tache newTask, @PathVariable int id) {
		Optional<Tache> task = Optional.ofNullable(findTaskById(id));
		if (!task.isPresent())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "entity not found"
					);
	     task.get().setDescription(newTask.getDescription());
	     return task.get();
	  }  
	 @DeleteMapping("/taches/{id}")
	  void deleteEmployee(@PathVariable int id) {
		 Optional<Tache> task = Optional.ofNullable(findTaskById(id));
			if (!task.isPresent())
				throw new ResponseStatusException(
						  HttpStatus.NOT_FOUND, "entity not found"
						);
			this.allTaches.remove(task.get());
			}
}