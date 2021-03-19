package chess.domain.piece;

import chess.domain.Board;
import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.MoveStrategy;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected PieceType pieceType;
    protected PieceColor pieceColor;
    protected MoveStrategy moveStrategy;

    protected Piece(PieceType pieceType, PieceColor pieceColor, MoveStrategy moveStrategy) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.moveStrategy = moveStrategy;
    }

    public String getName() {
        if (pieceColor.equals(PieceColor.BLACK)) {
            return pieceType.toBlack();
        }
        return pieceType.getType();
    }

    public boolean isColor(PieceColor color) {
        return pieceColor.equals(color);
    }

    public boolean isEnemy(Piece that) {
        return !isColor(that.pieceColor);
    }
}
