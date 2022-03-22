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

    public static List<Piece> createNewWhitePieces() {
        List<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(new Rook(Color.WHITE, new Position('a', '1')));
        whitePieces.add(new Knight(Color.WHITE, new Position('b', '1')));
        whitePieces.add(new Bishop(Color.WHITE, new Position('c', '1')));
        whitePieces.add(new Queen(Color.WHITE, new Position('d', '1')));
        whitePieces.add(new King(Color.WHITE, new Position('e', '1')));
        whitePieces.add(new Bishop(Color.WHITE, new Position('f', '1')));
        whitePieces.add(new Knight(Color.WHITE, new Position('g', '1')));
        whitePieces.add(new Rook(Color.WHITE, new Position('h', '1')));

        for (char i = 0; i < 8; i++) {
            whitePieces.add(new Pawn(Color.WHITE, new Position((char) ('a' + i), '2')));
        }
        return whitePieces;
    }
}
