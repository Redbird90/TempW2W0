package com.example.jkt.wall2wall0;

import com.example.jkt.wall2wall0.Pixmap;

public class Carousel {
	
	Link first;
	Link last;
	int i;
	
	public Carousel() {
		first = null;
		last = null;	
		i=0;
	}
	
	public Link getFirst() {
		return first;
	}

	public void setFirst(Link first) {
		this.first = first;
	}

	public Link getLast() {
		return last;
	}

	public void setLast(Link last) {
		this.last = last;
	}

	public boolean isEmpty() {
	    return first == null;
	}
	
	public void insert(int id, String name, Pixmap pixmap) {
	    Link newLink = new Link(id, name, pixmap);
	    if (isEmpty()){
	      first = newLink;
	    }else {
	      last.next = newLink;
	      newLink.previous = last;
	    }
	    last = newLink;
	    i++;
	  }
	
	public int size(){
		return this.i;
	}
	
	public void clear(){
		Link current = first;
		for(int i=0; i<this.i;i++){
			current.getPixmap().dispose();
			current = current.next;
		}
		first.next = null;
		first.previous = null;
		first = null;
		last.next = null;
		last.previous=null;
		last = null;
		this.i=0;
	}
	
	
}

