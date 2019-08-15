package com.fse.projectmanagementui.projectmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	HealthCheckTest.class,
	UserServiceTest.class,
	ProjectServiceTest.class
})
//@SpringBootTest(classes = {HealthCheckTest.class, UserServiceTest.class})
public class ProjectManagerApplicationTests {

	

}
