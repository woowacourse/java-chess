package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    private ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard createNewChessBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        PieceFactory.createNewBlackPieces()
                .forEach(piece -> pieces.put(piece.getPosition(), piece));
        PieceFactory.createNewWhitePieces()
                .forEach(piece -> pieces.put(piece.getPosition(), piece));
        return new ChessBoard(pieces);
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
