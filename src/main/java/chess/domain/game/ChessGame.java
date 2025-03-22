package chess.domain.game;

import chess.domain.Position;
import chess.domain.TeamColor;


public class ChessGame {
    public ChessGame(Turn turn) {
        this.turn = turn;
    }

    private Turn turn;

    public void move( Position start, Position target) {
        this.turn = turn.movePiece(start, target);
    }

    public TeamColor getTurnColor() {
        return turn.getTeamColor();
    }

    public boolean isFinished() {
        return turn.isFinished();
    }
}
