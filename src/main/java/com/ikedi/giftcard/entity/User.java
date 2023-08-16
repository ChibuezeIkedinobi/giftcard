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
@Table(name = "user") // name of the table
@SQLDelete(sql = "UPDATE user SET deleted_at = NOW() where id=?", check = ResultCheckStyle.COUNT)  // soft delete
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, updatable = false, name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet", referencedColumnName = "wallet_id")
    private Wallet wallet;

    public User(Long id, String firstName, String lastName, String email, Wallet wallet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.wallet = wallet;
    }
}
