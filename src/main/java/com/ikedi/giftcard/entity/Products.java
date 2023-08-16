package com.ikedi.giftcard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "products") // name of the table
@SQLDelete(sql = "UPDATE products SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private long discount;

    private long fee;

    @NotBlank
    private boolean status;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, updatable = false, name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "products", referencedColumnName = "product_id")
    private Currency currency;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "provider_product",
            joinColumns = @JoinColumn(name = "product", referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "provider", referencedColumnName = "provider_id")
    )
    private Set<Provider> providers;

    public Products(Long id, String name, long discount, long fee, boolean status, Currency currency, Set<Provider> providers) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.fee = fee;
        this.status = status;
        this.currency = currency;
        this.providers = providers;
    }
}


