package com.ikedi.giftcard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "product") // name of the table
@SQLDelete(sql = "UPDATE product SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "please Enter a product name")
    @NotNull(message = "please add a product name")
    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, precision = 2)
    private BigDecimal discount;

    @Column(nullable = false, precision = 2)
    private BigDecimal fee;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, updatable = false, name = "deleted_at")
    private LocalDateTime deletedAt;

    public Product(Long id, String name, BigDecimal discount, BigDecimal fee, boolean status) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.fee = fee;
        this.status = status;
    }
}


