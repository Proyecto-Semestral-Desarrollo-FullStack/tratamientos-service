package com.veterinaria.tratamientos.service;

import com.veterinaria.tratamientos.dto.TratamientoRequest;
import com.veterinaria.tratamientos.dto.TratamientoResponse;
import com.veterinaria.tratamientos.exception.ResourceNotFoundException;
import com.veterinaria.tratamientos.model.Tratamiento;
import com.veterinaria.tratamientos.repository.TratamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final CitaClientService citaClientService;

    public TratamientoResponse crear(TratamientoRequest request) {
        if (!citaClientService.existeCita(request.getCitaId())) {
            throw new IllegalArgumentException(
                    "La cita con id " + request.getCitaId() + " no existe");
        }
        Tratamiento t = Tratamiento.builder()
                .descripcion(request.getDescripcion())
                .indicaciones(request.getIndicaciones())
                .citaId(request.getCitaId())
                .build();

        return toResponse(tratamientoRepository.save(t));
    }

    public List<TratamientoResponse> listarTodos() {
        return tratamientoRepository.findAll().stream()
                .map(this::toResponse).toList();
    }

    public TratamientoResponse buscarPorId(Long id) {
        return toResponse(findOrThrow(id));
    }

    public List<TratamientoResponse> listarPorCita(Long citaId) {
        return tratamientoRepository.findByCitaId(citaId)
                .stream().map(this::toResponse).toList();
    }

    public TratamientoResponse actualizar(Long id, TratamientoRequest request) {
        Tratamiento t = findOrThrow(id);
        t.setDescripcion(request.getDescripcion());
        t.setIndicaciones(request.getIndicaciones());
        return toResponse(tratamientoRepository.save(t));
    }

    public void eliminar(Long id) {
        if (!tratamientoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tratamiento no encontrado con id: " + id);
        }
        tratamientoRepository.deleteById(id);
    }

    private Tratamiento findOrThrow(Long id) {
        return tratamientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Tratamiento no encontrado con id: " + id));
    }

    private TratamientoResponse toResponse(Tratamiento t) {
        return TratamientoResponse.builder()
                .id(t.getId())
                .descripcion(t.getDescripcion())
                .indicaciones(t.getIndicaciones())
                .citaId(t.getCitaId())
                .build();
    }
}