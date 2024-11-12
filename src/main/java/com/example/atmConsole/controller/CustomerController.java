package com.example.atmConsole.controller;


import com.example.atmConsole.dto.CustomerCreateDto;
import com.example.atmConsole.dto.FindCustomerDto;
import com.example.atmConsole.dto.ResponseDto;
import com.example.atmConsole.dto.UpdateCustomerDto;
import com.example.atmConsole.model.Customer;
import com.example.atmConsole.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto<String>> createCustomer(@Validated @RequestBody CustomerCreateDto requestDto) {
        try {
            customerService.create(requestDto);
            ResponseDto<String> response = new ResponseDto<>("success", "User Created successfully");
            return ResponseEntity.status(CREATED).body(response);
        } catch (RuntimeException e) {
            ResponseDto<String> response = new ResponseDto<>("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("error", "An unexpected error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable Integer id) {
        try {
            FindCustomerDto customerDto = customerService.get(id);
            ResponseDto<String> response = new ResponseDto<>("found", "Customer found: " + customerDto.toString());
            return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("status","found","data",customerDto));
        } catch (RuntimeException e) {
            ResponseDto<String> response = new ResponseDto<>("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("error", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/acc/{accNo}")
    public ResponseEntity getCustomerByAcc(@PathVariable Integer accNo)
    {
        FindCustomerDto findCustomerDto = customerService.getByAcc(accNo);
        return ResponseEntity.ok().body(Map.of("status","ok","body",findCustomerDto));
    }

    @GetMapping("/all")
    public ResponseEntity getAllCustomer() {
        try {
                List<FindCustomerDto> allCustomers = customerService.getAll();
            if (allCustomers == null || allCustomers.isEmpty()) {
                    ResponseDto<String> response = new ResponseDto<>("not found","No customer found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            else {

                return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("message","found","data",allCustomers));
            }

        }
            catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("error", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) {
        try {
            customerService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Customer deleted");
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            ResponseDto<String> response = new ResponseDto<>("error", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity udpateCustomer(@PathVariable Integer id, @RequestBody UpdateCustomerDto updates)
    {
        try
        {
            Customer customer = customerService.update(updates,id);
            return ResponseEntity.ok().body(Map.of("message","updated successfully","data",customer));
        }catch (RuntimeException e)
        {
            return ResponseEntity.status(NOT_FOUND).body(Map.of("status","not found","message","customer not found"));
        }
    }

}
