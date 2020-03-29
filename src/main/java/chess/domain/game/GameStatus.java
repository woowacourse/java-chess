package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Row;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;

import java.util.List;

import static chess.domain.game.Team.WHITE;

public class GameStatus {
    private static final int BOARD_MAX_INDEX = 7;
    private static final int BOARD_MIN_INDEX = 0;
    private static final int COUNT_INIT = 0;
    private static final Team INIT_TEAM = WHITE;

    private static Team nowPlayingTeam = INIT_TEAM;
    private static boolean isGameEnd = false;

    private GameStatus() {
    }

    public static void initialize() {
        nowPlayingTeam = INIT_TEAM;
        isGameEnd = false;
    }

    public static double getTotalScore(Board board) {
        Score score = Score.DEFAULT;

        for (int j = BOARD_MIN_INDEX; j <= BOARD_MAX_INDEX; j++) {
            score = getColumnScore(board, score, j);
        }
        return score.getScore();
    }

    private static Score getColumnScore(Board board, Score score, int j) {
        int pawnCount = COUNT_INIT;
        List<Row> rows = board.getBoard();

        for (Row row : rows) {
            ChessPiece chessPiece = row.get(j);

            pawnCount = getColumnPawnCount(chessPiece);
            score = addIfSameTeam(score, chessPiece);
        }
        score = subtractSameColumnPawnScore(score, pawnCount);
        return score;
    }

    private static int getColumnPawnCount(ChessPiece chessPiece) {
        int pawnCount = COUNT_INIT;
        if (chessPiece.getTeam() == nowPlayingTeam && chessPiece instanceof Pawn) {
            pawnCount++;
        }
        return pawnCount;
    }

    private static Score addIfSameTeam(Score score, ChessPiece chessPiece) {
        if (chessPiece.getTeam() == nowPlayingTeam) {
            return score.add(chessPiece.getPoint());
        }
        return score;
    }

    private static Score subtractSameColumnPawnScore(Score score, int pawnCount) {
        if (pawnCount >= 2) {
            return score.subtract(pawnCount * 0.5);
        }
        return score;
    }

    public static Team getNowPlayingTeam() {
        return nowPlayingTeam;
    }

    public static void changePlayingTeam() {
        nowPlayingTeam = Team.getOpponentTeam(nowPlayingTeam);
    }

    public static void updateGameEnd() {
        isGameEnd = true;
    }

    public static boolean isGameEnd() {
        return isGameEnd;
    }
}
