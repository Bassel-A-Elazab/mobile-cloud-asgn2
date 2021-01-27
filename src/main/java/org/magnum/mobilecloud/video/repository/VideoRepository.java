package org.magnum.mobilecloud.video.repository;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long>{
	
	// return the video by using ID.
	Video findById(Long id);
	
	// return the videos using their name.
	Collection<Video> findByName(String name);
	
	// return the videos that their duration is less than specific duration.
	Collection<Video> findByDurationLessThan(long duration);
}
