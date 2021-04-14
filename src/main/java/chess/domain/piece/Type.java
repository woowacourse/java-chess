package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.strategy.IterableMoveStrategy;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.piece.strategy.NonIterableMoveStrategy;
import chess.domain.piece.strategy.PawnMoveStrategy;
import java.util.Arrays;

public enum Type {

    PAWN("P", 1, new PawnMoveStrategy()),
    QUEEN("Q", 9, new IterableMoveStrategy()),
    KING("K", 0, new NonIterableMoveStrategy()),
    BISHOP("B", 3, new IterableMoveStrategy()),
    KNIGHT("N", 2.5, new NonIterableMoveStrategy()),
    ROOK("R", 5, new IterableMoveStrategy()),
    BLANK(".", 0, new MoveStrategy() {
        @Override
        public boolean movable(ChessBoard chessBoard, Position sourcePosition,
            Position targetPosition, Piece sourcePiece) {
            throw new UnsupportedOperationException(Blank.BLANK_ERROR);
        }
    });

    private final String name;
    private final double score;
    private final MoveStrategy moveStrategy;

    Type(String name, double score, MoveStrategy moveStrategy) {
        this.name = name;
        this.score = score;
        this.moveStrategy = moveStrategy;
    }

    public String nameByColor(Color color) {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    public static Type of(char piece) {
        System.out.println(piece);
        return Arrays.stream(values())
            .filter(value -> value.name.equals(String.valueOf(piece).toUpperCase()))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 피스 없음."));
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public double getScore() {
        return score;
    }
}
