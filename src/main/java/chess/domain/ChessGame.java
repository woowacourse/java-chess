package chess.domain;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Positions;
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

    public void move(Positions positions) {
        moveStrategy = moveStrategy.changeStrategy(positions.from());
        moveStrategy.move(turnColor, positions);
        changeTurnColor();
    }

    private void changeTurnColor() {
        turnColor = turnColor.findOppositeColor();
    }

    public Map<Position, PieceType> collectBoard() {
        return moveStrategy.collectBoard();
    }
}
