package by.parf.register;

import by.parf.bean.Registration;
import by.parf.protocol.Response;

/**
 * Created by parf on 7.9.16.
 */
public interface RegisterService {
    Registration register(String name);
    Response createRegisterResponse(Registration registration);
}
