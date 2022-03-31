package chess.dto;

import chess.domain.piece.Form;
import chess.domain.piece.Piece;

public class PieceDto {

    public static String getName(final Piece piece) {
        return Form.getSymbol(piece);
    }
}
