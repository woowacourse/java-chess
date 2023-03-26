package chess.dao;

import chess.domain.piece.type.Piece;
import java.util.List;

public interface PiecesDao {
    List<Piece> findAll();

    void insertAll(List<Piece> pieces);

    void delete();
}
