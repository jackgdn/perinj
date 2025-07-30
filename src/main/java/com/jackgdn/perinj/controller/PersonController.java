package com.jackgdn.perinj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jackgdn.perinj.entity.Person;
import com.jackgdn.perinj.entity.dto.OtherInformationDTO;
import com.jackgdn.perinj.entity.dto.PasswordSetsDTO;
import com.jackgdn.perinj.entity.dto.PersonDTO;
import com.jackgdn.perinj.service.PersonService;

@RestController
@RequestMapping("/")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/addperson")
    public Person addPerson(@Validated @RequestBody PersonDTO personDto) {
        return personService.addPerson(personDto);
    }

    @PutMapping("/updateperson")
    public Person updatePerson(@Validated @RequestBody PersonDTO personDto) {
        return personService.updatePerson(personDto);
    }

    @PutMapping("/updatepasswordsets")
    public Person updatePersonSets(@Validated @RequestBody PasswordSetsDTO passwordSetDto) {
        return personService.updatePasswordSets(passwordSetDto);
    }

    @PutMapping("/updateotherinformation")
    public Person updateOtherInformation(@Validated @RequestBody OtherInformationDTO otherInformationDto) {
        return personService.updateOtherInformation(otherInformationDto);
    }

    @GetMapping("/getperson")
    public Person getPerson(@RequestBody String name) {
        return personService.getPerson(name);
    }

}