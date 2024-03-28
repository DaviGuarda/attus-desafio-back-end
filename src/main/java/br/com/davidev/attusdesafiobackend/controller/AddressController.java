package br.com.davidev.attusdesafiobackend.controller;

import br.com.davidev.attusdesafiobackend.dto.AddressDTO;
import br.com.davidev.attusdesafiobackend.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/address/v1")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/create/{id}")
    public ResponseEntity<AddressDTO> create(@PathVariable(value = "id") Long id, @RequestBody @Valid AddressDTO data) {
        var person = addressService.create(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @PutMapping("/update/{idAddress}/{idPerson}")
    public ResponseEntity<AddressDTO> update(@PathVariable(value = "idAddress") Long idAddress, @PathVariable(value = "idPerson") Long idPerson, @RequestBody @Valid AddressDTO data) {
        var person = addressService.update(idAddress, idPerson, data);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @PatchMapping("/default/{idPerson}/{idAddress}")
    public ResponseEntity<?> setDefaultAddress(@PathVariable(value = "idPerson") Long idPerson, @PathVariable(value = "idAddress") Long idAddress) {
        addressService.setDefaultAddress(idPerson, idAddress);
        return ResponseEntity.ok("Address set as default successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable(value = "id") Long id) {
        var person = addressService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll() {
        var people = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(people);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        addressService.delete(id);
        return ResponseEntity.ok("Address deleted successfully!");
    }
}
