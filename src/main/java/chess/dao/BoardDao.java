package chess.dao;

import chess.dto.request.CreatePieceDto;
import chess.dto.request.DeletePieceDto;
import chess.dto.request.UpdatePiecePositionDto;
import chess.dto.response.BoardDto;

public interface BoardDao {
    BoardDto getBoard(String gameId);

    void createPiece(CreatePieceDto createPieceDto);

    void deletePiece(DeletePieceDto deletePieceDto);

    void updatePiecePosition(UpdatePiecePositionDto updatePiecePositionDto);
}
