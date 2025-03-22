package chess.board;

import chess.Position;
import chess.piece.LivePiece;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pieces {
    private final List<LivePiece> pieces;

    public Pieces(List<LivePiece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public LivePiece getPiece(Position position) {
        return pieces.stream()
                .filter(lp -> lp.isSamePosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물 없음"));
    }

    public List<LivePiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    public boolean isEmpty(Position position) {
        return pieces.stream()
                .noneMatch(lp -> lp.isSamePosition(position));
    }

    public boolean isSameColor(Position end, Piece piece) {

        return pieces.stream()
                .filter(lp -> lp.isSamePosition(end))
                .anyMatch(lp -> lp.isSameColor(piece));
    }
}
