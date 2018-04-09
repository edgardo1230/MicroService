package com.nearshoretechnology.focalpoint.eureka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.nearshoretechnology.focalpoint.config.eureka.FpEurekaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={FpEurekaApplication.class})
public class FpEurekaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
