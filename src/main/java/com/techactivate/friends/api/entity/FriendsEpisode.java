package com.techactivate.friends.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection="samples_friends")
public class FriendsEpisode {

	@Id
	public String _id;

	@Field("id")
	private Float episodeId;
	
	private String url;
	private String name;
	private Float season;
	private float number;
	private String airdate;
	private String airtime;
	private String airstamp;
	private Float runtime;
	Image image;
	private String summary;
	Links object;

}
