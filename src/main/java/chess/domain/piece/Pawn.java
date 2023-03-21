package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Pawn extends Piece {
    private final List<Move> possibleMoves;
    private final boolean isMoved;

    public Pawn(final Camp camp) {
        super(camp, Role.PAWN);
        this.isMoved = false;
        this.possibleMoves = generatePossibleMoves();
    }

    public Pawn(final Camp camp, final boolean isMoved) {
        super(camp, Role.PAWN);
        this.isMoved = isMoved;
        this.possibleMoves = generatePossibleMoves();
    }

    private List<Move> generatePossibleMoves() {
        if (camp.equals(Camp.WHITE)) {
            return generateWhitePossibleMoves();
        }

        return generateBlackPossibleMoves();
    }

    private List<Move> generateWhitePossibleMoves() {
        if (isMoved) {
            return List.of(
                    Move.UP,
                    Move.UP_RIGHT,
                    Move.UP_LEFT
            );
        }
        return List.of(
                Move.UP,
                Move.UP_RIGHT,
                Move.UP_LEFT,
                Move.UP_UP
        );
    }

    private List<Move> generateBlackPossibleMoves() {
        if (isMoved) {
            return List.of(
                    Move.DOWN,
                    Move.DOWN_RIGHT,
                    Move.DOWN_LEFT
            );
        }
        return List.of(
                Move.DOWN,
                Move.DOWN_RIGHT,
                Move.DOWN_LEFT,
                Move.DOWN_DOWN
        );
    }

    @Override
    public boolean isMovable(final Square source, final Square target, final Move move) {
        return possibleMoves.contains(move);
    }
}
