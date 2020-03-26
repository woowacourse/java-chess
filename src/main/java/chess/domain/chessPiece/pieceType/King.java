package chess.domain.chessPiece.pieceType;

import chess.domain.MovableStrategy.KingMovableStrategy;
import chess.domain.MovableStrategy.MovableStrategy;
import chess.domain.chessPiece.PieceColor;

import java.util.Objects;

public class King implements PieceType {

    public static final String NAME = "K";

    private final PieceColor pieceColor;
    private final MovableStrategy movableStrategy;

    King(PieceColor pieceColor) {
        Objects.requireNonNull(pieceColor, "킹의 피스 색상이 null입니다.");
        this.pieceColor = pieceColor;
        this.movableStrategy = new KingMovableStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }

}
