package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.board.Route;
import chess.model.board.vector.Vector;

import java.util.List;

public interface Piece {
    Route produceRoute(List<Coordinate> coordinates, Vector vector);

    String askTeamColor();

    Piece cloneSelf();

    boolean isPawn();

    double getScore();

    boolean isKing();
}
