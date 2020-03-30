package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Row;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;

import java.util.List;

import static chess.domain.board.BoardInfo.BOARD_MAX_INDEX;
import static chess.domain.board.BoardInfo.BOARD_MIN_INDEX;
import static chess.domain.game.Team.WHITE;

public class GameStatus {
    private static final int COUNT_INIT = 0;
    private static final Team INIT_TEAM = WHITE;

    private static Team nowPlayingTeam;
    private static boolean isGameEnd;

    public GameStatus() {
        nowPlayingTeam = INIT_TEAM;
        isGameEnd = false;
    }

    public double getTotalScore(Board board) {
        Score score = Score.DEFAULT;

        for (int j = BOARD_MIN_INDEX; j <= BOARD_MAX_INDEX; j++) {
            score = getColumnScore(board, score, j);
        }
        return score.getScore();
    }

    private Score getColumnScore(Board board, Score score, int j) {
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

    private int getColumnPawnCount(ChessPiece chessPiece) {
        int pawnCount = COUNT_INIT;
        if (chessPiece.getTeam() == nowPlayingTeam && chessPiece instanceof Pawn) {
            pawnCount++;
        }
        return pawnCount;
    }

    private Score addIfSameTeam(Score score, ChessPiece chessPiece) {
        if (chessPiece.getTeam() == nowPlayingTeam) {
            return score.add(chessPiece.getPoint());
        }
        return score;
    }

    private Score subtractSameColumnPawnScore(Score score, int pawnCount) {
        if (pawnCount >= 2) {
            return score.subtract(pawnCount * 0.5);
        }
        return score;
    }

    public Team getNowPlayingTeam() {
        return nowPlayingTeam;
    }

    public void changePlayingTeam() {
        nowPlayingTeam = Team.getOpponentTeam(nowPlayingTeam);
    }

    public void updateGameEnd() {
        isGameEnd = true;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }
}
