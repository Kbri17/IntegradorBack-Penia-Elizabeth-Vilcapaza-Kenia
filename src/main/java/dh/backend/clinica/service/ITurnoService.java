package dh.backend.clinica.service;

import dh.backend.clinica.dto.request.TurnoModifyDto;
import dh.backend.clinica.dto.request.TurnoRequestDto;
import dh.backend.clinica.dto.response.TurnoResponseDto;


import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);
    Optional<TurnoResponseDto> buscarPorId(Integer id);
    List<TurnoResponseDto> buscarTodos();

    void modificarTurno(TurnoModifyDto turnoModifyDto);

    void eliminarTurno(Integer id);
    Optional<TurnoResponseDto> buscarTurnosPorPaciente(String pacienteApellido);
}
