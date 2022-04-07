package chess.domain.piece;

import chess.dto.PieceInfo;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private final List<Piece> pieces = new ArrayList<>();

    public Pieces() {
        for (Color color : Color.values()) {
            addPieces(color);
        }
    }

    private void addPieces(final Color color) {
        pieces.add(new Pawn(color));
        pieces.add(new Rook(color));
        pieces.add(new Knight(color));
        pieces.add(new Bishop(color));
        pieces.add(new Queen(color));
        pieces.add(new King(color));
    }

    public Piece getPiece(final PieceInfo pieceInfo) {
        return pieces.stream()
                .filter(piece -> piece.isSamePieceInfo(pieceInfo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피스 심볼 정보입니다."));
    }
}
