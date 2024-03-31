package br.com.davidev.attusdesafiobackend.config.unittests.converter.mocks;

import br.com.davidev.attusdesafiobackend.dto.AddressDTO;
import br.com.davidev.attusdesafiobackend.model.Address;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockAddress {

    private final MockPerson mockPerson = new MockPerson();

    public Address mockEntity(){
        return mockEntity(0);
    }

    public AddressDTO mockDTO(){
        return mockDTO(0);
    }

    public List<Address> mockEntityList(){
        return IntStream.range(0, 10).mapToObj(this::mockEntity).collect(Collectors.toList());
    }

    public List<AddressDTO> mockDTOList(){
        return IntStream.range(0, 10).mapToObj(this::mockDTO).collect(Collectors.toList());
    }

    public Address mockEntity(Integer number){
        var person = mockPerson.mockEntity(number);
        return Address.builder()
                .id(number.longValue())
                .streetAddress("Street Address" + number)
                .zipCode("Zip Code" + number)
                .houseNumber(10)
                .city("City" + number)
                .province("Province" + number)
                .defaultAddress(false)
                .person(person)
                .build();
    }

    public AddressDTO mockDTO(Integer number) {
        return new AddressDTO(
                number.longValue(),
                "Street Address" + number,
                "Zip Code" + number,
                10,
                "City" + number,
                "Province" + number,
                false
        );

    }
}
