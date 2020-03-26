package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.BishopMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;

import java.util.Objects;

public class Bishop implements PieceType {

    public static final String NAME = "B";

    private final PieceColor pieceColor;
    private final MovableStrategy movableStrategy;

    Bishop(PieceColor pieceColor) {
        Objects.requireNonNull(pieceColor, "비숍의 피스 색상이 null입니다.");
        this.pieceColor = pieceColor;
        this.movableStrategy = new BishopMovableStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
