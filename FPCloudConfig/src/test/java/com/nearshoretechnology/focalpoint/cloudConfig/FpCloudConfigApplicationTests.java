package com.nearshoretechnology.focalpoint.cloudConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.nearshoretechnology.focalpoint.config.cloud.FpCloudConfigApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={FpCloudConfigApplication.class})
public class FpCloudConfigApplicationTests {

	@Test
	public void contextLoads() {
	}

}
