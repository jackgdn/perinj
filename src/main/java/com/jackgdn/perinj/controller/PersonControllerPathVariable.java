package com.jackgdn.perinj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jackgdn.perinj.entity.Person;
import com.jackgdn.perinj.entity.dto.OtherInformationDTO;
import com.jackgdn.perinj.entity.dto.PasswordSetsDTO;
import com.jackgdn.perinj.service.PersonService;

@RestController
@RequestMapping("/")
public class PersonControllerPathVariable {
    @Autowired
    PersonService personService;

    @GetMapping("/getperson/{name}")
    public Person getPerson(@PathVariable String name) {
        return personService.getPerson(name);
    }

    @GetMapping("/getpasswordsets/{name}")
    public PasswordSetsDTO getPasswordSets(@PathVariable String name) {
        return personService.getPasswordSets(name);
    }

    @GetMapping("/getotherinformation/{name}")
    public OtherInformationDTO getOtherInformation(@PathVariable String name) {
        return personService.getOtherInformation(name);
    }
}