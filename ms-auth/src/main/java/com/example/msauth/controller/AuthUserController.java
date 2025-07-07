package com.example.msauth.controller;

import com.example.msauth.dto.AuthUserDto; // DTO que contiene los datos de un usuario para el login y creación
import com.example.msauth.entity.AuthUser; // Entidad que representa a un usuario
import com.example.msauth.entity.TokenDto; // DTO que contiene el token generado al autenticar un usuario
import com.example.msauth.service.AuthUserService; // Servicio para manejar la lógica de negocio relacionada con los usuarios
import org.springframework.beans.factory.annotation.Autowired; // Inyección de dependencias de Spring
import org.springframework.http.ResponseEntity; // Tipo de respuesta que se devolverá
import org.springframework.web.bind.annotation.*; // Anotaciones de Spring para los endpoints de la API

@RestController // Define que esta clase es un controlador de una API REST
@RequestMapping("/auth") // Define el prefijo para todas las rutas de este controlador
public class AuthUserController {

    @Autowired
    AuthUserService authUserService; // Inyección de servicio para manejar la lógica de negocio

    // Endpoint para iniciar sesión (login)
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        // Llama al servicio de autenticación y obtiene el token
        TokenDto tokenDto = authUserService.login(authUserDto);

        // Si el token es nulo, significa que la autenticación falló, entonces devuelve un error 400
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();

        // Si la autenticación fue exitosa, devuelve el token en la respuesta con el código 200 OK
        return ResponseEntity.ok(tokenDto);
    }

    // Endpoint para validar un token
    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        // Llama al servicio de validación de token y obtiene el resultado
        TokenDto tokenDto = authUserService.validate(token);

        // Si la validación falla, devuelve un error 400
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();

        // Si el token es válido, devuelve el token en la respuesta con el código 200 OK
        return ResponseEntity.ok(tokenDto);
    }

    // Endpoint para crear un nuevo usuario
    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody AuthUserDto authUserDto) {
        // Llama al servicio para guardar el nuevo usuario
        AuthUser authUser = authUserService.save(authUserDto);

        // Si no se pudo crear el usuario, devuelve un error 400
        if (authUser == null)
            return ResponseEntity.badRequest().build();

        // Si la creación fue exitosa, devuelve los datos del usuario en la respuesta con el código 200 OK
        return ResponseEntity.ok(authUser);
    }

    // Endpoint para obtener el ID del usuario a partir del token
    @GetMapping("/userId")
    public ResponseEntity<Integer> getUserId(@RequestHeader("Authorization") String token) {
        // Eliminar el prefijo "Bearer " del token si está presente
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remueve el prefijo "Bearer "
        }

        // Llama al servicio para obtener el ID del usuario desde el token
        Integer userId = authUserService.getUserIdFromToken(token);

        // Si no se pudo obtener el ID del usuario, devuelve un error 400
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        // Devuelve el ID del usuario en la respuesta con el código 200 OK
        return ResponseEntity.ok(userId);
    }

    // Endpoint para obtener el nombre del usuario a partir del token
    @GetMapping("/userName")
    public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String token) {
        // Eliminar el prefijo "Bearer " del token si está presente
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remueve el prefijo "Bearer "
        }

        // Llama al servicio para obtener el nombre de usuario desde el token
        String userName = authUserService.getUserNameFromToken(token);

        // Si no se pudo obtener el nombre del usuario, devuelve un error 400
        if (userName == null) {
            return ResponseEntity.badRequest().build();
        }

        // Devuelve el nombre del usuario en la respuesta con el código 200 OK
        return ResponseEntity.ok(userName);
    }
}
