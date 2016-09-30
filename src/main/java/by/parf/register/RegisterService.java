package by.parf.register;

import by.parf.bean.Registration;
import by.parf.protocol.Request;
import by.parf.protocol.Response;

import javax.lang.model.element.PackageElement;

/**
 * Created by parf on 7.9.16.
 */
public interface RegisterService {

    Registration get(String id);
    Registration find();

    Response processRegistration(Request request);
}
