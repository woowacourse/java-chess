package chess.service;

import chess.persistence.dto.GameSessionDto;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    GameSessionDto createRoom(GameSessionDto gameSessionDto);

    Optional<GameSessionDto> findRoomById(long id);

    Optional<GameSessionDto> findRoomByTitle(String title);

    List<GameSessionDto> findLatestRooms(int limit);
}
