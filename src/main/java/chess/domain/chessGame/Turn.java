package chess.domain.chessGame;

import chess.domain.piece.Piece;

public class Turn {

    private TurnState turnState;

    public Turn(TurnState turnState) {
        this.turnState = turnState;
    }

    public static Turn create() {
        return new Turn(TurnState.WAIT);
    }

    public void startGame() {
        if (turnState == TurnState.END) {
            throw new IllegalStateException("게임을 시작할 수 없는 상태입니다");
        }
        if (turnState == TurnState.WAIT) {
            turnState = TurnState.WHITE;
            return;
        }
        turnState = TurnState.activate(turnState);
    }

    public void pauseGame() {
        turnState = TurnState.inactivate(turnState);
    }

    public void stopGame() {
        turnState = TurnState.END;
    }

    public void oppositeTurn() {
        if (turnState == TurnState.BLACK) {
            turnState = TurnState.WHITE;
            return;
        }
        turnState = TurnState.BLACK;
    }

    public boolean isActive() {
        return turnState.isActive();
    }

    public boolean isEnd() {
        return turnState == TurnState.END;
    }

    public boolean isValidTurn(Piece piece) {
        return piece.isSameColor(turnState.color())
                && turnState.isActive();
    }

    public String state() {
        return this.turnState.name();
    }


}
