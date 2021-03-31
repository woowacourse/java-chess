package chess;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class SymbolToPieceConvert {

    private static final String PAWN = "p";
    private static final String ROOK = "r";
    private static final String KNIGHT = "n";
    private static final String QUEEN = "q";
    private static final String KING = "k";
    private static final String BISHOP = "b";

    private SymbolToPieceConvert() {};

    public static Piece convert(String symbol, Position position) {
        String symbolType = symbol.toLowerCase();
        Color color = getColor(symbol, symbolType);
        if (PAWN.equals(symbolType)) {
            return new Pawn(color, position);
        }
        if (ROOK.equals(symbolType)) {
            return new Rook(color, position);
        }
        if (KNIGHT.equals(symbolType)) {
            return new Knight(color, position);
        }
        if (QUEEN.equals(symbolType)) {
            return new Queen(color, position);
        }
        if (KING.equals(symbolType)) {
            return new King(color, position);
        }
        if (BISHOP.equals(symbolType)) {
            return new Bishop(color, position);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private static Color getColor(String symbol, String symbolType) {
        if (symbol.equals(symbolType)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
