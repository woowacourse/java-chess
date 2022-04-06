package chess.model.piece;

import chess.model.ConsoleBoard;
import chess.model.square.Square;

public interface Piece {

    boolean isBlack();

    String name();

    boolean movable(ConsoleBoard consoleBoard, Square source, Square target);

    boolean canMoveWithoutObstacle(ConsoleBoard consoleBoard, Square source, Square target);

    double getPoint();

    boolean isPawn();

    boolean isKing();

    boolean isSameColor(Color color);

    Color color();

    boolean isNotEmpty();
}
