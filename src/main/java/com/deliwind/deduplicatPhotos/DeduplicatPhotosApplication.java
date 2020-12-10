package com.deliwind.deduplicatPhotos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DeduplicatPhotosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeduplicatPhotosApplication.class, args);
	}

}
