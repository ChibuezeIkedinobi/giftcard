package com.ikedi.giftcard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "provider_product") // name of the table
@SQLDelete(sql = "UPDATE provider_product SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class ProviderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 2)
    private BigDecimal discount;

    @Column(nullable = false, precision = 2)
    private BigDecimal fee;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id")  //JoinColumn: Specifies a column for joining an entity association or element collection
    private Product product;

    @ManyToOne(targetEntity = Provider.class)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @OneToOne(targetEntity = Currency.class)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    private Currency currency;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false, updatable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, updatable = false, name = "deleted_at")
    private LocalDateTime deletedAt;
}
