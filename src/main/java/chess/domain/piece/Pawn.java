package chess.domain.piece;


import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.DOWN_LEFT;
import static chess.domain.board.Direction.DOWN_RIGHT;
import static chess.domain.board.Direction.TOP;
import static chess.domain.board.Direction.TOP_LEFT;
import static chess.domain.board.Direction.TOP_RIGHT;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.board.ChessBoard;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    private static final int OTHER_PIECE_MOVE_SIZE = 1;
    private static final int MAXIMUM_PIECE_MOVE = 2;

    private final Direction defaultDirection;
    private final List<Direction> initDirections;

    public Pawn(Color color) {
        super(color, "P");
        this.defaultDirection = defaultDirectionDecision(color);
        this.initDirections = initDirectionDecision(color);
    }

    private Direction defaultDirectionDecision(Color color) {
        if (color == Color.BLACK) {
            return DOWN;
        }
        return TOP;
    }

    private List<Direction> initDirectionDecision(Color color) {
        if (color == Color.BLACK) {
            return Arrays.asList(DOWN_RIGHT, DOWN_LEFT);
        }
        return Arrays.asList(TOP_RIGHT, TOP_LEFT);
    }

    @Override
    public boolean isMovable(Position from, Position to, ChessBoard chessBoard) {
        if (isExistPositionToOtherTeamPiece(from, to, chessBoard)) {
            return isMovableToOtherTeamPosition(from, to);
        }
        return isMovableToPosition(from, to, chessBoard);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private boolean isExistPositionToOtherTeamPiece(Position from, Position to, ChessBoard chessBoard) {
        return !chessBoard.isEmptyPosition(to);
    }

    private boolean isMovableToOtherTeamPosition(Position from, Position to) {
        return initDirections.stream()
                .map(direction -> direction.route(from, to))
                .anyMatch(route -> route.size() == OTHER_PIECE_MOVE_SIZE);
    }

    private boolean isMovableToPosition(Position from, Position to, ChessBoard chessBoard) {
        final List<Position> route = defaultDirection.route(from, to);
        validateNotInitLinePosition(from, route);

        return isExistPosition(route) && isEmptyRoute(route, chessBoard);
    }

    private void validateNotInitLinePosition(Position from, List<Position> route) {
        if (!from.isInitLine() && route.size() == MAXIMUM_PIECE_MOVE) {
            throw new IllegalArgumentException("최초 위치가 아니면 2칸 이동할 수 없습니다.");
        }
    }

    private boolean isExistPosition(List<Position> route) {
        return !route.isEmpty() && route.size() <= MAXIMUM_PIECE_MOVE;
    }

    private boolean isEmptyRoute(List<Position> route, ChessBoard chessBoard) {
        return route.stream()
                .allMatch(position -> chessBoard.isEmptyPosition(position));
    }
}
