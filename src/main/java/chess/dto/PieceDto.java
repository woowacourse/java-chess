package chess.dto;

import chess.domain.piece.PieceSymbol;
import chess.domain.piece.Piece;

public class PieceDto {

    public static String getName(final Piece piece) {
        return PieceSymbol.getSymbol(piece);
    }
}
