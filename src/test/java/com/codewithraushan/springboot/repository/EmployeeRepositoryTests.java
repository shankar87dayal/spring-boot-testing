package com.codewithraushan.springboot.repository;

import com.codewithraushan.springboot.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup(){
         employee = Employee.builder()
                .firstName("Raushan")
                .lastName("Ranjan")
                .email("raushan@gmail.com")
                .build();
    }


    // JUnit test for save employee operation
//    @DisplayName("JUnit test for save employee operation") //default it give it's methods name
    @Test
    public void giveEmployeeObject_whenSave_thenReturnSavedEmployee() {

        //given - precondition or setUp
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();

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


    // JUnit test for get all employees operations
    @DisplayName("JUnit test for get all employees operations")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {

        //given - precondition or setUp

//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        Employee employee1 = Employee.builder() //we can also use setter methods to save the object
                .firstName("Rahul")
                .lastName("kumar")
                .email("rahuln@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        //when - action or the behaviour that we are going test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    //junit test for get employee by id operation
    @DisplayName("junit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

        //given -precondition or setup
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("ravi@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or the behaviour that we are going test
        Employee employeeDb = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        assertThat(employeeDb).isNotNull();

    }

    //junit test for get employee by email operations
    @DisplayName("junit test for get employee by email operations")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {

        //given -precondition or setup
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        employeeRepository.save(employee);

        //when - action or the behaviour that we are going test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();


    }

    //junit test for update employee operations
    @DisplayName("junit test for update employee operations")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployeeObejct() {

        //given -precondition or setup
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        employeeRepository.save(employee);


        //when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("ravi@gmail.com");
        savedEmployee.setFirstName("Ravi");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        //then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("ravi@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ravi");

    }


    //junit test for delete employee operation
    @DisplayName("junit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployeeObect() {

        //given -precondition or setup
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        employeeRepository.save(employee);


        //when - action or the behaviour that we are going test
//        employeeRepository.delete(employee);
        employeeRepository.deleteById(employee.getId());

        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());

        //then - verify the output
        assertThat(optionalEmployee).isEmpty();

    }

    //junit test for custom Query using JPQl index
    @DisplayName("junit test for custom Query using JPQl index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject() {

        //given -precondition or setup
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Raushan";
        String lastName = "Ranjan";

        //when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    //junit test for custom Query using JPQl with Named params
    @DisplayName("junit test for custom Query using JPQl with Named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        //given -precondition or setup
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        employeeRepository.save(employee);
        String firstName = "Raushan";
        String lastName = "Ranjan";

        //when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);
        //then - verify the output
        assertThat(savedEmployee).isNotNull();
    }
    //junit test for custom query using native SQL with index
    @DisplayName("junit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenRetuenEmployeeObject(){

       //given -precondition or setup
//        Employee employee = Employee.builder() //we can also use setter methods to save the object
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        employeeRepository.save(employee);
//        String firstName = "Raushan";
//        String lastName = "Ranjan";

       //when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

       //then - verify the output
        assertThat(savedEmployee).isNotNull();

     }

    //junit test for custom query using native SQL with named params
    @DisplayName("junit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenRetuenEmployeeObject(){

        //given -precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Raushan")
//                .lastName("Ranjan")
//                .email("raushan@gmail.com")
//                .build();
        employeeRepository.save(employee);


        //when - action or the behaviour that we are going test
        Employee savedEmployee = employeeRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        //then - verify the output
        assertThat(savedEmployee).isNotNull();

    }
}
