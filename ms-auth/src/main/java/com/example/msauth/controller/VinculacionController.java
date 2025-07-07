package com.example.msauth.controller;

import com.example.msauth.entity.AuthUser; // Entidad que representa los usuarios
import com.example.msauth.service.AuthUserService; // Servicio para manejar las operaciones de usuario
import com.example.msauth.service.VinculacionService; // Servicio para la lógica de vinculación de usuarios
import org.springframework.beans.factory.annotation.Autowired; // Para la inyección automática de dependencias
import org.springframework.http.ResponseEntity; // Para definir las respuestas HTTP
import org.springframework.web.bind.annotation.*; // Anotaciones de Spring para crear los endpoints REST

import java.util.List; // Para manejar listas de usuarios

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/usuario") // Define la ruta base para las operaciones relacionadas con los usuarios
public class VinculacionController {

    // Inyección de las dependencias para los servicios de vinculación y de usuarios
    @Autowired
    private VinculacionService vinculacionService;
    @Autowired
    private AuthUserService authUserService;

    // Método para vincular un usuario con un cliente
    @PostMapping("/vincularCliente/{userId}/{clienteId}")
    public ResponseEntity<AuthUser> vincularCliente(@PathVariable Integer userId, @PathVariable Integer clienteId) {
        // Llama al servicio de vinculación para asociar el cliente al usuario
        AuthUser authUser = vinculacionService.vincularCliente(userId, clienteId);

        // Si el usuario o el cliente no existen, se devuelve un error 400 (Bad Request)
        if (authUser == null) {
            return ResponseEntity.badRequest().build(); // Usuario o Cliente no encontrado
        }

        // Si la vinculación es exitosa, se devuelve el usuario actualizado
        return ResponseEntity.ok(authUser);
    }

    // Método para vincular un usuario con un vendedor
    @PostMapping("/vincularVendedor/{userId}/{vendedorId}")
    public ResponseEntity<AuthUser> vincularVendedor(@PathVariable Integer userId, @PathVariable Integer vendedorId) {
        // Llama al servicio de vinculación para asociar el vendedor al usuario
        AuthUser authUser = vinculacionService.vincularVendedor(userId, vendedorId);

        // Si el usuario o el vendedor no existen, se devuelve un error 400 (Bad Request)
        if (authUser == null) {
            return ResponseEntity.badRequest().build(); // Usuario o Vendedor no encontrado
        }

        // Si la vinculación es exitosa, se devuelve el usuario actualizado
        return ResponseEntity.ok(authUser);
    }

    // Método para obtener y listar todos los usuarios registrados
    @GetMapping("/listar")
    public ResponseEntity<List<AuthUser>> listarUsuarios() {
        // Llama al servicio de usuarios para obtener todos los usuarios
        List<AuthUser> usuarios = authUserService.listarUsuarios();

        // Devuelve la lista de usuarios con el código de estado 200 OK
        return ResponseEntity.ok(usuarios);
    }

    // Método para eliminar un usuario por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        // Llama al servicio de usuarios para eliminar el usuario con el ID proporcionado
        boolean eliminado = authUserService.eliminarUsuario(id);

        // Si no se pudo eliminar el usuario (porque no existe), se devuelve un error 404 (Not Found)
        if (!eliminado) {
            return ResponseEntity.notFound().build(); // Usuario no encontrado
        }

        // Si el usuario fue eliminado correctamente, se devuelve el código 204 No Content
        return ResponseEntity.noContent().build();
    }
}
