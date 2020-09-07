package com.backend.serveur;

public class Tache {
	private  int id;
	private  String description;

	public Tache(int id, String content) {
		this.id = id;
		this.description = content;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String content) {
		this.description = content;
	}

	public void setId(int id) {
		this.id = id;
	}


	
}
