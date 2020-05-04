package com.techactivate.friends.api.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techactivate.friends.api.entity.FriendsEpisode;
import com.techactivate.friends.api.repository.FriendsEpisodePagedRepo;

@Service
public class FriendsService {
	@Autowired
	FriendsEpisodePagedRepo  repoFriends;


	public Page<FriendsEpisode>  getPage(int offset, int pageSize) {
		return repoFriends.findAll(PageRequest.of(offset, pageSize));
	}


	public Iterable<FriendsEpisode> getAll() {
		return repoFriends.findAll();
	}


	public Page<FriendsEpisode> getAll(int offset, int pageSize) {
		return getPage(offset, pageSize);
	}

	public Page<FriendsEpisode>  getSeason(int season, int offset, int pageSize) {
		return repoFriends.findAllBySeason(Float.valueOf(season), PageRequest.of(offset, pageSize));

	}

	public FriendsEpisode getEpisodeById(ObjectId id) {
		FriendsEpisode something = repoFriends.findBy_id(id);
		return something;
	}

	public void modifyEpisodeById(ObjectId id, FriendsEpisode episode) {
		repoFriends.save(episode);
	}

	public FriendsEpisode createEpisode(FriendsEpisode episode) {
		repoFriends.save(episode);
		return episode;
	}

	public void deleteEpisode(ObjectId id) {
		repoFriends.delete(repoFriends.findBy_id(id));
	}

	public FriendsEpisode getEpisodeByOtherId(String id) {
		return repoFriends.findByEpisodeId(Float.valueOf(id));
	}
	 
}
