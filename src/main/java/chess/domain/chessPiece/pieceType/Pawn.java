package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.MovableStrategy.PawnMovableStrategy;
import chess.domain.chessPiece.PieceColor;

import java.util.Objects;

public class Pawn implements PieceType {

    public static final String NAME = "P";

    private final PieceColor pieceColor;
    private final MovableStrategy movableStrategy;

    Pawn(PieceColor pieceColor) {
        Objects.requireNonNull(pieceColor, "폰의 피스 색상이 null입니다.");
        this.pieceColor = pieceColor;
        this.movableStrategy = new PawnMovableStrategy(pieceColor);
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
