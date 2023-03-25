package dao;

import domain.piece.move.Coordinate;

public interface ChessGameDao {

    void create(final Coordinate start, final Coordinate end);
    Coordinate read();
    void update(final Coordinate start, final Coordinate end);
    void delete();
}
