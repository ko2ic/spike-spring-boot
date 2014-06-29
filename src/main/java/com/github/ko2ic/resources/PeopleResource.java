package com.github.ko2ic.resources;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.ko2ic.core.Person;
import com.github.ko2ic.db.PeopleRepository;
import com.google.common.base.Optional;

@RestController
@RequestMapping(value = "/people")
public class PeopleResource {

    @Autowired
    private PeopleRepository peopleRepository;

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public @ResponseBody
    Person createPerson(@RequestBody Person people) {
        return peopleRepository.create(people);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public List<Person> listPeople() {
        return peopleRepository.findAll();
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    @Transactional
    public Person getPerson(@PathVariable("personId") Long personId) {
        final Optional<Person> person = peopleRepository.findById(personId);
        if (!person.isPresent()) {
            throw new PersonNotFoundException("Not Found Person");
        }
        return person.get();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found Person")
    @ExceptionHandler(PersonNotFoundException.class)
    public void notfound() {
        // Nothing to do
    }

    private static class PersonNotFoundException extends RuntimeException {
        public PersonNotFoundException(String message) {
            super(message);
        }
    }
}
