package com.epam.project.web.controller;

import com.epam.project.PersonModel;
import com.epam.project.web.converter.PersonConverter;
import com.epam.project.entity.Person;
import com.epam.project.service.PersonService;
import com.epam.project.web.dto.MessageResponseDto;
import com.epam.project.web.handler.BindingResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@Consumes("application/json")
//@Produces("application/json")
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> udate (
            @PathVariable
                    Long id,
            @RequestBody
            @Valid
                    PersonModel personModel, BindingResult bindingResult) {
        MessageResponseDto messageResponseDto =
                BindingResultHandler.validEntity(bindingResult);
        if (null != messageResponseDto) {
            return new ResponseEntity<MessageResponseDto>(
                    messageResponseDto, HttpStatus.BAD_REQUEST);
        }
        personService.update(PersonConverter.convertToPerson(personModel));
        messageResponseDto = new MessageResponseDto();
        messageResponseDto.setMsg("person was update");
        messageResponseDto.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(messageResponseDto, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<PersonModel>> findAll() {
        List<Person> persons = personService.findAll();
        List<PersonModel> personModelList = persons.stream().map(PersonConverter::convertToPersonModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(personModelList);
    }
}
