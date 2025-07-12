package com.realestate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Property title is required")
    @Size(min = 5, max = 200, message = "Property title must be between 5 and 200 characters")
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    @NotBlank(message = "Address is required")
    @Column(length = 500)
    private String address;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "State is required")
    private String state;
    
    @NotBlank(message = "Postal code is required")
    @Column(name = "postal_code")
    private String postalCode;
    
    private String country;
    
    @NotNull(message = "Property type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "property_type")
    private PropertyType propertyType;
    
    @NotNull(message = "Listing type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "listing_type")
    private ListingType listingType;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(precision = 15, scale = 2)
    private BigDecimal price;
    
    @NotNull(message = "Bedrooms count is required")
    @Min(value = 0, message = "Bedrooms count cannot be negative")
    private Integer bedrooms;
    
    @NotNull(message = "Bathrooms count is required")
    @Min(value = 0, message = "Bathrooms count cannot be negative")
    private Integer bathrooms;
    
    @Column(name = "square_feet")
    private Integer squareFeet;
    
    @Column(name = "lot_size")
    private Double lotSize;
    
    @Column(name = "year_built")
    private Integer yearBuilt;
    
    @Column(name = "garage_spaces")
    private Integer garageSpaces;
    
    @Column(name = "parking_spaces")
    private Integer parkingSpaces;
    
    @Column(name = "property_features", length = 1000)
    private String propertyFeatures;
    
    @Column(name = "appliances_included", length = 500)
    private String appliancesIncluded;
    
    @Column(name = "heating_type")
    private String heatingType;
    
    @Column(name = "cooling_type")
    private String coolingType;
    
    @Column(name = "flooring_type")
    private String flooringType;
    
    @Column(name = "property_condition")
    @Enumerated(EnumType.STRING)
    private PropertyCondition propertyCondition;
    
    @Column(name = "hoa_fee", precision = 10, scale = 2)
    private BigDecimal hoaFee;
    
    @Column(name = "property_taxes", precision = 10, scale = 2)
    private BigDecimal propertyTaxes;
    
    @Column(name = "listing_date")
    private LocalDate listingDate;
    
    @Column(name = "available_date")
    private LocalDate availableDate;
    
    @Enumerated(EnumType.STRING)
    private PropertyStatus status;
    
    @Column(name = "virtual_tour_url")
    private String virtualTourUrl;
    
    @Column(name = "main_image_url")
    private String mainImageUrl;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client owner;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PropertyImage> images;
    
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;
    
    public enum PropertyType {
        HOUSE, APARTMENT, CONDO, TOWNHOUSE, VILLA, DUPLEX, LAND, COMMERCIAL, OFFICE, WAREHOUSE
    }
    
    public enum ListingType {
        SALE, RENT, LEASE
    }
    
    public enum PropertyCondition {
        EXCELLENT, GOOD, FAIR, NEEDS_REPAIR, NEW_CONSTRUCTION
    }
    
    public enum PropertyStatus {
        ACTIVE, PENDING, SOLD, RENTED, WITHDRAWN, EXPIRED
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = PropertyStatus.ACTIVE;
        }
        if (listingDate == null) {
            listingDate = LocalDate.now();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Property() {}
    
    public Property(String title, String address, String city, String state, String postalCode,
                   PropertyType propertyType, ListingType listingType, BigDecimal price,
                   Integer bedrooms, Integer bathrooms, Agent agent) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.propertyType = propertyType;
        this.listingType = listingType;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.agent = agent;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public PropertyType getPropertyType() { return propertyType; }
    public void setPropertyType(PropertyType propertyType) { this.propertyType = propertyType; }
    
    public ListingType getListingType() { return listingType; }
    public void setListingType(ListingType listingType) { this.listingType = listingType; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public Integer getBedrooms() { return bedrooms; }
    public void setBedrooms(Integer bedrooms) { this.bedrooms = bedrooms; }
    
    public Integer getBathrooms() { return bathrooms; }
    public void setBathrooms(Integer bathrooms) { this.bathrooms = bathrooms; }
    
    public Integer getSquareFeet() { return squareFeet; }
    public void setSquareFeet(Integer squareFeet) { this.squareFeet = squareFeet; }
    
    public Double getLotSize() { return lotSize; }
    public void setLotSize(Double lotSize) { this.lotSize = lotSize; }
    
    public Integer getYearBuilt() { return yearBuilt; }
    public void setYearBuilt(Integer yearBuilt) { this.yearBuilt = yearBuilt; }
    
    public Integer getGarageSpaces() { return garageSpaces; }
    public void setGarageSpaces(Integer garageSpaces) { this.garageSpaces = garageSpaces; }
    
    public Integer getParkingSpaces() { return parkingSpaces; }
    public void setParkingSpaces(Integer parkingSpaces) { this.parkingSpaces = parkingSpaces; }
    
    public String getPropertyFeatures() { return propertyFeatures; }
    public void setPropertyFeatures(String propertyFeatures) { this.propertyFeatures = propertyFeatures; }
    
    public String getAppliancesIncluded() { return appliancesIncluded; }
    public void setAppliancesIncluded(String appliancesIncluded) { this.appliancesIncluded = appliancesIncluded; }
    
    public String getHeatingType() { return heatingType; }
    public void setHeatingType(String heatingType) { this.heatingType = heatingType; }
    
    public String getCoolingType() { return coolingType; }
    public void setCoolingType(String coolingType) { this.coolingType = coolingType; }
    
    public String getFlooringType() { return flooringType; }
    public void setFlooringType(String flooringType) { this.flooringType = flooringType; }
    
    public PropertyCondition getPropertyCondition() { return propertyCondition; }
    public void setPropertyCondition(PropertyCondition propertyCondition) { this.propertyCondition = propertyCondition; }
    
    public BigDecimal getHoaFee() { return hoaFee; }
    public void setHoaFee(BigDecimal hoaFee) { this.hoaFee = hoaFee; }
    
    public BigDecimal getPropertyTaxes() { return propertyTaxes; }
    public void setPropertyTaxes(BigDecimal propertyTaxes) { this.propertyTaxes = propertyTaxes; }
    
    public LocalDate getListingDate() { return listingDate; }
    public void setListingDate(LocalDate listingDate) { this.listingDate = listingDate; }
    
    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    
    public PropertyStatus getStatus() { return status; }
    public void setStatus(PropertyStatus status) { this.status = status; }
    
    public String getVirtualTourUrl() { return virtualTourUrl; }
    public void setVirtualTourUrl(String virtualTourUrl) { this.virtualTourUrl = virtualTourUrl; }
    
    public String getMainImageUrl() { return mainImageUrl; }
    public void setMainImageUrl(String mainImageUrl) { this.mainImageUrl = mainImageUrl; }
    
    public Agent getAgent() { return agent; }
    public void setAgent(Agent agent) { this.agent = agent; }
    
    public Client getOwner() { return owner; }
    public void setOwner(Client owner) { this.owner = owner; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<PropertyImage> getImages() { return images; }
    public void setImages(List<PropertyImage> images) { this.images = images; }
    
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
    
    public String getFullAddress() {
        StringBuilder fullAddress = new StringBuilder(address);
        if (city != null) fullAddress.append(", ").append(city);
        if (state != null) fullAddress.append(", ").append(state);
        if (postalCode != null) fullAddress.append(" ").append(postalCode);
        if (country != null) fullAddress.append(", ").append(country);
        return fullAddress.toString();
    }
    
    public String getShortDescription() {
        return bedrooms + " bed, " + bathrooms + " bath " + propertyType.toString().toLowerCase();
    }
}
