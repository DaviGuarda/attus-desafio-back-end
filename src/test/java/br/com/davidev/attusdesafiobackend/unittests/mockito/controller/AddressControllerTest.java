package br.com.davidev.attusdesafiobackend.unittests.mockito.controller;

import br.com.davidev.attusdesafiobackend.controller.AddressController;
import br.com.davidev.attusdesafiobackend.dto.AddressDTO;
import br.com.davidev.attusdesafiobackend.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void shouldCreateAddress() throws Exception {
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

        mockMvc.perform(post("/api/addresses/v1/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.street_address").value("Street Address"))
                .andExpect(jsonPath("$.zip_code").value("Zip Code"))
                .andExpect(jsonPath("$.house_number").value(10))
                .andExpect(jsonPath("$.city").value("City"))
                .andExpect(jsonPath("$.province").value("Province"))
                .andExpect(jsonPath("$.defaultAddress").value(true));

        verify(addressService, times(1)).create(id, data);
    }

    @Test
    public void shouldUpdateAddress() throws Exception {
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

        when(addressService.update(idAddress, idPerson, data)).thenReturn(data);

        mockMvc.perform(put("/api/addresses/v1/{idAddress}/{idPerson}", idAddress, idPerson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.street_address").value("Street Address"))
                .andExpect(jsonPath("$.zip_code").value("Zip Code"))
                .andExpect(jsonPath("$.house_number").value(10))
                .andExpect(jsonPath("$.city").value("City"))
                .andExpect(jsonPath("$.province").value("Province"))
                .andExpect(jsonPath("$.defaultAddress").value(false));

        verify(addressService, times(1)).update(idAddress, idPerson, data);
    }

    @Test
    public void shouldSetDefaultAddress() throws Exception {
        Long idPerson = 1L;
        Long idAddress = 1L;

        doNothing().when(addressService).setDefaultAddress(idPerson, idAddress);

        mockMvc.perform(patch("/api/addresses/v1/{idPerson}/{idAddress}", idPerson, idAddress))
                .andExpect(status().isOk())
                .andExpect(content().string("Address set as default successfully!"));

        verify(addressService, times(1)).setDefaultAddress(idPerson, idAddress);
    }

    @Test
    public void shouldFindByIdAddress() throws Exception {
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

        mockMvc.perform(get("/api/addresses/v1/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.street_address").value("Street Address"))
                .andExpect(jsonPath("$.zip_code").value("Zip Code"))
                .andExpect(jsonPath("$.house_number").value(10))
                .andExpect(jsonPath("$.city").value("City"))
                .andExpect(jsonPath("$.province").value("Province"))
                .andExpect(jsonPath("$.defaultAddress").value(false));

        verify(addressService, times(1)).findById(id);
    }

    @Test
    public void shouldFindAllAddresses() throws Exception {
        List<AddressDTO> expectedAddresses = getAddressDTOS();


        when(addressService.findAll()).thenReturn(expectedAddresses);

        mockMvc.perform(get("/api/addresses/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].street_address").value("Street Address"))
                .andExpect(jsonPath("$[0].zip_code").value("Zip Code"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].street_address").value("Street Address2"))
                .andExpect(jsonPath("$[1].zip_code").value("Zip Code2"));

        verify(addressService, times(1)).findAll();
    }

    @Test
    public void shouldDeleteAddress() throws Exception {
        Long id = 1L;

        doNothing().when(addressService).delete(id);

        mockMvc.perform(delete("/api/addresses/v1/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Address deleted successfully!"));

        verify(addressService, times(1)).delete(id);
    }

    private static List<AddressDTO> getAddressDTOS() {
        List<AddressDTO> expectedAddresses = new ArrayList<>();

        AddressDTO data1 = new AddressDTO(
                1L,
                "Street Address",
                "Zip Code",
                10,
                "City",
                "Province",
                false
        );

        expectedAddresses.add(data1);

        AddressDTO data2 = new AddressDTO(
                2L,
                "Street Address2",
                "Zip Code2",
                10,
                "City2",
                "Province2",
                false
        );

        expectedAddresses.add(data2);
        return expectedAddresses;
    }
}