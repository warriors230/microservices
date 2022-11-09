package com.warriors.microservicios.customerms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tb_customers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customers implements Serializable {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Column(name = "number_id", nullable = false, unique = true)
    @Size(min = 8, max = 8)
    private String numberID;
    @Column(name = "firts_name", nullable = false)
    private String firtsName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    @JoinColumn(name = "region_id")
    private Region region;
    private String state;
    @Column(name = "photo_url")
    private String photoUrl;

}
