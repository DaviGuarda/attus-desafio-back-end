package br.com.davidev.attusdesafiobackend.config.unittests.converter;

import br.com.davidev.attusdesafiobackend.config.unittests.converter.mocks.MockAddress;
import br.com.davidev.attusdesafiobackend.model.converter.AddressConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AddressConverterTest {

    MockAddress inputObject;

    @BeforeEach
    public void setUp(){
        inputObject = new MockAddress();
    }

    @Test
    @Order(1)
    public void shouldParseEntityToDTO(){
        var addressEntity = inputObject.mockEntity(1);
        var addressDTO = AddressConverter.toDTO(addressEntity);

        assertThat(addressDTO.id()).isEqualTo(1L);
        assertThat(addressDTO.streetAddress()).isEqualTo("Street Address1");
        assertThat(addressDTO.zipCode()).isEqualTo("Zip Code1");
        assertThat(addressDTO.houseNumber()).isEqualTo(10);
        assertThat(addressDTO.city()).isEqualTo("City1");
        assertThat(addressDTO.province()).isEqualTo("Province1");
        assertThat(addressDTO.defaultAddress()).isEqualTo(false);
    }

    @Test
    @Order(2)
    public void shouldParseEntityListToDTOList(){
        var outPutListEntity = inputObject.mockEntityList();
        var outPutListDTO = AddressConverter.toDTOList(outPutListEntity);

        var outPutOne = outPutListDTO.get(1);

        assertThat(outPutOne.id()).isEqualTo(1L);
        assertThat(outPutOne.streetAddress()).isEqualTo("Street Address1");
        assertThat(outPutOne.zipCode()).isEqualTo("Zip Code1");
        assertThat(outPutOne.houseNumber()).isEqualTo(10);
        assertThat(outPutOne.city()).isEqualTo("City1");
        assertThat(outPutOne.province()).isEqualTo("Province1");
        assertThat(outPutOne.defaultAddress()).isEqualTo(false);

        var outPutFive = outPutListDTO.get(5);

        assertThat(outPutFive.id()).isEqualTo(5L);
        assertThat(outPutFive.streetAddress()).isEqualTo("Street Address5");
        assertThat(outPutFive.zipCode()).isEqualTo("Zip Code5");
        assertThat(outPutFive.houseNumber()).isEqualTo(10);
        assertThat(outPutFive.city()).isEqualTo("City5");
        assertThat(outPutFive.province()).isEqualTo("Province5");
        assertThat(outPutFive.defaultAddress()).isEqualTo(false);

        var outPutSeven = outPutListDTO.get(7);

        assertThat(outPutSeven.id()).isEqualTo(7L);
        assertThat(outPutSeven.streetAddress()).isEqualTo("Street Address7");
        assertThat(outPutSeven.zipCode()).isEqualTo("Zip Code7");
        assertThat(outPutSeven.houseNumber()).isEqualTo(10);
        assertThat(outPutSeven.city()).isEqualTo("City7");
        assertThat(outPutSeven.province()).isEqualTo("Province7");
        assertThat(outPutSeven.defaultAddress()).isEqualTo(false);

    }

    @Test
    @Order(3)
    public void shouldParseDTOToEntity(){
        var addressDTO = inputObject.mockDTO(1);
        var addressEntity = AddressConverter.toEntity(addressDTO);

        assertThat(addressEntity.getId()).isEqualTo(1L);
        assertThat(addressEntity.getStreetAddress()).isEqualTo("Street Address1");
        assertThat(addressEntity.getZipCode()).isEqualTo("Zip Code1");
        assertThat(addressEntity.getHouseNumber()).isEqualTo(10);
        assertThat(addressEntity.getCity()).isEqualTo("City1");
        assertThat(addressEntity.getProvince()).isEqualTo("Province1");
        assertThat(addressEntity.getDefaultAddress()).isEqualTo(false);
    }

    @Test
    @Order(4)
    public void shouldParseDTOListToEntityList(){
        var outPutListDTO = inputObject.mockDTOList();
        var outPutListEntity = AddressConverter.toEntityList(outPutListDTO);

        var outPutOne = outPutListEntity.get(1);

        assertThat(outPutOne.getId()).isEqualTo(1L);
        assertThat(outPutOne.getStreetAddress()).isEqualTo("Street Address1");
        assertThat(outPutOne.getZipCode()).isEqualTo("Zip Code1");
        assertThat(outPutOne.getHouseNumber()).isEqualTo(10);
        assertThat(outPutOne.getCity()).isEqualTo("City1");
        assertThat(outPutOne.getProvince()).isEqualTo("Province1");
        assertThat(outPutOne.getDefaultAddress()).isEqualTo(false);

        var outPutFive = outPutListEntity.get(5);

        assertThat(outPutFive.getId()).isEqualTo(5L);
        assertThat(outPutFive.getStreetAddress()).isEqualTo("Street Address5");
        assertThat(outPutFive.getZipCode()).isEqualTo("Zip Code5");
        assertThat(outPutFive.getHouseNumber()).isEqualTo(10);
        assertThat(outPutFive.getCity()).isEqualTo("City5");
        assertThat(outPutFive.getProvince()).isEqualTo("Province5");
        assertThat(outPutFive.getDefaultAddress()).isEqualTo(false);

        var outPutSeven = outPutListEntity.get(7);

        assertThat(outPutSeven.getId()).isEqualTo(7L);
        assertThat(outPutSeven.getStreetAddress()).isEqualTo("Street Address7");
        assertThat(outPutSeven.getZipCode()).isEqualTo("Zip Code7");
        assertThat(outPutSeven.getHouseNumber()).isEqualTo(10);
        assertThat(outPutSeven.getCity()).isEqualTo("City7");
        assertThat(outPutSeven.getProvince()).isEqualTo("Province7");
        assertThat(outPutSeven.getDefaultAddress()).isEqualTo(false);
    }

}
