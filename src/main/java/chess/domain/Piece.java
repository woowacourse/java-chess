package chess.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Piece {

    private final boolean isWhite;
    private final boolean isFinite;
    private final Set<Move> moves;

    public Piece(boolean isWhite, boolean isFinite, List<Move> moves) {
        this.isWhite = isWhite;
        this.isFinite = isFinite;
        this.moves = moves.stream()
                .flatMap(this::flipMove)
                .collect(Collectors.toSet());
    }

    private Stream<Move> flipMove(Move move) {
        return Stream.of(
                move,
                move.flipHorizontal(),
                move.flipVertical(),
                move.flipHorizontal().flipVertical()
        );
    }

    public boolean hasSameColor(Piece otherPiece) {
        return isWhite == otherPiece.isWhite;
    }

    public boolean hasMove(Move move) {
        boolean hasMove = false;
        for (Move pieceMove : moves) {
            hasMove = hasMove || compareMove(pieceMove, move);
        }
        return hasMove;
    }

    private boolean compareMove(Move pieceMove, Move move) {
        if (isFinite) {
            return pieceMove.equals(move);
        }
        return pieceMove.equals(move.getUnitMove());
    }
}
