package chess.controller.dao;

import chess.domain.position.File;
import chess.domain.position.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PieceOnBoards {
    private List<PieceOnBoard> pieceOnBoards;

    private PieceOnBoards(List<PieceOnBoard> pieceOnBoards) {
        this.pieceOnBoards = pieceOnBoards;
    }

    public static PieceOnBoards of(List<PieceOnBoard> pieceOnBoards) {
        return new PieceOnBoards(pieceOnBoards);
    }

    public List<PieceOnBoard> getPieceOnBoards() {
        return Collections.unmodifiableList(this.pieceOnBoards);
    }

    public Optional<PieceOnBoard> find(String target) {
        String file = File.valueOf(target.substring(0, 1).toUpperCase()).name();
        String rank = Rank.of(target.substring(1)).name();
        String position = file + rank;
        Optional<PieceOnBoard> targetPiece = Optional.ofNullable(this.pieceOnBoards.stream()
                .filter(p -> p.getPosition().equals(position))
                .findFirst()
                .orElse(null));

        return targetPiece;
    }
}
