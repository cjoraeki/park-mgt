package com.carparking.customerservice.entity;


//import com.carparking.addressservice.entity.Address;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;



@Document
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Customer {
    @Id
    private String id;

    private String firstname;

    private String lastname;

    @Indexed(unique = true)
    private String phoneNumber;

    @Indexed(unique = true)
    private String email;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

}
