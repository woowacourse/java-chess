package chess.domain.game;

import chess.domain.piece.King;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeadPieces {

    private final List<Piece> blackPieces;
    private final List<Piece> whitePieces;

    public DeadPieces() {
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public void add(final Piece piece) {
        validateKingDead();
        if (piece.isBlank()) {
            return;
        }
        if (piece.getTeam() == Team.BLACK) {
            blackPieces.add(piece);
            return;
        }
        whitePieces.add(piece);
    }

    private void validateKingDead() {
        if (isKingDead()) {
            throw new IllegalStateException("[ERROR] 왕이 이미 사망하여 게임이 끝났습니다.");
        }
    }

    public Team getTeamOfDeadKing() {
        validateKingNotDead();
        if (blackPieces.contains(King.class)) {
            return Team.BLACK;
        }
        if (whitePieces.contains(King.class)) {
            return Team.BLACK;
        }
        return Team.NONE;
    }

    private void validateKingNotDead() {
        if (!isKingDead()) {
            throw new IllegalStateException("[ERROR] 왕이 아직 사망하지 않았습니다.");
        }
    }

    public boolean isKingDead() {
        return blackPieces.stream().anyMatch(Piece::isKing) || whitePieces.stream().anyMatch(Piece::isKing);
    }

    public List<Piece> getBlackPieces() {
        return Collections.unmodifiableList(blackPieces);
    }

    public List<Piece> getWhitePieces() {
        return Collections.unmodifiableList(whitePieces);
    }
}
