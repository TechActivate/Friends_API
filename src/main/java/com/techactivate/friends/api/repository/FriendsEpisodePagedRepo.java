package com.techactivate.friends.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.techactivate.friends.api.entity.FriendsEpisode;

public interface FriendsEpisodePagedRepo extends PagingAndSortingRepository<FriendsEpisode, String> {

	FriendsEpisode findBy_id(ObjectId id);

	Page<FriendsEpisode> findAllBySeason(double season, Pageable pageable);

	@Query(value = "{ 'id' : ?0 }")
//This would fetch all the fields, if you want to see, say, only _id, id and name, use following:	
//	@Query(value="{ 'id' : ?0 }",fields="{ 'episodeId' : 1, 'name':1}")
	FriendsEpisode findByEpisodeId(Float episodeId);
}
