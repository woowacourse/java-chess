package chess.repository;

import chess.dto.ChessRequestDto;
import chess.dto.MoveRequestDto;
import chess.dto.TurnChangeRequestDto;
import chess.dto.TurnRequestDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ChessRepository {
    void initializePieceStatus(final Map<String, String> board) throws SQLException;

    void initializeTurn() throws SQLException;

    List<ChessRequestDto> showAllPieces() throws SQLException;

    List<TurnRequestDto> showCurrentTurn() throws SQLException;

    void movePiece(final MoveRequestDto moveRequestDto) throws SQLException;

    void changeTurn(final TurnChangeRequestDto turnChangeRequestDto) throws SQLException;

    void removeAllPieces() throws SQLException;

    void removeTurn() throws SQLException;

    void removePiece(final MoveRequestDto moveRequestDto) throws SQLException;
}
