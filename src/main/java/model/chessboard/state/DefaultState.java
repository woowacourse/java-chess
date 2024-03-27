package model.chessboard.state;

import java.util.Map;
import model.piece.Color;
import model.piece.PieceHolder;
import model.position.Position;

public abstract class DefaultState {
    protected Map<Position, PieceHolder> chessBoard;
    protected final Color currentColor;

    protected DefaultState(Map<Position, PieceHolder> chessBoard, Color currentColor) {
        this.chessBoard = chessBoard;
        this.currentColor = currentColor;
    }

    public abstract DefaultState move(Position source, Position destination);

    protected abstract boolean isCheckedBy(Color targetColor);

    protected abstract DefaultState nextState();

    public abstract boolean isFinish();

    public Map<Position, PieceHolder> getChessBoard() {
        return Map.copyOf(chessBoard);
    }
}
