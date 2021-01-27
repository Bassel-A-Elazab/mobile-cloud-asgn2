package org.magnum.mobilecloud.video;

import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/video")
public class VideoController {
	
	@Autowired
	private VideoRepository videoRep; 
	
	
}
