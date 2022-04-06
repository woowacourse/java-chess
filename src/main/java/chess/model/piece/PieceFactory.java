package chess.model.piece;

import chess.model.Color;
import chess.model.piece.pawn.Pawn;
import chess.service.PieceDto;

public class PieceFactory {
    public static Piece create(PieceDto dto) {
        Color color = Color.valueOf(dto.getColor().toUpperCase());
        String typeName = dto.getType();
        if (typeName.equals("pawn")) {
            return Pawn.of(color);
        }
        if (typeName.equals("king")) {
            return new King(color);
        }
        if (typeName.equals("queen")) {
            return new Queen(color);
        }
        if (typeName.equals("knight")) {
            return new Knight(color);
        }
        if (typeName.equals("rook")) {
            return new Rook(color);
        }
        if (typeName.equals("bishop")) {
            return new Bishop(color);
        }
        return new Empty();
    }
}
