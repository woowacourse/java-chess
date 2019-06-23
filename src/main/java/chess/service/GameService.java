package chess.service;

import chess.domain.CoordinatePair;
import chess.domain.GameResult;
import chess.persistence.dto.BoardStateDto;
import chess.service.dto.CoordinatePairDto;

import java.util.List;

public interface GameService {
    List<BoardStateDto> findBoardStatesByRoomId(long sessionId);

    GameResult movePiece(CoordinatePair from, CoordinatePair to, long sessionId);

    List<CoordinatePairDto> findMovableCoordinates(long sessionId, CoordinatePair from);
}
