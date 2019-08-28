package am.itspace.springhateoas.controller;

import am.itspace.springhateoas.hateoas.UserResource;
import am.itspace.springhateoas.hateoas.UserResourceAssembler;
import am.itspace.springhateoas.model.User;
import am.itspace.springhateoas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @Autowired
    private PagedResourcesAssembler<User> pagedResourcesAssembler;

    @GetMapping("/resource")
    public ResponseEntity allUsers() {
        List<UserResource> userResourceList = userRepository.findAll()
                .stream()
                .map(user -> userResourceAssembler.toResource(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResourceList);
    }

    @GetMapping("/page")
    public ResponseEntity pageUsers(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        PagedResources<UserResource> pagedResources = pagedResourcesAssembler.toResource(userPage, userResourceAssembler);
        return ResponseEntity.ok(pagedResources);
    }
    @PostMapping("/save")
    @Transactional
    public ResponseEntity addUser() {
        userRepository.save(User
                .builder()
                .name("Kane")
                .surname("Smith")
                .build());
        throw new RuntimeException("");
    }
}
