package br.com.davidev.attusdesafiobackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street_address")
    private String streetAddress;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "house_number")
    private int houseNumber;
    private String city;
    private String province;
    @Column(name = "default_address")
    private Boolean defaultAddress;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetAddress='" + streetAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", houseNumber=" + houseNumber +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", defaultAddress=" + defaultAddress +
                '}';
    }
}
