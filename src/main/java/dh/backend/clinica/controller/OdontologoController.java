package dh.backend.clinica.controller;

import dh.backend.clinica.entity.Odontologo;
import dh.backend.clinica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        // aca jackson convierte el objeto JSON a un objeto Java "paciente"
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>>  buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
    @PutMapping("/modificar")
    public ResponseEntity<String>  modificarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if(odontologoEncontrado.isPresent()){
            odontologoService.modificarOdontologo(odontologo);
            String jsonResponse = "{\"mensaje\": \"El odontologo fue modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("{\"mensaje\": \"El odontologo fue eliminado\"}");
    }

    @GetMapping("/buscarApellidoNombre/{apellido}/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarPorApellidoONombre(@PathVariable String apellido, @PathVariable String nombre) {
        List<Odontologo> odontologos = odontologoService.buscarPorApellidoONombre(apellido, nombre);
        if (odontologos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.ok(odontologos);
        }
    }

    @GetMapping("/buscarApellidoQueComienzaCon/{prefix}")
    public ResponseEntity<List<Odontologo>> buscarPorApellidoQueComienzaCon(@PathVariable String prefix) {
        List<Odontologo> odontologos = odontologoService.buscarPorApellidoQueComienzaCon(prefix);

        if (odontologos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(odontologos);
        }

    }

}



