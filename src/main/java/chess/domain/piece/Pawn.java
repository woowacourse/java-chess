package chess.domain.piece;

import static chess.domain.piece.Type.PAWN;

import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private static final int STAY = 0;
    private static final int ONE_SQUARE = 1;
    private static final int TWO_SQUARES = 2;
    public static final Rank WHITE_INITIAL_POSITION = Rank.TWO;
    public static final Rank BLACK_INITIAL_POSITION = Rank.SEVEN;

    private final Color color;

    public Pawn(Color color) {
        this.color = color;
    }

    @Override
    public String identifyType() {
        return PAWN.name();
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    @Override
    public boolean canMove(Position source, Position target, Piece piece) {
        if (piece.isSameColor(color)) {
            return false;
        }
        if (this.color == Color.BLACK) {
            return checkBlack(source, target, piece);
        }
        return checkWhite(source, target, piece);
    }

    @Override
    public List<Position> searchPath(Position source, Position target) {
        int rankDiff = source.calculateRankDifference(target);
        List<Position> path = new ArrayList<>();

        if (Math.abs(rankDiff) == TWO_SQUARES) {
            source = source.move(STAY, rankDiff / TWO_SQUARES);
            path.add(source);
        }
        return path;
    }

    private boolean checkBlack(Position source, Position target, Piece piece) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        if (rankDiff == -ONE_SQUARE && Math.abs(fileDiff) == ONE_SQUARE) {
            return piece.isSameColor(Color.WHITE);
        }
        if (piece.isSameColor(Color.WHITE)) {
            return false;
        }
        if (source.isRank(BLACK_INITIAL_POSITION)) {
            return (rankDiff == -ONE_SQUARE || rankDiff == -TWO_SQUARES) && (fileDiff == STAY);
        }
        return rankDiff == -ONE_SQUARE && fileDiff == STAY;
    }

    private boolean checkWhite(Position source, Position target, Piece piece) {
        int rankDiff = source.calculateRankDifference(target);
        int fileDiff = source.calculateFileDifference(target);

        if (rankDiff == ONE_SQUARE && Math.abs(fileDiff) == ONE_SQUARE) {
            return piece.isSameColor(Color.BLACK);
        }
        if (piece.isSameColor(Color.BLACK)) {
            return false;
        }
        if (source.isRank(WHITE_INITIAL_POSITION)) {
            return (rankDiff == ONE_SQUARE || rankDiff == TWO_SQUARES) && (fileDiff == STAY);
        }
        return rankDiff == ONE_SQUARE && fileDiff == STAY;
    }
}
