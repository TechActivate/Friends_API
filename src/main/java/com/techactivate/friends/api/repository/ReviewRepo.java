package com.techactivate.friends.api.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.techactivate.friends.api.entity.Review;

public interface ReviewRepo extends PagingAndSortingRepository<Review, String> {
	Review findBy_id(ObjectId id);
}
