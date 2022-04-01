package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import java.util.List;

public abstract class FixedMovablePiece extends Piece {

    private static final int FIXED_MOVE_SIZE = 1;

    private final List<Direction> directions;

    public FixedMovablePiece(Color color, String name, List<Direction> directions) {
        super(color, name);
        this.directions = directions;
    }

    @Override
    public boolean isMovable(Position from, Position to, ChessBoard chessBoard) {
        return isMovableByFixed(from, to) && !isToSameColor(from, to, chessBoard);
    }

    private boolean isMovableByFixed(Position from, Position to) {
        return directions.stream()
                .map(direction -> direction.route(from, to))
                .anyMatch(route -> route.size() == FIXED_MOVE_SIZE);
    }

    private boolean isToSameColor(Position from, Position to, ChessBoard chessBoard) {
        if (chessBoard.isEmptyPosition(to)) {
            return false;
        }
        return chessBoard.findByPiece(to).isSameTeamPiece(chessBoard.findByPiece(from));
    }
}
