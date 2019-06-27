package chess.domain.board;

import chess.domain.piece.*;

import java.util.Map;

public class PlayerFactory {
    public static Map<Square, Piece> init(PieceInit pieceInit) {
        return pieceInit.create();
    }
}
