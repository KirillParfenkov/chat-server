package by.parf.register;

import by.parf.bean.Registration;
import by.parf.protocol.Command;
import by.parf.protocol.Header;
import by.parf.protocol.Response;
import by.parf.protocol.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by parf on 7.9.16.
 */
public class RegisterServiceImpl implements RegisterService {

    private List<Registration> registrations;

    public RegisterServiceImpl() {
        this.registrations = new ArrayList<>();
    }

    @Override
    public Registration register(String name) {
        Registration registration = new Registration(UUID.randomUUID().toString(), name);
        registrations.add(registration);
        return registration;
    }

    @Override
    public Response createRegisterResponse(Registration registration) {
        return new Response(
                new Header(Command.REGISTER),
                registration.getId(),
                Status.SUCCESS);
    }
}
