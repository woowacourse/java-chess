package chess.model.piece;

import chess.model.Coordinate;
import chess.model.Route;
import chess.model.Vector;

import java.util.List;

public interface Piece {
    Route produceRoute(List<Coordinate> coordinates, Vector vector);

    String askTeamColor();

    Piece cloneSelf();

    boolean isPawn();
}
