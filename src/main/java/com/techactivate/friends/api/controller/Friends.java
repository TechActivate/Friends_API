package com.techactivate.friends.api.controller;

import java.util.ArrayList;
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

import com.techactivate.friends.api.entity.FriendsEpisode;
import com.techactivate.friends.api.service.FriendsService;

import static com.techactivate.friends.api.common.Constants.PAGE_SIZE;

@RestController
@ResponseBody
@RequestMapping("/friends")
public class Friends {

	@Autowired
	FriendsService serviceFriends;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<Page<FriendsEpisode>> getEpisodes(HttpServletResponse response) {
		return this.getEpisodesWithinRange(1, PAGE_SIZE, response);
	}

	
	@GetMapping(value = "/", params = { "end", "start" })
	public ResponseEntity<Page<FriendsEpisode>> getEpisodesWithinRange(
					@RequestParam(value = "start", required = false, defaultValue = "1") Integer start,
					@RequestParam(value = "end", required = false, defaultValue = "1") Integer end,
					HttpServletResponse response) {
		if (start < 1)
			start = 1;
		if (end < start || end == null)
			end = start + PAGE_SIZE - 1;

		Page<FriendsEpisode> page = serviceFriends.getPage(start - 1, end - start + 1);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = { "/random" })
	public List<FriendsEpisode> getRandomEpisodes(HttpServletResponse response) {
		
		Random r = new Random();
		int randomNumber= r.nextInt(PAGE_SIZE);
		return serviceFriends.getPage(randomNumber, PAGE_SIZE).getContent();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<FriendsEpisode> getEpisodeById(@PathVariable("id") ObjectId id) {
		return ResponseEntity.ok(serviceFriends.getEpisodeById(id));
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<FriendsEpisode> getEpisodeByOtherId(@PathVariable("id") String id) {
		return ResponseEntity.ok(serviceFriends.getEpisodeByOtherId(id));
	}

	@GetMapping(value = "/allids")
	public ResponseEntity<List<String>> getAllIds() {
		Iterable<FriendsEpisode> list = serviceFriends.getAll();
		List<String> s = new ArrayList();
		for(FriendsEpisode f: list)
			s.add(f.get_id());
		return ResponseEntity.ok(s);
	}

	@PutMapping(value = "/{id}")
	public void modifyfriendById(@PathVariable("id") ObjectId id, @Valid @RequestBody FriendsEpisode friends) {
		serviceFriends.modifyEpisodeById(id, friends);
	}

	@PostMapping(value = "/")
	public ResponseEntity<FriendsEpisode> createfriend(@Valid @RequestBody FriendsEpisode friends) {
		serviceFriends.createEpisode(friends);
		return ResponseEntity.ok(friends);
	}

	@DeleteMapping(value = "/{id}")
	public void deletefriend(@PathVariable ObjectId id) {
		serviceFriends.deleteEpisode(id);
	}

	@GetMapping(value = "/season/{season}")
	public ResponseEntity<Page<FriendsEpisode>> getFirstPageOfEpisodeBySeason(@PathVariable("season") Integer season,
					HttpServletResponse response) {
		return this.getEpisodeBySeason(season, 1, PAGE_SIZE, response);
	}

	@GetMapping(value = "/season/{season}", params = { "end", "start" })
	public ResponseEntity<Page<FriendsEpisode>> getEpisodeBySeason(@PathVariable("season") Integer season,
					@RequestParam(value = "start", required = false, defaultValue = "1") Integer start,
					@RequestParam(value = "end", required = false, defaultValue = "1") Integer end,
					HttpServletResponse response) {

		if (start < 1)
			start = 1;
		if (end < start || end == null)
			end = start + PAGE_SIZE - 1;

		Page<FriendsEpisode> seasonPage = serviceFriends.getSeason(season, start - 1, end - start + 1);
		return ResponseEntity.ok(seasonPage);
	}
}
