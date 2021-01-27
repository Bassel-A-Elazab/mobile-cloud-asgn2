package org.magnum.mobilecloud.video;

import java.util.Collection;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
}
