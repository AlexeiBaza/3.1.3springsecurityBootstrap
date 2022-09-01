package net.proselyte.springbootdemo;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Optional;

@SpringBootApplication
public class SpringbootdemoApplication {

	private final UserRepository userRepository;

	@Autowired
	public SpringbootdemoApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void test() {
		Optional<User> user1 = userRepository.findById(1L);
		user1.get().getRoles().forEach(System.out::println);
	}

}
