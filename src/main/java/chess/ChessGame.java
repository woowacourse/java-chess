package chess;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;

public final class ChessGame {
    private final Board board;
    private boolean isPlaying = true;
    private Team currentTurnTeam;

    public ChessGame() {
        this(new BoardFactory().getBoard(), Team.WHITE);
    }

    public ChessGame(final Board board, final Team team) {
        this.board = board;
        currentTurnTeam = team;
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

    public final boolean isKingDieEnd() {
        return board.isEnemyKingDie(currentTurnTeam) || board.isEnemyKingDie(Team.getAnotherTeam(currentTurnTeam));
    }


    public final Team winner() {
        if (board.isEnemyKingDie(currentTurnTeam)) {
            return currentTurnTeam;
        }
        return Team.getAnotherTeam(currentTurnTeam);
    }
}
