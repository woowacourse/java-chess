package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;

import java.util.Arrays;

public class GameStatus {

    private final Score whiteScore;
    private final Score blackScore;

    private GameStatus(final Score whiteScore, final Score blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static GameStatus statusOfBoard(final Board board) {
        return new GameStatus(ownerScore(board, Owner.WHITE), ownerScore(board, Owner.BLACK));
    }

    private static Score ownerScore(final Board board, final Owner owner) {
        if (board.isKingAlive(owner)) {
            return calculateOwnerScore(board, owner);
        }
        return Score.ZERO_SCORE;
    }

    private static Score calculateOwnerScore(final Board board, final Owner owner) {
        return calculateOwnerScoreOnBoard(board, owner)
                .calculatePawnPenaltyScore(getPawnCountInVertical(board, owner));
    }

    private static Score calculateOwnerScoreOnBoard(final Board board, final Owner owner) {
        return board.pieces().stream()
                .filter(piece -> piece.isSameOwner(owner))
                .map(Piece::score)
                .reduce(Score.ZERO_SCORE, Score::sum);
    }

    private static int getPawnCountInVertical(final Board board, final Owner owner) {
        int totalCount = 0;
        for (final Horizontal horizontal : Horizontal.values()) {
            totalCount += penaltyScorePawnCount(board, horizontal, owner);
        }
        return totalCount;
    }

    private static int penaltyScorePawnCount(final Board board, final Horizontal horizontal, final Owner owner) {
        int pawnCount = (int) Arrays.stream(Vertical.values())
                .map(vertical -> board.pickPiece(horizontal, vertical))
                .filter(piece -> piece.isSameOwnerPawn(owner))
                .count();
        if (pawnCount > 1) {
            return pawnCount;
        }
        return 0;
    }

    public Owner judgeWinner() {
        if (whiteScore.isHigherThan(blackScore)) {
            return Owner.WHITE;
        }

        if (blackScore.isHigherThan(whiteScore)) {
            return Owner.BLACK;
        }
        return Owner.NONE;
    }

    public double whiteScore() {
        return whiteScore.value();
    }

    public double blackScore() {
        return blackScore.value();
    }
}
