package chess.dao;

import java.util.List;

import chess.domain.piece.dto.FindPiecePositionDto;
import chess.domain.piece.dto.SavePieceDto;
import chess.domain.piece.dto.UpdatePiecePositionDto;
import chess.domain.piece.dto.GeneratePieceDto;
import chess.domain.service.dto.ChessGameDto;
import chess.domain.service.dto.UpdateTurnDto;

public interface ChessGameDao {

    Long saveNewChessGame();

    boolean isExistPreviousChessGame(Long gameId);

    void savePiece(SavePieceDto savePieceDto);

    List<GeneratePieceDto> findAllPieceByGameId(Long gameId);

    ChessGameDto findChessGameByGameId(Long gameId);

    void updatePiecePosition(UpdatePiecePositionDto updatePiecePositionDto, FindPiecePositionDto findPiecePositionDto);

    void deletePieceByPosition(FindPiecePositionDto findPiecePositionDto);

    void updateTurn(UpdateTurnDto updateTurnDto);
}
