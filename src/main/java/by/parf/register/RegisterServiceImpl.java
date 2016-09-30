package by.parf.register;

import by.parf.bean.Registration;
import by.parf.protocol.Command;
import by.parf.protocol.Header;
import by.parf.protocol.Response;
import by.parf.protocol.Status;
import by.parf.register.dao.RegisterDao;
import by.parf.register.dao.RegisterDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by parf on 7.9.16.
 */
public class RegisterServiceImpl implements RegisterService {

    private RegisterDao registerDao;

    public RegisterServiceImpl() {
        this.registerDao = new RegisterDaoImpl();
    }

    @Override
    public Registration register(String name) {
        Registration registration = registerDao.save(name);
        return registration;
    }

    @Override
    public Registration get(String id) {
        return null;
    }

    @Override
    public Registration find() {
        return null;
    }

    @Override
    public Response createRegisterResponse(Registration registration) {
        return new Response(
                new Header(Command.REGISTER),
                registration.getId(),
                Status.SUCCESS);
    }
}
