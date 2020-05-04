package com.techactivate.friends.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.techactivate.friends.api.entity.FriendsEpisode;

public interface FriendEpisodeRepo extends MongoRepository<FriendsEpisode, String>{
	
	FriendsEpisode findBy_id(ObjectId id);

}
