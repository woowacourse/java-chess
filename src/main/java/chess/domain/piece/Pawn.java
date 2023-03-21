package chess.domain.piece;

import chess.domain.position.Move;
import chess.domain.position.Position;
import java.util.Set;

public class Pawn extends Piece {

    private static final int INITIAL_WHITE_RANK = 2;
    private static final int INITIAL_BLACK_RANK = 7;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Move move, Piece targetPiece) {
        if (isAttack(targetPiece)) {
            return canAttack(move);
        }
        if (isFirstMove(move)) {
            return canInitialMove(move);
        }
        return canMove(move);
    }

    private boolean isFirstMove(Move move) {
        Position source = move.getSource();
        return source.getRankIndex() == getInitialRankIndex();
    }

    private int getInitialRankIndex() {
        if (color == Color.WHITE) {
            return INITIAL_WHITE_RANK;
        }
        return INITIAL_BLACK_RANK;
    }

    private boolean isAttack(Piece targetPiece) {
        return targetPiece != null;
    }

    private boolean canAttack(Move move) {
        if (!move.isDiagonal()) {
            return false;
        }
        int deltaRank = move.getDeltaRank();
        if (isUpward()) {
            return deltaRank == 1;
        }
        return deltaRank == -1;
    }

    private boolean canInitialMove(Move move) {
        int deltaFile = move.getDeltaFile();
        int deltaRank = move.getDeltaRank();
        if (isUpward()) {
            return deltaFile == 0 && Set.of(1, 2).contains(deltaRank);
        }
        return deltaFile == 0 && Set.of(-1, -2).contains(deltaRank);
    }

    private boolean canMove(Move move) {
        int deltaFile = move.getDeltaFile();
        int deltaRank = move.getDeltaRank();
        if (isUpward()) {
            return deltaFile == 0 && deltaRank == 1;
        }
        return deltaFile == 0 && deltaRank == -1;
    }

    private boolean isUpward() {
        return color == Color.WHITE;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
