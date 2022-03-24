package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGameProgress {

    private final ChessGame chessGame;
    private final GameSwitch gameSwitch;
    private final Turn turn;

    public ChessGameProgress() {
        this.chessGame = new ChessGame(Board.create());
        this.gameSwitch = new GameSwitch();
        this.turn = new Turn();
    }

    public void move(final String rawSource, final String rawTarget) {
        final Piece targetPiece = chessGame.move(
                Position.valueOf(rawSource),
                Position.valueOf(rawTarget),
                turn
        );
        turnOffWhenKingDie(targetPiece);
        turn.nextTurn();
    }

    private void turnOffWhenKingDie(final Piece targetPiece) {
        if (targetPiece.isKing()) {
            gameSwitch.turnOff();
        }
    }

    public Score calculateScore() {
        return new Score(getCurrentBoard());
    }

    public boolean isOn() {
        return gameSwitch.isOn();
    }

    public void turnOff() {
        gameSwitch.turnOff();
    }

    public Map<Position, Piece> getCurrentBoard() {
        return chessGame.getBoard();
    }
}
