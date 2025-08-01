package com.jackgdn.perinj.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackgdn.perinj.entity.Information;
import com.jackgdn.perinj.entity.PasswordSet;
import com.jackgdn.perinj.entity.Person;
import com.jackgdn.perinj.entity.dto.InformationDTO;
import com.jackgdn.perinj.entity.dto.OtherInformationDTO;
import com.jackgdn.perinj.entity.dto.PasswordSetDTO;
import com.jackgdn.perinj.entity.dto.PasswordSetsDTO;
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

    private void SetNonNullList(Consumer<List<String>> setter, List<String> value) {
        setter.accept((value == null) ? List.of() : value);
    }

    public Person addPerson(PersonDTO personDto) {
        perinjRepository.findById(personDto.getName())
                .ifPresent(person -> {
                    throw new RuntimeException("Person already exists with name: " + personDto.getName());
                });
        Person personEntity = new Person();
        BeanUtils.copyProperties(personDto, personEntity);
        SetNonNullList(personEntity::setPhoneNumbers, personDto.getPhoneNumbers());
        SetNonNullList(personEntity::setEmailAddresses, personDto.getEmailAddresses());
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

    public Person updatePasswordSets(PasswordSetsDTO passwordSetsDto) {
        Person personEntity = perinjRepository.findById(passwordSetsDto.getName())
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + passwordSetsDto.getName()));
        personEntity.getPasswordSets()
                .addAll(Optional.ofNullable(passwordSetsDto.getPasswordSets()
                        .stream()
                        .map(passwordSetDto -> {
                            PasswordSet passwordSetEntity = new PasswordSet();
                            BeanUtils.copyProperties(passwordSetDto, passwordSetEntity);
                            return passwordSetEntity;
                        })
                        .collect(Collectors.toList()))
                        .orElse(List.of()));
        return perinjRepository.save(personEntity);
    }

    public Person updateOtherInformation(OtherInformationDTO otherInformationDto) {
        Person personEntity = perinjRepository.findById(otherInformationDto.getName())
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + otherInformationDto.getName()));
        personEntity.getOtherInformation()
                .addAll(Optional.ofNullable(otherInformationDto.getOtherInformation()
                        .stream()
                        .map(InformationDto -> {
                            Information informationEntity = new Information();
                            BeanUtils.copyProperties(InformationDto, informationEntity);
                            return informationEntity;
                        })
                        .collect(Collectors.toList()))
                        .orElse(List.of()));
        return perinjRepository.save(personEntity);
    }

    public Person getPerson(String name) {
        return perinjRepository.findById(name)
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + name));
    }

    public PasswordSetsDTO getPasswordSets(String name) {
        Person person = perinjRepository.findById(name)
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + name));
        PasswordSetsDTO passwordSetsDto = new PasswordSetsDTO();
        passwordSetsDto.setName(name);
        passwordSetsDto.setPasswordSets(person.getPasswordSets()
                .stream()
                .map(passwordSetEntity -> {
                    PasswordSetDTO passwordSetDto = new PasswordSetDTO();
                    BeanUtils.copyProperties(passwordSetEntity, passwordSetDto);
                    return passwordSetDto;
                })
                .collect(Collectors.toList()));
        return passwordSetsDto;
    }

    public OtherInformationDTO getOtherInformation(String name) {
        Person person = perinjRepository.findById(name)
                .orElseThrow(
                        () -> new RuntimeException("Person not found with name: " + name));
        OtherInformationDTO otherInformationDto = new OtherInformationDTO();
        otherInformationDto.setName(name);
        otherInformationDto.setOtherInformation(person.getOtherInformation()
                .stream()
                .map(informationEntity -> {
                    InformationDTO informationDto = new InformationDTO();
                    BeanUtils.copyProperties(informationEntity, informationDto);
                    return informationDto;
                })
                .collect(Collectors.toList()));
        return otherInformationDto;
    }

}
