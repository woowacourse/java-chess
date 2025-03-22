package chess.board;

import chess.Position;
import chess.piece.LivePiece;
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

}
