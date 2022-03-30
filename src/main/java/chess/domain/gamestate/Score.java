package chess.domain.gamestate;

import static chess.domain.Camp.BLACK;
import static chess.domain.Camp.NONE;
import static chess.domain.Camp.WHITE;

import chess.domain.Camp;
import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.piece.Type;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Score {
    private static final int COUNT_KING_IN_EACH_CAMP = 1;
    private static final double HANDICAP_PAWN_SAME_CAMP_IN_A_COLUMN = 0.5;

    private final double value;

    private Score(double value) {
        this.value = value;
    }

    static Map<Camp, Score> of(Board board) {
        Map<Camp, Score> scores = new EnumMap<>(Camp.class);
        scores.put(WHITE, of(board, WHITE));
        scores.put(BLACK, of(board, BLACK));
        return scores;
    }

    private static Score of(Board board, Camp camp) {
        return new Score(Arrays.stream(Type.values())
                .mapToDouble(type -> calculatePieceOf(board, camp, type))
                .sum());
    }

    static Camp winnerOf(Board board) {
        Camp winnerByKing = winnerByKing(board);
        if (winnerByKing != NONE) {
            return winnerByKing;
        }
        return winnerByScore(board);
    }

    private static Camp winnerByKing(Board board) {
        if (board.countPiecesOf(WHITE, Type.KING) < COUNT_KING_IN_EACH_CAMP) {
            return BLACK;
        }
        if (board.countPiecesOf(BLACK, Type.KING) < COUNT_KING_IN_EACH_CAMP) {
            return WHITE;
        }
        return NONE;
    }

    private static Camp winnerByScore(Board board) {
        Score scoreOfWhite = of(board, WHITE);
        Score scoreOfBlack = of(board, BLACK);
        if (scoreOfWhite.value > scoreOfBlack.value) {
            return WHITE;
        }
        if (scoreOfBlack.value > scoreOfWhite.value) {
            return BLACK;
        }
        return NONE;
    }

    private static double calculatePieceOf(Board board, Camp camp, Type type) {
        if (type == Type.PAWN) {
            return calculatePawnOf(board, camp);
        }
        return type.scoreOf(board.countPiecesOf(camp, type));
    }

    private static double calculatePawnOf(Board board, Camp camp) {
        double score = 0;
        for (Column column : Column.values()) {
            score += calculatePawnOfCampIn(board, column, camp);
        }
        return score;
    }

    private static double calculatePawnOfCampIn(Board board, Column column, Camp camp) {
        int count = board.countPiecesOfCampIn(column, camp, Type.PAWN);
        if (count > 1) {
            return Type.PAWN.scoreOf(count) * HANDICAP_PAWN_SAME_CAMP_IN_A_COLUMN;
        }
        return Type.PAWN.scoreOf(count);
    }

    public double getValue() {
        return value;
    }
}
