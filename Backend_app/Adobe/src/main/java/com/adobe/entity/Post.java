package com.adobe.entity;

import java.sql.Timestamp;

//import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    
	    private String content;
	    private Timestamp created_at;
	    private Timestamp updated_at;
	    private Integer likes;
	    private Integer unlikes;
	    
	    @ManyToOne
	    private User user;

	    public void setCreatedAt() {
	        this.created_at = new Timestamp(System.currentTimeMillis());
	    }

	    public void setUpdatedAt() {
	        this.updated_at = new Timestamp(System.currentTimeMillis());
	    }
	    

}
