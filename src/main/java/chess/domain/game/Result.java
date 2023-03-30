package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Result {

    public static final int LOWER_BOUNDARY = 1;
    public static final int UPPER_BOUNDARY = 8;
    private static final int OVER_COUNT = 2;
    public static final int DIVIDE_VALUE = 2;
    public static final int ALL_KING_LIVE = 2;

    private final Map<Position, Piece> board;

    public Result(final Map<Position, Piece> board) {
        this.board = board;
    }

    public double calculateScore(Side side) {
        double sum = 0;

        for (int file = LOWER_BOUNDARY; file <= UPPER_BOUNDARY; file++) {
            sum += addScoreByRank(file, side);
        }
        return sum;
    }

    private double addScoreByRank(final int file, Side side) {
        double sum = 0;
        int pawnCount = 0;

        for (int rank = LOWER_BOUNDARY; rank <= UPPER_BOUNDARY; rank++) {
            final Piece piece = getPiece(Position.of(File.getFile(file), Rank.getRank(rank)));
            sum += addScoreIfRightColor(piece, side);
            pawnCount += addPawnCount(piece, side);
        }
        sum = minusIfPawnOver(sum, pawnCount);
        return sum;
    }

    private double minusIfPawnOver(double sum, final int pawnCount) {
        if (pawnCount >= OVER_COUNT) {
            sum -= pawnCount * (Type.PAWN.getValue() / DIVIDE_VALUE);
        }
        return sum;
    }

    private int addPawnCount(final Piece piece, Side side) {
        if (side.equals(piece.getSide()) && piece.isPawn()) {
            return 1;
        }
        return 0;
    }

    private double addScoreIfRightColor(final Piece piece, Side side) {
        if (side.equals(piece.getSide())) {
            return piece.getScore();
        }
        return 0;
    }

    public Side calculateWinner() {
        List<Piece> kings = new ArrayList<>();

        checkKingLive(kings);
        if (kings.size() == ALL_KING_LIVE) {
            return Side.NEUTRALITY;
        }
        return kings.get(0).getSide();
    }

    private void checkKingLive(final List<Piece> pieces) {
        for (int file = LOWER_BOUNDARY; file <= UPPER_BOUNDARY; file++) {
            addKingByRank(pieces, file);
        }
    }

    private void addKingByRank(final List<Piece> pieces, final int file) {
        for (int rank = LOWER_BOUNDARY; rank <= UPPER_BOUNDARY; rank++) {
            final Piece piece = getPiece(Position.of(File.getFile(file), Rank.getRank(rank)));

            addKingIfKingLive(pieces, piece);
        }
    }

    private void addKingIfKingLive(final List<Piece> pieces, final Piece piece) {
        if (piece.isKing()) {
            pieces.add(piece);
        }
    }

    private Piece getPiece(Position position) {
        return board.get(position);
    }
}
