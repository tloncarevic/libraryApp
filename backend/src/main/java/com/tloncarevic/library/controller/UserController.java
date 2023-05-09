package com.tloncarevic.library.controller;

import com.tloncarevic.library.model.User;
import com.tloncarevic.library.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @CrossOrigin(origins = "*")
    public List<User> getUsers() {

        System.out.println("[GET] /users");
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        System.out.println("[GET] /users/"+id);
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException {
        System.out.println("[POST] /users");
        User savedUser = userRepository.save(user);
        return ResponseEntity.created(new URI("/users/" + savedUser.getId())).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        System.out.println("[PUT] /users");
        User currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setBook(user.getBook());

        currentUser = userRepository.save(user);

        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        System.out.println("[DELETE] /users/"+id);
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
