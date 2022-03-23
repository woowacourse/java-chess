package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.Objects;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        Objects.requireNonNull(pieces, "pieces는 null이 들어올 수 없습니다.");
        this.pieces = new HashMap<>(pieces);
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
