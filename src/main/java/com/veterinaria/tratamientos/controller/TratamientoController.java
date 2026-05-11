package com.veterinaria.tratamientos.controller;

import com.veterinaria.tratamientos.dto.TratamientoRequest;
import com.veterinaria.tratamientos.dto.TratamientoResponse;
import com.veterinaria.tratamientos.service.TratamientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos")
@RequiredArgsConstructor
public class TratamientoController {

    private final TratamientoService tratamientoService;

    @PostMapping
    public ResponseEntity<TratamientoResponse> crear(@Valid @RequestBody TratamientoRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamientoService.crear(req));
    }

    @GetMapping
    public ResponseEntity<List<TratamientoResponse>> listar() {
        return ResponseEntity.ok(tratamientoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamientoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tratamientoService.buscarPorId(id));
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<TratamientoResponse>> listarPorCita(@PathVariable Long citaId) {
        return ResponseEntity.ok(tratamientoService.listarPorCita(citaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamientoResponse> actualizar(@PathVariable Long id,
                                                          @Valid @RequestBody TratamientoRequest req) {
        return ResponseEntity.ok(tratamientoService.actualizar(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tratamientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}