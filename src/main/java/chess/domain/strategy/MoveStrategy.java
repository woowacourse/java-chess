package chess.domain.strategy;

import chess.domain.Blank;
import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.PieceType;
import chess.domain.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class MoveStrategy {
    protected final Map<Position, Piece> board;

    public MoveStrategy(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public abstract MoveStrategy changeStrategy(Position from);

    public abstract void move(Color turnColor, Position from, Position to);

    public void checkTurnOf(Piece currentPiece, Color turnColor) {
        if (!currentPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("상대 말은 이동할 수 없습니다.");
        }
    }

    protected boolean isAllBlankCourses(Set<Position> movablePositions) {
        return movablePositions.stream()
                .map(board::get)
                .allMatch(piece -> piece instanceof Blank);
    }

    public Map<Position, PieceType> collectBoard() {
        return board.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().pieceType()
                ));
    }
}
