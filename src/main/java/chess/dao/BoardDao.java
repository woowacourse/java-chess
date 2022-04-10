package chess.dao;

import chess.model.Board;
import chess.model.piece.Piece;
import chess.model.square.Square;
import java.util.List;
import java.util.Map;

public interface BoardDao<T> {

    Board save(Board board);

    int deleteAll();

    int deleteById(int id);

    T getById(int id);

    T init(T board, Map<Square, Piece> startingPieces);
}
