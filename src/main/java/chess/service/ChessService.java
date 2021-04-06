package chess.service;

import chess.domain.Result;
import chess.dto.MovementDto;
import chess.dto.PieceDto;

import java.util.Map;

public interface ChessService {

    Map<String, PieceDto> move(MovementDto movementDto);

    Result saveAndShowResult();

    Map<String, PieceDto> getDefaultChessBoard();

    Result getResult();

    Result terminateGameAndGetResult();

    Map<String, PieceDto> getSavedChessBoard();

}
