package chess.model.piece;

import chess.model.Coordinate;

import java.util.List;

public interface Piece {
    boolean isMovePossible(List<Coordinate> asList);
    void signalMoved();
}
