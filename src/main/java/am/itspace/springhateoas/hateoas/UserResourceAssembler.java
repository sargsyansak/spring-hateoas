package am.itspace.springhateoas.hateoas;

import am.itspace.springhateoas.controller.UserController;
import am.itspace.springhateoas.model.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
        UserResource userResource = createResourceWithId(user.getId(), user);
        userResource.setUser(user);
        return userResource;
    }
}
