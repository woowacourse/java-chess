package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.Arrays;

public class Status {

    private final Score whiteScore;
    private final Score blackScore;

    private Status(final Score whiteScore, final Score blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static Status statusOfBoard(Board board) {
        return new Status(ownerScore(board, Owner.WHITE),
                ownerScore(board, Owner.BLACK));
    }

    private static Score ownerScore(final Board board, final Owner owner) {
        if (board.isKingAlive(owner)) {
            return calculateOwnerScore(board, owner);
        }
        return new Score(0);
    }

    private static Score calculateOwnerScore(final Board board, final Owner owner) {
        return calculateOwnerScoreOnBoard(board, owner)
                .calculatePawnPenaltyScore(getPawnCountInVertical(board, owner));
    }

    private static Score calculateOwnerScoreOnBoard(final Board board, final Owner owner) {
        return board.pieces().stream()
                .filter(piece -> piece.isOwner(owner))
                .map(Piece::score)
                .reduce(new Score(0), Score::sum);
    }

    private static int getPawnCountInVertical(final Board board, final Owner owner) {
        int totalCount = 0;
        for (final Vertical vertical : Vertical.values()) {
            totalCount += penaltyScorePawnCount(board, vertical, owner);
        }
        return totalCount;
    }

    private static int penaltyScorePawnCount(Board board, Vertical vertical, Owner owner) {
        int pawnCount = (int) Arrays.stream(Horizontal.values())
                .map(horizontal -> board.of(vertical, horizontal))
                .filter(piece -> piece.isSameOwnerPawn(owner))
                .count();
        if (pawnCount > 1) {
            return pawnCount;
        }
        return 0;
    }

    public double whiteScore() {
        return whiteScore.value();
    }

    public double blackScore() {
        return blackScore.value();
    }
}
