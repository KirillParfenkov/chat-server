package by.parf.register;

import by.parf.bean.Registration;
import by.parf.protocol.*;
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
    public Registration get(String id) {
        return null;
    }

    @Override
    public Registration find() {
        return null;
    }

    @Override
    public Response processRegistration(Request request) {

        Command command = request.getHeader().getCommand();
        Response response;
        if (Command.REGISTER.equals(command)) {
            String name = request.getBody().toString();
            Registration registration = registerDao.save(name);
            response = createRegisterResponse(registration);
        } else {
            response = createErrorResponse();
        }
        return response;
    }

    private Response createRegisterResponse(Registration registration) {
        return new Response(
                new Header(Command.REGISTER),
                registration.getId(),
                Status.SUCCESS);
    }

    private Response createErrorResponse() {
        return new Response(
                new Header(Command.REGISTER),
                null,
                Status.ERROR);
    }
}
