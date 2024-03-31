package br.com.davidev.attusdesafiobackend.service.implementation;

import br.com.davidev.attusdesafiobackend.dto.AddressDTO;
import br.com.davidev.attusdesafiobackend.model.Address;
import br.com.davidev.attusdesafiobackend.model.converter.AddressConverter;
import br.com.davidev.attusdesafiobackend.repository.AddressRepository;
import br.com.davidev.attusdesafiobackend.repository.PersonRepository;
import br.com.davidev.attusdesafiobackend.service.AddressService;
import br.com.davidev.attusdesafiobackend.util.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;

    @Override
    public AddressDTO create(Long id, AddressDTO data) {

        var person = personRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("[AddressServiceImpl:addressCreate] Person with Id :{} not found", id);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );

        var address = AddressConverter.toEntity(data);
        address.setPerson(person);
        address.setDefaultAddress(person.getAddresses().isEmpty());

        var savedAddress = addressRepository.save(address);

        log.info("[AddressServiceImpl:addressCreate] Create one address:{}, has been created", savedAddress);

        return AddressConverter.toDTO(savedAddress);
    }

    @Override
    public AddressDTO update(Long idAddress, Long idPerson, AddressDTO data) {

        var address = addressRepository.findById(idAddress)
                .orElseThrow(() -> {
                            log.error("[AddressServiceImpl:updateAddress] Address with Id :{} not found", idAddress);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );

        validateAddressAssociation(address, idPerson);

        address.setStreetAddress(data.streetAddress());
        address.setZipCode(data.zipCode());
        address.setHouseNumber(data.houseNumber());
        address.setCity(data.city());
        address.setProvince(data.province());

        var updatedAddress = addressRepository.save(address);

        log.info("[AddressServiceImpl:addressUpdate] Update one address:{}, has been updated", updatedAddress);

        return AddressConverter.toDTO(updatedAddress);
    }

    @Override
    public void setDefaultAddress(Long idPerson, Long idAddress) {

        var address = addressRepository.findById(idAddress)
                .orElseThrow(() -> {
                            log.error("[AddressServiceImpl:updateAddress] Address with Id :{} not found", idAddress);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );
        validateAddressAssociation(address, idPerson);

        addressRepository.updateDefaultAddress(idPerson, idAddress);
        log.info("[AddressServiceImpl:addressSetDefaultAddress] set the defaultAddress:{}, defaultAddress defined", address);

    }

    @Override
    public AddressDTO findById(Long id) {
        var address = addressRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("[AddressServiceImpl:updateAddress] Getting one ID :{} not found", id);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );

        log.info("[AddressServiceImpl:addressFindById] Getting one address:{}, address found", address);

        return AddressConverter.toDTO(address);
    }

    @Override
    public List<AddressDTO> findAll() {
        List<Address> addresses = addressRepository.findAll();

        return AddressConverter.toDTOList(addresses);
    }

    @Override
    public void delete(Long id) {
        var address = addressRepository.findById(id)
                .orElseThrow(() -> {
                            log.error("[AddressServiceImpl:updateAddress] Address with Id :{} not found", id);
                            return ExceptionUtils.handleResourceNotFoundException();
                        }
                );

        addressRepository.delete(address);

        log.info("[AddressServiceImpl:addressDelete] Delete one address:{}, address deleted", address);
    }

    private void validateAddressAssociation(Address address, Long idPerson) {
        if (!address.getPerson().getId().equals(idPerson)) {
            throw ExceptionUtils.handleAddressNotAssociatedException();
        }
    }
}
