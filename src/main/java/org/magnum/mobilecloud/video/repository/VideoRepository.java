package org.magnum.mobilecloud.video.repository;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long>{
	
	// return the video by using ID.
	Video findById(Long id);
	
	// return the video using their name.
	Collection<Video> findByName(String name);
}
