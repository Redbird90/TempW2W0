package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.Pixmap;

public class Link {

	int id;
	String name;
	Pixmap pixmap;
	Link next;
	Link previous;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Link(int id, String name, Pixmap pixmap) {
		this.pixmap = pixmap;
		this.id = id;
		this.name = name;
	}
	
	public Link(int id) {
		this.id = id;
	}
	
	public Link(Pixmap pixmap, String name) {
		this.pixmap = pixmap;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Pixmap getPixmap() {
		return pixmap;
	}
	
	public void setPixmap(Pixmap pixmap) {
		this.pixmap = pixmap;
	}
	
	public Link getNext() {
		return next;
	}
	
	public Link getPrevious() {
		return previous;
	}

	public void setNext(Link next) {
		this.next = next;
	}

	public void setPrevious(Link previous) {
		this.previous = previous;
	}
	
	
}
