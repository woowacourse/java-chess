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

    public final void initialize() {
        BoardFactory boardFactory = new BoardFactory();
        this.board = boardFactory.board();
        this.currentTurn = Team.WHITE;
    }

    public final void move(final String startPoint, final String endPoint) {
        board.move(position(startPoint), position(endPoint), currentTurn);
        if (board.isEnemyKingDead(currentTurn)) {
            winner = currentTurn;
            finish();
        }
        currentTurn = Team.enemyTeam(currentTurn);
    }

    private Position position(final String point) {
        return new Position(
                Row.location(String.valueOf(point.charAt(1))),
                Col.location(String.valueOf(point.charAt(0)))
        );
    }

    public final void finish() {
        isPlaying = false;
    }

    public final boolean isPlaying() {
        return isPlaying;
    }

    public final double scoreByTeam(final Team team) {
        return board.scoreByTeam(team);
    }

    public final Board board() {
        return board;
    }

    public final Team winner() {
        return winner;
    }

    public Team turn() {
        return currentTurn;
    }
}
