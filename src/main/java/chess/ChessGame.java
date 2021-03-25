package chess;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

public final class ChessGame {
    private Board board;
    private boolean isPlaying = true;
    private Team currentTurnTeam;

    public final void initSetting() {
        BoardFactory boardFactory = new BoardFactory();
        currentTurnTeam = Team.WHITE;
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

    public final void move(final Position startPoint, final Position endPoint) {
        board.move(startPoint, endPoint, currentTurnTeam);
        if (board.isEnemyKingDie(currentTurnTeam)) {
            end();
        }
        currentTurnTeam = Team.getAnotherTeam(currentTurnTeam);
    }

    public final double getScoreByTeam(final Team team) {
        return board.scoreByTeam(team);
    }

    public final Team winner() {
        if (board.isEnemyKingDie(currentTurnTeam)) {
            return currentTurnTeam;
        }
        return Team.getAnotherTeam(currentTurnTeam);
    }
}
