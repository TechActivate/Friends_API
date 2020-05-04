package com.techactivate.friends.api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "samples_friends_reviews")
public class Review {
	@Id
	private String _id;
	private String episode;
	private String author;
	private String email;
	private boolean agree;
	private String comment;
	private int rating;
	private Date date;
	private String string;

	private enum ContactType {
		None, Tel, Email
	}
}
