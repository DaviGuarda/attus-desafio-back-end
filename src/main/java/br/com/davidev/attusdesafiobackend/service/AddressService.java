package br.com.davidev.attusdesafiobackend.service;

import br.com.davidev.attusdesafiobackend.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO create(Long id, AddressDTO data);
    AddressDTO update(Long idAddress, Long idPerson, AddressDTO data);
    void setDefaultAddress(Long personId, Long addressId);
    AddressDTO findById(Long id);
    List<AddressDTO> findAll();
    void delete(Long id);
}
