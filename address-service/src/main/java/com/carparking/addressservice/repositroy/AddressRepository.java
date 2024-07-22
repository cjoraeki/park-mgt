package com.carparking.addressservice.repositroy;

import com.carparking.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCustomerId(String customerId);
}
