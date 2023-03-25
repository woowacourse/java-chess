package chess.domain.dao;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.Position;

import java.util.List;

public interface PieceDao {

    void create (Piece piece, Color color);

    List<Piece> findPieceByColor (Color color);

    void updatePosition (Piece updatedPiece, Position from);

    void deletePieceByColor(Piece piece, Color color);

    void deleteAll();

}
