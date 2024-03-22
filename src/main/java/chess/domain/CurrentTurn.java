package chess.domain;

import chess.domain.square.piece.Color;

// TODO: 불변객체로 만들어도 ChessBoard가 가진 필드는 턴마다 변해야 하는데 이점이 있는지 생각해보기.
public class CurrentTurn {
    private Color currentTurn;

    public CurrentTurn(Color startTurn) {
        this.currentTurn = startTurn;
    }

    public void change() {
        if (currentTurn == Color.BLACK) {
            currentTurn = Color.WHITE;
            return;
        }
        currentTurn = Color.BLACK;
    }

    public Color value() {
        return currentTurn;
    }
}
