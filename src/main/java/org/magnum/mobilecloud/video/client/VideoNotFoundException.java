package org.magnum.mobilecloud.video.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "video dosn't exist")
public class VideoNotFoundException extends RuntimeException{

}
