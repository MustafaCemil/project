package com.etiya.etiya.controller;

import com.etiya.etiya.dto.CustomersDto;
import com.etiya.etiya.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/musteri")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    public CustomersController(CustomersService customersService){
        this.customersService = customersService;
    }

    @RequestMapping(value = "/listeleme",method = RequestMethod.GET)
    public ResponseEntity<List<CustomersDto>> listeleme(Pageable pageable){
        try {
            return new ResponseEntity(customersService.listeleme(pageable), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET )
    public ResponseEntity<CustomersDto> tekKayit(@PathVariable("id") Long id){
        CustomersDto customersDto =  customersService.kayitBul(id);
        try {
            return new ResponseEntity(customersDto,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/ekle",method = RequestMethod.POST)
    public ResponseEntity<CustomersDto> ekleme(@Valid @RequestBody CustomersDto customersDto){
        try{
            return new ResponseEntity(customersService.kayitEkleme(customersDto),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/guncelleme/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CustomersDto> guncelleme(@PathVariable("id") Long id, @Valid @RequestBody CustomersDto customersDto){
        try {
            return new ResponseEntity(customersService.guncelleme(id,customersDto),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/silme/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> silme(@PathVariable("id") Long id){
        try{
            return new ResponseEntity(customersService.silme(id),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
