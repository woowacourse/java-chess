package chess.service;

import chess.domain.GameResult;
import chess.persistence.dto.GameSessionDto;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    GameSessionDto createSession(GameSessionDto gameSessionDto);

    Optional<GameSessionDto> findSessionById(long id);

    Optional<GameSessionDto> findSessionByTitle(String title);

    List<GameSessionDto> findLatestSessions(int limit);

    void updateSessionState(long id, GameResult stateTo);
}
