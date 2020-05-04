package com.techactivate.friends.api.controller;

import static com.techactivate.friends.api.common.Constants.PAGE_SIZE;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techactivate.friends.api.entity.Review;
import com.techactivate.friends.api.service.ReviewService;

@RestController
@ResponseBody
@RequestMapping("/reviews")
public class Reviews {

	@Autowired
	ReviewService serviceReview;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Iterable<Review>> getAllReviews(HttpServletResponse response) {
		return ResponseEntity.ok(serviceReview.getAll());
	}

	@GetMapping(value = "/", params = { "end", "start" })
	public ResponseEntity<Page<Review>> getReviewsWithinRange(
					@RequestParam(value = "start", required = false, defaultValue = "1") Integer start,
					@RequestParam(value = "end", required = false, defaultValue = "1") Integer end,
					HttpServletResponse response) {
		if (start < 1)
			start = 1;
		if (end < start || end == null)
			end = start + PAGE_SIZE - 1;

		Page<Review> page = serviceReview.getPage(start - 1, end - start + 1);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = { "/random" })
	public List<Review> getRandomReviews(HttpServletResponse response) {
		
		Random r = new Random();
		int randomNumber= r.nextInt(PAGE_SIZE);
		return serviceReview.getPage(randomNumber, PAGE_SIZE).getContent();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable("id") ObjectId id) {
		return ResponseEntity.ok(serviceReview.getReviewById(id));
	}

	@PutMapping(value = "/{id}")
	public void modifyreviewById(@PathVariable("id") ObjectId id, @Valid @RequestBody Review reviews) {
		serviceReview.modifyReviewById(id, reviews);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Review> createreview(@Valid @RequestBody Review reviews) {
		serviceReview.createReview(reviews);
		return ResponseEntity.ok(reviews);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteReview(@PathVariable ObjectId id) {
		serviceReview.deleteReview(id);
	}
}
