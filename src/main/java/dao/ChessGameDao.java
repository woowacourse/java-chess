package dao;

import domain.piece.move.Coordinate;

import java.util.List;

public interface ChessGameDao {

    void create(final Coordinate start, final Coordinate end);
    List<Coordinate> read();
    void delete();
}
