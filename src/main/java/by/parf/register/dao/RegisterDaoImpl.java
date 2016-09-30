package by.parf.register.dao;

import by.parf.bean.Registration;
import by.parf.register.exeption.EmptyResultException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by parf on 30.9.16.
 */
public class RegisterDaoImpl implements RegisterDao {

    private List<Registration> registrations;

    public RegisterDaoImpl() {
        this.registrations = new ArrayList<>();
    }

    @Override
    public Registration save(String name) {
        Registration registration = new Registration(UUID.randomUUID().toString(), name);
        registrations.add(registration);
        return registration;
    }

    @Override
    public Registration get(String id) throws EmptyResultException {

        if (registrations != null && id != null) {
            return registrations.stream()
                    .filter(r -> id.equals(r.getId()))
                    .findFirst()
                    .orElseThrow(() -> new EmptyResultException());
        }

        /* Never returns null */
        return null;
    }

    @Override
    public List<Registration> find() {
        return registrations.stream().collect(Collectors.toList());
    }
}