package chess.domain.refactorPiece;


import static chess.domain.refactorBoard.Direction.DOWN;
import static chess.domain.refactorBoard.Direction.LEFT;
import static chess.domain.refactorBoard.Direction.RIGHT;
import static chess.domain.refactorBoard.Direction.TOP;

import chess.domain.piece.attribute.Color;
import chess.domain.refactorBoard.ChessBoard;
import chess.domain.refactorBoard.Direction;
import chess.domain.refactorPosition.Position;
import java.util.Arrays;
import java.util.List;

public class Rook implements Piece {

    private final Color color;
    private final String name;
    private final List<Direction> directions;

    public Rook(Color color) {
        this(color, "R", Arrays.asList(TOP, DOWN, LEFT, RIGHT));
    }

    private Rook(Color color, String name, List<Direction> directions) {
        this.color = color;
        this.name = name;
        this.directions = directions;
    }

    @Override
    public Piece move(Position from, Position to, ChessBoard chessBoard) {
        if (!isMovable(from, to, chessBoard)) {
            throw new IllegalArgumentException("움직일 수 없는 이동입니다.");
        }

        return this;
    }

    private boolean isMovable(Position from, Position to, ChessBoard chessBoard) {
        final List<Position> route = calculateRoute(from, to);

        return isEmptyRouteWithToPosition(to, route, chessBoard);
    }

    private List<Position> calculateRoute(Position from, Position to) {
        return directions.stream()
                .map(direction -> direction.route(from, to))
                .filter(route -> !route.isEmpty())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 있는 경로가 없습니다."));
    }

    private boolean isEmptyRouteWithToPosition(Position to, List<Position> route, ChessBoard chessBoard) {
        return route.stream()
                .filter(position -> !position.equals(to))
                .allMatch(position -> chessBoard.isEmptyPosition(position));
    }
}
