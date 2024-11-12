package com.example.atmConsole.repo;

import com.example.atmConsole.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
