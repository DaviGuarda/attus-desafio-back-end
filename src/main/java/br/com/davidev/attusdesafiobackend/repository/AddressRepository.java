package br.com.davidev.attusdesafiobackend.repository;

import br.com.davidev.attusdesafiobackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying
    @Query("UPDATE Address a SET a.defaultAddress = CASE WHEN a.id = ?2 THEN true ELSE false END WHERE a.person.id = ?1")
    void updateDefaultAddress(Long idPerson, Long idAddress);

}
