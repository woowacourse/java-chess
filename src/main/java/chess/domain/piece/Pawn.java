package chess.domain.piece;

import chess.domain.board.Move;
import chess.domain.board.Square;
import java.util.List;

public class Pawn extends Piece {
    private final List<Move> possibleMoves;
    private final boolean isMoved;

    public Pawn(final Camp camp, final Square position) {
        super(camp, position);
        this.isMoved = false;
        this.possibleMoves = makePossibleMoves();
    }

    public Pawn(final Camp camp, final Square position, final boolean isMoved) {
        super(camp, position);
        this.isMoved = isMoved;
        this.possibleMoves = makePossibleMoves();
    }

    private List<Move> makePossibleMoves() {
        if (camp().equals(Camp.WHITE)) {
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
    public boolean isMovable(final Square target, final Move move, final boolean isPathBlocked) {
        return possibleMoves.contains(move) && !isPathBlocked;
    }

    @Override
    public PieceType pieceType() {
        return PieceType.PAWN;
    }
}
