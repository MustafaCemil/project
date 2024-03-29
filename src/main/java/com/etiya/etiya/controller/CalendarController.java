package com.etiya.etiya.controller;

import com.etiya.etiya.dto.CalendarDto;
import com.etiya.etiya.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/takvim")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    public CalendarController(CalendarService calendarService){
        this.calendarService = calendarService;
    }

    @RequestMapping(value = "/listeleme",method = RequestMethod.GET)
    public ResponseEntity<List<CalendarDto>> listeleme(Pageable pageable){
        try {
            return new ResponseEntity(calendarService.listeleme(pageable), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET )
    public ResponseEntity<CalendarDto> tekKayit(@PathVariable("id") Long id){
        CalendarDto calendarDto =  calendarService.kayitBul(id);
        try {
            return new ResponseEntity(calendarDto,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/ekle",method = RequestMethod.POST)
    public ResponseEntity<CalendarDto> ekleme(@Valid @RequestBody CalendarDto calendarDto){
        try{
            return new ResponseEntity(calendarService.kayitEkleme(calendarDto),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/guncelleme/{id}",method = RequestMethod.PUT)
    public ResponseEntity<CalendarDto> guncelleme(@PathVariable("id") Long id, @Valid @RequestBody CalendarDto calendarDto){
        try {
            return new ResponseEntity(calendarService.guncelleme(id, calendarDto),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }

    @RequestMapping(value = "/silme/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> silme(@PathVariable("id") Long id){
        try{
            return new ResponseEntity(calendarService.silme(id),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
