package com.techactivate.friends.api.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.techactivate.friends.api.entity.Review;
import com.techactivate.friends.api.repository.ReviewRepo;

@Service
public class ReviewService {
	@Autowired
	ReviewRepo  repoReivew;

	public Page<Review>  getPage(int offset, int pageSize) {
		return repoReivew.findAll(PageRequest.of(offset, pageSize));
	}


	public Iterable<Review> getAll() {
		return repoReivew.findAll();
	}

	public Review getReviewById(ObjectId id) {
		return repoReivew.findBy_id(id);
	}

	public void modifyReviewById(ObjectId id, Review review) {
		repoReivew.save(review);
	}

	public Review createReview(Review review) {
		repoReivew.save(review);
		return review;
	}

	public void deleteReview(ObjectId id) {
		repoReivew.delete(repoReivew.findBy_id(id));
	}
}
