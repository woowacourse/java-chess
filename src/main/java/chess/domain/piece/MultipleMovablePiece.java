package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.board.Direction;
import java.util.List;

public abstract class MultipleMovablePiece extends Piece {

    private List<Direction> directions;

    MultipleMovablePiece(Color color, String name, List<Direction> directions) {
        super(color, name);
        this.directions = directions;
    }

    @Override
    public boolean isMovable(Position from, Position to, ChessBoard chessBoard) {
        final List<Position> route = calculateRoute(from, to);
        return isEmptyRouteWithToPosition(to, route, chessBoard) && !isToSameColor(from, to, chessBoard);
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

    private boolean isToSameColor(Position from, Position to, ChessBoard chessBoard) {
        if (chessBoard.isEmptyPosition(to)) {
            return false;
        }
        return chessBoard.findByPiece(to).isSameTeamPiece(chessBoard.findByPiece(from));
    }
}
