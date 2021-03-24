package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

public class ChessGame {
    private Board board;
    private boolean isPlaying = true;
    private Team winner;

    public final void initSetting() {
        BoardFactory boardFactory = new BoardFactory();
        board = boardFactory.getBoard();
    }

    public final void end() {
        isPlaying = false;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public final Board getBoard() {
        return board;
    }

    public final void move(final Position startPoint, final Position endPoint, final Team team) {
        board.move(startPoint, endPoint, team);
        if (board.isEnemyKingDie(team)) {
            winner = team;
            end();
        }
    }

    public final double getScoreByTeam(final Team team) {
        return board.scoreByTeam(team);
    }

    public final Team winner() {
        return winner;
    }
}
