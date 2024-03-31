package br.com.davidev.attusdesafiobackend.unittests.mockito.service;

import br.com.davidev.attusdesafiobackend.model.Address;
import br.com.davidev.attusdesafiobackend.model.converter.AddressConverter;
import br.com.davidev.attusdesafiobackend.repository.AddressRepository;
import br.com.davidev.attusdesafiobackend.repository.PersonRepository;
import br.com.davidev.attusdesafiobackend.service.implementation.AddressServiceImpl;
import br.com.davidev.attusdesafiobackend.unittests.converter.mocks.MockAddress;
import br.com.davidev.attusdesafiobackend.unittests.converter.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AddressServiceImplTest {

    MockAddress input;
    MockPerson inputPerson;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    public void setup() {
        input = new MockAddress();
        inputPerson = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateAddress() {
        var addressDTO = input.mockDTO(1);

        var person = inputPerson.mockEntity(1);

        var address = AddressConverter.toEntity(addressDTO);
        address.setPerson(person);
        address.setDefaultAddress(true);

        var savedAddress = input.mockEntity(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(addressRepository.save(any(Address.class))).thenReturn(savedAddress);

        var result = addressService.create(1L, addressDTO);

        verify(personRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).save(any(Address.class));
        assertThat(result.id()).isEqualTo(savedAddress.getId());
        assertThat(result.streetAddress()).isEqualTo(savedAddress.getStreetAddress());
        assertThat(result.zipCode()).isEqualTo(savedAddress.getZipCode());
        assertThat(result.houseNumber()).isEqualTo(savedAddress.getHouseNumber());
        assertThat(result.city()).isEqualTo(savedAddress.getCity());
        assertThat(result.province()).isEqualTo(savedAddress.getProvince());
    }

    @Test
    public void shouldUpdateAddress() {
        var addressDTO = input.mockDTO(1);

        var address = input.mockEntity(1);

        var updatedAddress = input.mockEntity(1);
        updatedAddress.setStreetAddress("São Caetano");

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);

        var result = addressService.update(1L, 1L, addressDTO);

        verify(addressRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).save(any(Address.class));
        assertThat(updatedAddress.getId()).isEqualTo(result.id());
        assertThat(updatedAddress.getStreetAddress()).isEqualTo(result.streetAddress());
        assertThat(updatedAddress.getZipCode()).isEqualTo(result.zipCode());
        assertThat(updatedAddress.getHouseNumber()).isEqualTo(result.houseNumber());
        assertThat(updatedAddress.getCity()).isEqualTo(result.city());
        assertThat(updatedAddress.getProvince()).isEqualTo(result.province());
    }

    @Test
    public void shouldSetDefaultAddress() {
        var person = inputPerson.mockEntity(1);

        var address = input.mockEntity(1);
        address.setPerson(person);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        addressService.setDefaultAddress(1L, 1L);

        verify(addressRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).updateDefaultAddress(1L, 1L);
    }

    @Test
    public void shouldFindByIdAddress() {
        var address = input.mockEntity(1);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        var result = addressService.findById(1L);

        verify(addressRepository, times(1)).findById(1L);

        assertThat(address.getId()).isEqualTo(result.id());
        assertThat(address.getStreetAddress()).isEqualTo(result.streetAddress());
        assertThat(address.getZipCode()).isEqualTo(result.zipCode());
        assertThat(address.getHouseNumber()).isEqualTo(result.houseNumber());
        assertThat(address.getCity()).isEqualTo(result.city());
        assertThat(address.getProvince()).isEqualTo(result.province());
    }

    @Test
    public void shouldFindAllAddress() {
        var addresses = input.mockEntityList();

        when(addressRepository.findAll()).thenReturn(addresses);

        var result = addressService.findAll();

        verify(addressRepository, times(1)).findAll();
        assertThat(addresses.size()).isEqualTo(result.size());
        for (int i = 0; i < addresses.size(); i++) {
            var address = addresses.get(i);
            var addressDTO = result.get(i);

            assertThat(address.getId()).isEqualTo(addressDTO.id());
            assertThat(address.getStreetAddress()).isEqualTo(addressDTO.streetAddress());
            assertThat(address.getZipCode()).isEqualTo(addressDTO.zipCode());
            assertThat(address.getHouseNumber()).isEqualTo(addressDTO.houseNumber());
            assertThat(address.getCity()).isEqualTo(addressDTO.city());
            assertThat(address.getProvince()).isEqualTo(addressDTO.province());
        }
    }

    @Test
    public void shouldDeleteAddress() {

        var address = input.mockEntity(1);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        addressService.delete(1L);

        verify(addressRepository, times(1)).findById(1L);
        verify(addressRepository, times(1)).delete(address);
    }

}