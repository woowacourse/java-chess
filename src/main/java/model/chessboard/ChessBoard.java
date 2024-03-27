package model.chessboard;

import java.util.Map;
import model.chessboard.state.CurrentTurn;
import model.chessboard.state.DefaultState;
import model.piece.Color;
import model.piece.PieceHolder;
import model.position.Position;

public class ChessBoard {
    private DefaultState currentState;

    public ChessBoard() {
        currentState = new CurrentTurn(ChessBoardFactory.create(), Color.WHITE);
    }

    public void move(Position source, Position destination) {
        this.currentState = currentState.move(source, destination);
    }

    public boolean isFinish() {
        return currentState.isFinish();
    }

    public Map<Position, PieceHolder> getChessBoard() {
        return currentState.getChessBoard();
    }
}
