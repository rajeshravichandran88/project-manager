package com.fse.projectmanager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	HealthCheckTest.class,
	UserServiceTest.class,
	ProjectServiceTest.class,
	TaskServiceTest.class,
	ParentTaskServiceTest.class
})
//@SpringBootTest(classes = {HealthCheckTest.class, UserServiceTest.class})
public class ProjectManagerApplicationTests {

	

}
