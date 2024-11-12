package com.example.atmConsole.service;


import com.example.atmConsole.dto.CustomerCreateDto;
import com.example.atmConsole.dto.FindCustomerDto;
import com.example.atmConsole.dto.UpdateCustomerDto;
import com.example.atmConsole.model.Account;
import com.example.atmConsole.model.Customer;
import com.example.atmConsole.repo.AccountRepo;
import com.example.atmConsole.repo.AddressRepo;
import com.example.atmConsole.repo.CustomerRepo;
import com.example.atmConsole.util.AccNumberGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Transactional
    @Override
    public void create(CustomerCreateDto customerDto) {
        Customer newCustomer = new Customer(customerDto);

        AccNumberGenerator newAccNo = new AccNumberGenerator();
        Account newAccount = new Account(customerDto);

        newAccount.setAccNo(newAccNo.generateAccount());
        newAccount.setCustomer(newCustomer);

        newCustomer.setAccount(newAccount);

        addressRepo.save(customerDto.getAddress());
        accountRepo.save(newAccount);
        customerRepo.save(newCustomer);
    }

    @Override
    public FindCustomerDto get(Integer id) {
        Customer customer = customerRepo.findById(id).get();
        FindCustomerDto findCustomerDto = new FindCustomerDto(customer);
        return findCustomerDto;
    }

    @Override
    public FindCustomerDto getByAcc(Integer accNo) {
        Optional<Account> optionalAccount = accountRepo.findByAccNo(accNo);
        if(optionalAccount.isPresent())
        {
            Account account = optionalAccount.get();
            Customer customer = account.getCustomer();
            FindCustomerDto findCustomerDto = new FindCustomerDto(customer);
            findCustomerDto.setAccount(account);
            return findCustomerDto;
        }
        else {

        throw new RuntimeException("Could not find the account");
        }
    }

    @Transactional
    @Override
    public Customer update(UpdateCustomerDto updates, Integer id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if(optionalCustomer.isPresent())
        {
            Customer customer = optionalCustomer.get();
            Account account = accountRepo.findByAccNo(customer.getAccount().getAccNo()).get();
            customer.setAddress(updates.getAddress());
            accountRepo.save(account);
            addressRepo.save(updates.getAddress());
            customerRepo.save(customer);
            return customer;
        }
        else
        {
            throw new RuntimeException("User not found");
        }

    }

    @Override
    public void delete(Integer id) {
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if(optionalCustomer.isPresent())
        {
            Customer customer = optionalCustomer.get();
            customerRepo.delete(customer);
        }
        else
        {
            throw new RuntimeException("User not found");
        }

    }


    @Override
    public List<FindCustomerDto> getAll() {
        List<Customer> customer = customerRepo.findAll();
        List<FindCustomerDto> allCustomers =  customer.stream()
                .map(cus -> {
                    return new FindCustomerDto(cus.getId(),cus.getName(),cus.getAccount().getAccNo());
                }).toList();
        return  allCustomers;
    }
}
