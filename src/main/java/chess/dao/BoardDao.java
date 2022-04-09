package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public interface BoardDao<T> extends Dao<T> {

    T save(T target);

    T getById(int id);

    List<T> findAll();

    T init(T board, Map<Position, Piece> initialize);

    int deleteById(int id);

    void deleteAll();

    void updateTurn(Color color, int id);
}
