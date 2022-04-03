package chess.dao;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public interface ChessBoardDao {

    Map<Position, Piece> findAllPieces();

    void savePieces(Map<Position, Piece> chessBoard);
}
