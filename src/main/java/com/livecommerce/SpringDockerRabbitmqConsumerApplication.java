package com.livecommerce;

import com.livecommerce.data.dao.EmployeesRepository;
import com.livecommerce.data.dao2.EmployeeTwoRepository;
import com.livecommerce.model.Employees;
import com.livecommerce.model2.Employees2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringDockerRabbitmqConsumerApplication implements CommandLineRunner {

	@Autowired
	private EmployeesRepository employeesRepository;

	@Autowired
	private EmployeeTwoRepository employees2Repository;
	public static void main(String[] args) {
		SpringApplication.run(SpringDockerRabbitmqConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("************************************************************");
		log.info("Start creating and printing mongo objects");
		log.info("************************************************************");

		/*this.employeesRepository.save(new Employees( "Pablo", "IT", 5000.00));
		this.employees2Repository.save(new Employees2("Daniel", "IT", 7000.00));

		List<Employees> model1s = this.employeesRepository.findAll();
		for (Employees model1obj : model1s) {
			log.info(model1obj.toString());
		}


		List<Employees2> model2s = this.employees2Repository.findAll();
		for (Employees2 model2obj : model2s) {
			log.info(model2obj.toString());
		}*/

		log.info("************************************************************");
		log.info("Ended printing mongo objects");
		log.info("************************************************************");

	}

	/*@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		return sessionFactory;
	}*/
}
