package org.magnum.mobilecloud.video;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.magnum.mobilecloud.video.client.VideoNotFoundException;
import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
@RequestMapping(value="/video")
public class VideoController {
	
	@Autowired
	private VideoRepository videoRep; 
	
	// Mapping a request of /video to get all videos using spring data JPA.
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Collection<Video> getAllVideos(){
		return Lists.newArrayList(videoRep.findAll());
	}
	
	// Mapping a request of /video to add new video using spring data JPA.
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Video addVideo(@RequestBody Video video) {
		return videoRep.save(video);
	}
	
	// Mapping a request of /video/id to get the video info. Using ID.
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Video getVideo(@PathVariable long id){
		Video video = videoRep.findById(id);
		if(video == null){
			throw new VideoNotFoundException();
		}
		return video;
	}
	
	// Mapping a request of /video/{id}/like to allow user for like the videos.
	@RequestMapping(value = "/{id}/like", method = RequestMethod.POST)
	public void likeVideo(@PathVariable long id, Principal p, HttpServletResponse response) {
		Video video = videoRep.findById(id);
		if(video == null) {
			throw new VideoNotFoundException();
		}
		String username = p.getName();
		boolean liked = video.addLike(username);
		videoRep.save(video);
		if (liked) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
	
	// Mapping a request of /video/{id}/unlike to allow user for unlike the videos.
	@RequestMapping(value = "/{id}/unlike", method = RequestMethod.POST)
	public void unlikeVideo(@PathVariable long id, Principal p, HttpServletResponse response) {
		Video video = videoRep.findById(id);
		if(video == null) {
			throw new VideoNotFoundException();
		}
		String username = p.getName();
		boolean removed = video.removeLike(username);
		videoRep.save(video);
		if (removed) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
	
	// Mapping a request for get all videos that matches the passing title.
	@RequestMapping(value="search/finByName", method = RequestMethod.GET)
	public @ResponseBody Collection<Video> findVideoByName(@RequestParam("strName") String strName){
		Collection<Video> videos = videoRep.findByName(strName);
		if(videos == null) {
			videos = Collections.emptyList();
		}
		return videos;
	}
}
