package com.jackgdn.perinj.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackgdn.perinj.entity.PasswordSet;
import com.jackgdn.perinj.entity.Person;
import com.jackgdn.perinj.entity.dto.OtherInformationDTO;
import com.jackgdn.perinj.entity.dto.PasswordSetDTO;
import com.jackgdn.perinj.entity.dto.PersonDTO;
import com.jackgdn.perinj.repository.PerinjRepository;

@Service
public class PersonService {
    @Autowired
    PerinjRepository perinjRepository;

    private void SetValidString(Consumer<String> setter, String value) {
        if (value != null && !value.trim().isEmpty()) {
            setter.accept(value);
        }
    }

    public Person addPerson(PersonDTO personDto) {
        Person personEntity = new Person();
        BeanUtils.copyProperties(personDto, personEntity);
        return perinjRepository.save(personEntity);
    }

    public Person updatePerson(PersonDTO personDto) {
        Person personEntity = perinjRepository.findById(personDto.getName())
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + personDto.getName()));
        SetValidString(personEntity::setDateOfBirth, personDto.getDateOfBirth());
        personEntity.getPhoneNumbers().addAll(Optional.ofNullable(personDto.getPhoneNumbers()).orElse(List.of()));
        personEntity.getEmailAddresses().addAll(Optional.ofNullable(personDto.getEmailAddresses()).orElse(List.of()));
        SetValidString(personEntity::setQq, personDto.getQq());
        SetValidString(personEntity::setStudentID, personDto.getStudentID());
        SetValidString(personEntity::setId, personDto.getId());
        SetValidString(personEntity::setAddress, personDto.getAddress());
        return perinjRepository.save(personEntity);
    }

    public Person updatePasswordSet(PasswordSetDTO passwordSetDto) {
        Person personEntity = perinjRepository.findById(passwordSetDto.getName())
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + passwordSetDto.getName()));
        personEntity.getPasswordSets().addAll(passwordSetDto.getPasswordSets()
                .stream()
                .map(passwordSetSubDto -> {
                    PasswordSet passwordSet = new PasswordSet();
                    BeanUtils.copyProperties(passwordSetSubDto, passwordSet);
                    return passwordSet;
                })
                .collect(Collectors.toList()));
        return perinjRepository.save(personEntity);
    }

    public Person updateOtherInformation(OtherInformationDTO otherInformationDTO) {
        Person personEntity = perinjRepository.findById(otherInformationDTO.getName())
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + otherInformationDTO.getName()));
        personEntity.getOtherInformation().putAll(otherInformationDTO.getOtherInformation());
        return perinjRepository.save(personEntity);
    }

    public Person getPerson(String name) {
        return perinjRepository.findById(name)
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + name));
    }

}
