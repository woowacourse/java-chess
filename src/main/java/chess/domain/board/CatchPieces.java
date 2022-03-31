package chess.domain.board;

import chess.domain.piece.Team;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class CatchPieces {

    private final List<Piece> blackPieces;
    private final List<Piece> whitePieces;

    public CatchPieces() {
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public void addPiece(final Piece piece) {
        if (piece.isBlank()) {
            return;
        }
        if (piece.getColor() == Team.BLACK) {
            blackPieces.add(piece);
        }
        whitePieces.add(piece);
    }

    public boolean isKingCatch() {
        return blackPieces.stream().anyMatch(Piece::isKing) || whitePieces.stream().anyMatch(Piece::isKing);
    }
}
