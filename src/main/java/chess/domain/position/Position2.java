package chess.domain.position;

import chess.domain.piece.Piece2;
import chess.domain.piece.PieceColor;
import chess.domain.piece.strategy.MoveUnit;

public interface Position2 {

    static Position2 ofName(String name) {
        return null;
    }

    void replacePieceTo(Piece2 piece);

    Position2 move(MoveUnit moveUnit);

    boolean isColor(PieceColor color);

    boolean isEmpty();
}
