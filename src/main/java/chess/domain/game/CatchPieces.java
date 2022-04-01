package chess.domain.game;

import chess.domain.piece.Team;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
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
        if (piece.getTeam() == Team.BLACK) {
            blackPieces.add(piece);
            return;
        }
        whitePieces.add(piece);
    }

    public boolean isKingCatch() {
        return blackPieces.stream().anyMatch(Piece::isKing) || whitePieces.stream().anyMatch(Piece::isKing);
    }

    public List<Piece> getBlackPieces() {
        return Collections.unmodifiableList(blackPieces);
    }

    public List<Piece> getWhitePieces() {
        return Collections.unmodifiableList(whitePieces);
    }
}
