package com.livecommerce.mq;

import com.livecommerce.dto.Response;
import com.livecommerce.model.Employees;
import com.livecommerce.service.EmployeesService;
import com.livecommerce.local.dto.ResponseLocal;
import com.livecommerce.local.model.EmployeesLocal;
import com.livecommerce.local.service.EmployeesLocalService;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

@Slf4j
@Component
public class MessageListener {

    @Autowired
    EmployeesService employeeService;

    @Autowired
    EmployeesLocalService employeeLocalService;

    public MessageListener(EmployeesService employeeService){
        this.employeeService = employeeService;
    }

    Logger logger = getLogger(MessageListener.class);

    @RabbitListener(queues = MQconfig.QUEUE)
    public void listener(CustomMessage message){
        if(Objects.nonNull(message)) {
            logger.info("NUll error Message {} message {} "+MQconfig.QUEUE + message);
        }
        logger.info("Got Message {} message {} "+MQconfig.QUEUE + message);
        Either<Response, List<Employees>> eitherR = this.employeeService.postingEmployees(message.getMessage());
        Response responseError = eitherR.getLeft();
        if(Objects.nonNull(responseError) && Objects.nonNull(responseError.getMessage())){
            logger.info("Error to save Employees Message {} message {} "+MQconfig.QUEUE + message +" tha was not Saved in DB" +responseError.getMessage());
        }
        Try<List<Employees>> tryEmployeeList = eitherR.toTry();
        if(Objects.isNull(tryEmployeeList) || tryEmployeeList.isEmpty()) {
            logger.info("We got no response to save Employees Message {} message {} "+MQconfig.QUEUE + message +" tha was not Saved in DB");
        }
        logger.info("Employees Message {} message {} " + MQconfig.QUEUE + message + " Saved in DB" + tryEmployeeList);
    }

    /*@RabbitListener(queues = MQconfig.QUEUE)
    public void listenerLocal(CustomLocalMessage message){
        if(Objects.nonNull(message)) {
            logger.info("NUll error Message {} message {} "+MQconfig.QUEUE + message);
        }
        logger.info("Got Message {} message {} "+MQconfig.QUEUE + message);
        Either<ResponseLocal, List<EmployeesLocal>> eitherR = this.employeeLocalService.postingEmployees(message.getMessage());
        ResponseLocal responseError = eitherR.getLeft();
        if(Objects.nonNull(responseError) && Objects.nonNull(responseError.getMessage())){
            logger.info("Error to save Employees Message {} message {} "+MQconfig.QUEUE + message +" tha was not Saved in DB" +responseError.getMessage());
        }
        Try<List<EmployeesLocal>> tryEmployeeList = eitherR.toTry();
        if(Objects.isNull(tryEmployeeList) || tryEmployeeList.isEmpty()) {
            logger.info("We got no response to save Employees Message {} message {} "+MQconfig.QUEUE + message +" tha was not Saved in DB");
        }
        logger.info("Employees Message {} message {} " + MQconfig.QUEUE + message + " Saved in DB" + tryEmployeeList);
    }*/
}
