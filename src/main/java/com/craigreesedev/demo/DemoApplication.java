package com.craigreesedev.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.craigreesedev.demo.user.User;
import com.craigreesedev.demo.user.UserRepository;

@SpringBootApplication
public class DemoApplication {

	private final UserRepository userRepository;

    DemoApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {
			if (userRepository.findAll().size() == 0) {
				userRepository.save(new User("testUserName", "Test Full Name"));
				userRepository.save(new User("testUserName2", "Test Full Name Two"));
			}
		};
	}
}
