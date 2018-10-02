package com.java.test.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Region("test")
public class Customer {

    private String id;

}
