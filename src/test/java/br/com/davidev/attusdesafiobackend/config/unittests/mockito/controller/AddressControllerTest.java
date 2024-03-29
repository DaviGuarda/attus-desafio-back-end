package br.com.davidev.attusdesafiobackend.config.unittests.mockito.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import br.com.davidev.attusdesafiobackend.controller.AddressController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.davidev.attusdesafiobackend.dto.AddressDTO;
import br.com.davidev.attusdesafiobackend.service.AddressService;

public class AddressControllerTest {

    private AddressService addressService;
    private AddressController addressController;

    @BeforeEach
    public void setUp() {
        addressService = mock(AddressService.class);
        addressController = new AddressController(addressService);
    }

    @Test
    public void shouldCreateAddress() {
        Long id = 1L;
        AddressDTO data = new AddressDTO(
                null,
                "Street Address",
                "Zip Code",
                10,
                "City",
                "Province",
                false
        );
        AddressDTO expectedAddress = new AddressDTO(
                1L,
                "Street Address",
                "Zip Code",
                10,
                "City",
                "Province",
                true
        );
        when(addressService.create(id, data)).thenReturn(expectedAddress);

        ResponseEntity<AddressDTO> response = addressController.create(id, data);

        verify(addressService, times(1)).create(id, data);
        assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());
        assertThat(expectedAddress).isEqualTo(response.getBody());
    }

    @Test
    public void shouldUpdateAddress() {
        Long idAddress = 1L;
        Long idPerson = 1L;
        AddressDTO data = new AddressDTO(
                1L,
                "Street Address",
                "Zip Code",
                10,
                "City",
                "Province",
                false
        );
        AddressDTO expectedAddress = new AddressDTO(
                1L,
                "Street Address",
                "Zip Code",
                10,
                "City",
                "Province",
                false
        );
        when(addressService.update(idAddress, idPerson, data)).thenReturn(expectedAddress);

        ResponseEntity<AddressDTO> response = addressController.update(idAddress, idPerson, data);

        verify(addressService, times(1)).update(idAddress, idPerson, data);
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(expectedAddress).isEqualTo(response.getBody());
    }

    @Test
    public void shouldSetDefaultAddress() {
        Long idPerson = 1L;
        Long idAddress = 1L;

        ResponseEntity<?> response = addressController.setDefaultAddress(idPerson, idAddress);

        verify(addressService, times(1)).setDefaultAddress(idPerson, idAddress);
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat("Address set as default successfully!").isEqualTo(response.getBody());
    }

    @Test
    public void shouldFindByIdAddress() {
        Long id = 1L;
        AddressDTO expectedAddress = new AddressDTO(
                1L,
                "Street Address",
                "Zip Code",
                10,
                "City",
                "Province",
                false
        );
        when(addressService.findById(id)).thenReturn(expectedAddress);

        ResponseEntity<AddressDTO> response = addressController.findById(id);

        verify(addressService, times(1)).findById(id);
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(expectedAddress).isEqualTo(response.getBody());
    }

    @Test
    public void shouldFindAllAddresses() {
        List<AddressDTO> expectedAddresses = new ArrayList<>();
        when(addressService.findAll()).thenReturn(expectedAddresses);

        ResponseEntity<List<AddressDTO>> response = addressController.findAll();

        verify(addressService, times(1)).findAll();
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(expectedAddresses).isEqualTo(response.getBody());
    }

    @Test
    public void shouldDeleteAddress() {
        Long id = 1L;

        ResponseEntity<?> response = addressController.delete(id);

        verify(addressService, times(1)).delete(id);
        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat("Address deleted successfully!").isEqualTo(response.getBody());
    }
}