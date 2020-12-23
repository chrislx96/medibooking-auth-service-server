package com.medibooking.authenticationserver.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString(exclude = "accounts")
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "permission", unique = true, nullable = false)
    private String permission;

    @ManyToMany
    @JoinTable(name = "accounts_authorities",
            joinColumns = @JoinColumn(name = "authority_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private Set<Account> accounts;
}
