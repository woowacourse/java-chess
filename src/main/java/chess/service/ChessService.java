package chess.service;

import chess.domain.piece.Color;
import chess.dto.MoveRequestDto;
import chess.dto.MoveResultDto;
import chess.dto.PositionDto;
import java.util.List;
import java.util.Map;

public interface ChessService {
    List<PositionDto> getBoardByGameId(String gameId);

    MoveResultDto move(MoveRequestDto moveRequestDto);

    boolean isFinished(String gameId);

    Map<Color, Double> getScore(String gameId);
}
