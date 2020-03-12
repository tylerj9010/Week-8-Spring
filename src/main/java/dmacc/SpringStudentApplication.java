package dmacc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import dmacc.beans.Student;
import dmacc.controller.BeanConfiguration;
import dmacc.repository.StudentRepository;

@SpringBootApplication
public class SpringStudentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudentApplication.class, args);
	}
	
	@Autowired
	StudentRepository repo;

	

	@Override
	public void run(String... args) throws Exception {
		ApplicationContext appContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
		
		
		Student s = appContext.getBean("student", Student.class);
		repo.save(s);
		
		Student s2 = new Student("John Smith", 21, "2017");
		repo.save(s2);
		
		List<Student> allStudents = repo.findAll();
		
		for (Student people: allStudents) {
			System.out.println(people.toString());
		}

		((AbstractApplicationContext) appContext).close();
		
	}
	
	
}


