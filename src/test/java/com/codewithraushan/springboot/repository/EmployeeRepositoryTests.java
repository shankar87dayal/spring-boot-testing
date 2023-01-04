package com.codewithraushan.springboot.repository;

import com.codewithraushan.springboot.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;


    // JUnit test for save employee operation
//    @DisplayName("JUnit test for save employee operation") //default it give it's methods name
    @Test
    public void giveEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setUp
        Employee employee = Employee.builder() //we can also use setter methods to save the object
                .firstName("Raushan")
                .lastName("kumar")
                .email("raushan@gmail.com")
                .build();

        //when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        /**
         * Here we use assertThat method, this is a static method of Assertions class thats wise we call
         * Asssertions class if we import the static assertThat method then we can remove the
         * Assertions class. and we can direct  import assertThat method using (import static
         * org.assertj.core.api.Assertions.assertThat;) this. and now we can remove the Assertions class
         * :
         */
//        Assertions.assertThat(savedEmployee).isNotNull();
//        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
}
