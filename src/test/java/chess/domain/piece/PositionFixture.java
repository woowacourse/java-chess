package chess.domain.piece;

import chess.domain.position.Coordinate;
import chess.domain.position.Position;

public class PositionFixture {
    static final Position FROM = Position.fromCoordinate(Coordinate.of("d2"));
    static final Position LEFT_UP = Position.fromCoordinate(Coordinate.of("c3"));
    static final Position UP = Position.fromCoordinate(Coordinate.of("d3"));
    static final Position RIGHT_UP = Position.fromCoordinate(Coordinate.of("e3"));
    static final Position RIGHT = Position.fromCoordinate(Coordinate.of("e2"));
    static final Position RIGHT_DOWN = Position.fromCoordinate(Coordinate.of("e1"));
    static final Position DOWN = Position.fromCoordinate(Coordinate.of("d1"));
    static final Position LEFT_DOWN = Position.fromCoordinate(Coordinate.of("c1"));
    static final Position LEFT = Position.fromCoordinate(Coordinate.of("c2"));
    static final Position LEFT_DIAGONAL = Position.fromCoordinate(Coordinate.of("a5"));
    static final Position RIGHT_DIAGONAL = Position.fromCoordinate(Coordinate.of("h6"));
    static final Position UP_STRAIGHT = Position.fromCoordinate(Coordinate.of("d8"));
    static final Position RIGHT_STRAIGHT = Position.fromCoordinate(Coordinate.of("h2"));
    static final Position UP_UP = Position.fromCoordinate(Coordinate.of("d4"));
    static final Position LEFT_UP_UP = Position.fromCoordinate(Coordinate.of("c4"));
    static final Position RIGHT_UP_UP = Position.fromCoordinate(Coordinate.of("e4"));
    static final Position LEFT_LEFT_UP = Position.fromCoordinate(Coordinate.of("b3"));
    static final Position RIGHT_RIGHT_UP = Position.fromCoordinate(Coordinate.of("f3"));
}
