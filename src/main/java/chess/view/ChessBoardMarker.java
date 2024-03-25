package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardMarker {

    private static final Map<Piece, Character> pieceSymbol = new HashMap<>();

    static {
        pieceSymbol.put(new Rook(Color.BLACK), 'R');
        pieceSymbol.put(new Knight(Color.BLACK), 'N');
        pieceSymbol.put(new Bishop(Color.BLACK), 'B');
        pieceSymbol.put(new Queen(Color.BLACK), 'Q');
        pieceSymbol.put(new King(Color.BLACK), 'K');
        pieceSymbol.put(new Pawn(Color.BLACK), 'P');

        pieceSymbol.put(new Rook(Color.WHITE), 'r');
        pieceSymbol.put(new Knight(Color.WHITE), 'n');
        pieceSymbol.put(new Bishop(Color.WHITE), 'b');
        pieceSymbol.put(new Queen(Color.WHITE), 'q');
        pieceSymbol.put(new King(Color.WHITE), 'k');
        pieceSymbol.put(new Pawn(Color.WHITE), 'p');
    }

    public static char getSymbol(final Piece piece) {
        return pieceSymbol.get(piece);
    }
}
