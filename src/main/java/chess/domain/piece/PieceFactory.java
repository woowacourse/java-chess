package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.Position;

public class PieceFactory {

    private PieceFactory() {
    }

    public static List<Piece> createNewBlackPieces() {
        List<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(new Rook(Color.BLACK, new Position('a', '8')));
        blackPieces.add(new Knight(Color.BLACK, new Position('b', '8')));
        blackPieces.add(new Bishop(Color.BLACK, new Position('c', '8')));
        blackPieces.add(new Queen(Color.BLACK, new Position('d', '8')));
        blackPieces.add(new King(Color.BLACK, new Position('e', '8')));
        blackPieces.add(new Bishop(Color.BLACK, new Position('f', '8')));
        blackPieces.add(new Knight(Color.BLACK, new Position('g', '8')));
        blackPieces.add(new Rook(Color.BLACK, new Position('h', '8')));

        for (char i = 0; i < 8; i++) {
            blackPieces.add(new Pawn(Color.BLACK, new Position((char) ('a' + i), '7')));
        }
        return blackPieces;
    }
}
