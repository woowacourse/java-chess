package chess.dao;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;

import java.util.List;

public interface ChessBoardDao {

    void addPiece(Piece piece) throws Exception;

    void deletePiece(Position targetPosition) throws Exception;

    void updatePiece(Position sourcePosition, Position targetPosition) throws Exception;

    void deleteAll() throws Exception;

    ChessBoard find() throws Exception;

    void addPieces(List<Piece> pieces) throws Exception;
}
