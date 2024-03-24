package chess.domain;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
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

    public void move(Position source, Position destination) {
        moveStrategy = moveStrategy.changeStrategy(source);
        moveStrategy.move(turnColor, source, destination);
        changeTurnColor();
    }

    private void changeTurnColor() {
        turnColor = turnColor.findOppositeColor();
    }

    public Map<Position, PieceType> collectBoard() {
        return moveStrategy.collectBoard();
    }
}
