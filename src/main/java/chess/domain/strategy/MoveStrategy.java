package chess.domain.strategy;

import chess.domain.piece.blank.Blank;
import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class MoveStrategy {
    protected final Map<Position, Piece> board;

    public MoveStrategy(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public MoveStrategy changeStrategy(Position from) {
        PieceType pieceType = board.get(from).pieceType();
        if (pieceType == PieceType.NONE) {
            return new BlankMoveStrategy(board);
        }
        if (pieceType == PieceType.BLACK_PAWN || pieceType == PieceType.WHITE_PAWN) {
            return new PawnMoveStrategy(board);
        }
        return new GeneralMoveStrategy(board);
    }

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
