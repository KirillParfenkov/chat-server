package by.parf.register.dao;

import by.parf.bean.Registration;
import by.parf.register.exeption.EmptyResultException;

import java.util.List;

/**
 * Created by parf on 30.9.16.
 */
public interface RegisterDao {

    Registration save(String name);
    Registration get(String id) throws EmptyResultException;
    List<Registration> find();

}
