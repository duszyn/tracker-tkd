package com.duszynski.tracker_tkd;

import com.duszynski.tracker_tkd.model.Student;
import com.duszynski.tracker_tkd.model.StudentStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TrackerTkdApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackerTkdApplication.class, args);

		Student student = new Student("Jan", "Kowalski", LocalDate.of(2025, 1, 2), StudentStatus.ACTIVE);
	}

}
