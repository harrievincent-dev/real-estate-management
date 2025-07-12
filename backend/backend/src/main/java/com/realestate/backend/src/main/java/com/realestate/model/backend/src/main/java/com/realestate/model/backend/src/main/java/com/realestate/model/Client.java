package com.realestate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name")
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number should be valid")
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @NotBlank(message = "Address is required")
    @Column(length = 500)
    private String address;
    
    private String city;
    private String state;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @NotNull(message = "Client type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;
    
    @Column(name = "budget_min", precision = 15, scale = 2)
    private Double budgetMin;
    
    @Column(name = "budget_max", precision = 15, scale = 2)
    private Double budgetMax;
    
    @Column(name = "preferred_locations")
    private String preferredLocations;
    
    @Column(name = "property_preferences", length = 1000)
    private String propertyPreferences;
    
    @Enumerated(EnumType.STRING)
    private ClientStatus status;
    
    @Column(name = "lead_source")
    private String leadSource;
    
    @Column(length = 1000)
    private String notes;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "owner", cascade
