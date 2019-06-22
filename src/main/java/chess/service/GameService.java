package chess.service;

import chess.domain.CoordinatePair;
import chess.domain.GameResult;
import chess.persistence.dto.BoardStateDto;

import java.util.List;

public interface GameService {
    List<BoardStateDto> findBoardStatesByRoomId(long roomId);

    GameResult movePiece(CoordinatePair from, CoordinatePair to, long roomId);
}
