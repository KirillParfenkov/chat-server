package by.parf.register;

import by.parf.bean.Registration;
import by.parf.protocol.Response;

import javax.lang.model.element.PackageElement;

/**
 * Created by parf on 7.9.16.
 */
public interface RegisterService {

    Registration register(String name);
    Registration get(String id);
    Registration find();

    Response createRegisterResponse(Registration registration);
}
