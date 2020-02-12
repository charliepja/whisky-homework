package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canFindWhiskiesYear2018() {
		List<Whisky> found = whiskyRepository.findByYear(2018);
		assertEquals(2018, found.get(0).getYear());
	}

	@Test
	public void canFindDistilleriesByRegion() {
		List<Distillery> found = distilleryRepository.findDistilleriesByRegion("Speyside");
		assertEquals("Speyside", found.get(0).getRegion());
	}

	@Test
	public void canFindWhiskiesByDistilleryAndAge() {
		Distillery distillery1 = distilleryRepository.findDistinctDistilleryByName("Glendronach");
		List<Whisky> found = whiskyRepository.findWhiskiesByDistilleryAndAge(distillery1, 15);
		assertEquals("The Glendronach Revival", found.get(0).getName());
	}

}
