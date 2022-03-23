package chess.domain.piece;

import chess.domain.Position;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private PieceFactory() {
    }

    public static Map<Position, Piece> createNewChessBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createNewBlackPieces());
        pieces.putAll(createNewWhitePieces());
        return pieces;
    }

    private static Map<Position, Piece> createNewBlackPieces() {
        Map<Position, Piece> blackPieces = new HashMap<>(Map.of(
                new Position('a', '8'), new Rook(Color.BLACK),
                new Position('b', '8'), new Knight(Color.BLACK),
                new Position('c', '8'), new Bishop(Color.BLACK),
                new Position('d', '8'), new Queen(Color.BLACK),
                new Position('e', '8'), new King(Color.BLACK),
                new Position('f', '8'), new Bishop(Color.BLACK),
                new Position('g', '8'), new Knight(Color.BLACK),
                new Position('h', '8'), new Rook(Color.BLACK)
        ));
        for (char i = 0; i < 8; i++) {
            blackPieces.put(new Position((char) ('a' + i), '7'), new Pawn(Color.BLACK));
        }
        return blackPieces;
    }

    private static Map<Position, Piece> createNewWhitePieces() {
        Map<Position, Piece> whitePieces = new HashMap<>(Map.of(
                new Position('a', '1'), new Rook(Color.WHITE),
                new Position('b', '1'), new Knight(Color.WHITE),
                new Position('c', '1'), new Bishop(Color.WHITE),
                new Position('d', '1'), new Queen(Color.WHITE),
                new Position('e', '1'), new King(Color.WHITE),
                new Position('f', '1'), new Bishop(Color.WHITE),
                new Position('g', '1'), new Knight(Color.WHITE),
                new Position('h', '1'), new Rook(Color.WHITE)
        ));
        for (char i = 0; i < 8; i++) {
            whitePieces.put(new Position((char) ('a' + i), '2'), new Pawn(Color.WHITE));
        }
        return whitePieces;
    }
}
