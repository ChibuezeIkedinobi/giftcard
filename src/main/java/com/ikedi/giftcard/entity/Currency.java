package com.ikedi.giftcard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "currency") // name of the table
@SQLDelete(sql = "UPDATE currency SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Enter a country")
    @Column(nullable = false)
    private String country;

    @NotBlank(message = "Enter currency name")
    private String currency_name;

    @NotBlank(message = "Enter currency code")
    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false, name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false, updatable = false, name = "deleted_at")
    private LocalDateTime deletedAt;

    public Currency(Long id, String country, String currency_name, String code) {
        this.id = id;
        this.country = country;
        this.currency_name = currency_name;
        this.code = code;
    }
}
