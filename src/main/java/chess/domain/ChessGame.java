package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.domain.position.Row;

public final class ChessGame {
    private Board board;
    private Team currentTurn;
    private boolean isPlaying = true;
    private Team winner;

    public final void initSetting() {
        BoardFactory boardFactory = new BoardFactory();
        this.board = boardFactory.getBoard();
        this.currentTurn = Team.WHITE;
    }

    public final void move(final String startPoint, final String endPoint) {
        board.move(position(startPoint), position(endPoint), currentTurn);
        if (board.isEnemyKingDie(currentTurn)) {
            winner = currentTurn;
            end();
        }
        currentTurn = Team.getAnotherTeam(currentTurn);
    }

    private Position position(final String point) {
        return new Position(
                Row.getLocation(String.valueOf(point.charAt(1))),
                Col.getLocation(String.valueOf(point.charAt(0)))
        );
    }

    public final void end() {
        isPlaying = false;
    }

    public final boolean isPlaying() {
        return isPlaying;
    }

    public final double getScoreByTeam(final Team team) {
        return board.scoreByTeam(team);
    }

    public final Board getBoard() {
        return board;
    }

    public final Team winner() {
        return winner;
    }
}
