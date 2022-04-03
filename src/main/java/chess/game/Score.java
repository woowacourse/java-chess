package chess.game;

import chess.chessBoard.Board;
import chess.chessBoard.position.File;
import chess.chessBoard.position.Position;
import chess.chessBoard.position.Rank;

import java.util.HashMap;

import static chess.game.Player.BLACK;
import static chess.game.Player.WHITE;

public final class Score {

    private static final double DEDUCTION_PAWNS_IN_SAME_FILE = 0.5;
    private static final int STARTING_COUNT = 0;
    private static final int NONE_SUM = 0;
    private static final int COUNTS = 1;
    private final HashMap<Player, Double> scores;

    public Score() {
        this.scores = new HashMap<>();
    }

    public void calculateScore(final Board board) {
        scores.put(WHITE, calculateScore(board, WHITE));
        scores.put(BLACK, calculateScore(board, BLACK));
    }

    private double calculateScore(final Board board, final Player player) {
        double score = STARTING_COUNT;
        for (Rank rank : Rank.values()) {
            score = scoresInRank(board, player, score, rank);
        }
        return score;
    }

    private double scoresInRank(final Board board, final Player player, double score, final Rank rank) {
        int pawnCountInFile = STARTING_COUNT;
        for (File file : File.values()) {
            score = addScoreOfPiece(board, player, score, Position.of(rank, file));
            pawnCountInFile += addPawnCount(board, player, Position.of(rank, file));
        }
        if (pawnCountInFile > COUNTS) {
            score -= (DEDUCTION_PAWNS_IN_SAME_FILE * pawnCountInFile);
        }
        return score;
    }

    private int addPawnCount(final Board board, final Player player, final Position position) {
        if (board.isSamePlayerIn(position, player) || board.isPawn(position)) {
            return COUNTS;
        }
        return NONE_SUM;
    }

    private double addScoreOfPiece(final Board board, final Player player, double score, final Position position) {
        if (board.isSamePlayerIn(position, player)) {
            score = board.addPieceScore(position, score);
        }
        return score;
    }

    public HashMap<Player, Double> getScores() {
        return scores;
    }
}
