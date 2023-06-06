package com.example.homework27.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "MyOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "Order quantity should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @NotNull(message = "totalPrice quantity should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer totalPrice;


    @Column(columnDefinition ="DATE NOT NULL")
    private LocalDate dateReceived;

    @NotNull(message ="status should not be empty" )
    @Pattern(regexp = "^(" + "NEW" + "|" + "INPROGRESS" + "|" + "COMPLETED" + ")", message = "status should be NEW or INPROGRESS or COMPLETED ")
//    @Column(columnDefinition = "varchar(20) not null check (status='new' or status='inProgress' or status='completed')")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name ="product_id",referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
