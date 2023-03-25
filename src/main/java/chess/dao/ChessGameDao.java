package chess.dao;

import java.util.List;

import chess.domain.piece.dto.FindPieceDto;
import chess.domain.piece.dto.SavePieceDto;
import chess.domain.piece.dto.UpdatePiecePositionDto;
import chess.domain.piece.dto.GeneratePieceDto;
import chess.domain.service.dto.ChessGameDto;

public interface ChessGameDao {

    Long saveNewChessGame();

    boolean isExistPreviousChessGame(Long gameId);

    void savePiece(SavePieceDto savePieceDto);

    List<GeneratePieceDto> findAllPieceByGameId(Long gameId);

    ChessGameDto findChessGameByGameId(Long gameId);

    void updatePiecePosition(UpdatePiecePositionDto updatePiecePositionDto, FindPieceDto findPieceDto);
}
