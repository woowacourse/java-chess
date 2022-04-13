package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

import chess.domain.position.Position;

public class Board {

    private static final String NO_PIECE = "해당 위치에 말이 없습니다.";

    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public Piece findPiece(Position now) {
        if (pieces.containsKey(now)) {
            return pieces.get(now);
        }
        throw new IllegalArgumentException(NO_PIECE);
    }

    public void movePiece(Position from, Position to) {
        Piece fromPiece = findPiece(from);
        fromPiece.validateMove(from, to, pieces);
        pieces.remove(from);
        pieces.put(to, fromPiece);
    }

    public boolean isKingNotExist(Color color) {
        return this.pieces.values().stream()
                .noneMatch(piece -> piece.isKing() && piece.isSameColor(color));
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
