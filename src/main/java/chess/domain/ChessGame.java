package chess.domain;

import chess.domain.strategy.BlankMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.Map;

public class ChessGame {
    private MoveStrategy moveStrategy;
    private Color turnColor;

    public ChessGame(Map<Position, Piece> board) {
        this.moveStrategy = new BlankMoveStrategy(board);
        this.turnColor = Color.WHITE;
    }

    public void move(Position from, Position to) {
        moveStrategy = moveStrategy.changeStrategy(from);
        moveStrategy.move(turnColor, from, to);
        changeTurnColor();
    }

    private void changeTurnColor() {
        turnColor = turnColor.findOppositeColor();
    }

    public Map<Position, PieceType> collectBoard() {
        return moveStrategy.collectBoard();
    }
}
