package chess.controller.dao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PieceOnBoards {
    private List<PieceOnBoard> pieceOnBoards;

    private PieceOnBoards(List<PieceOnBoard> pieceOnBoards) {
        this.pieceOnBoards = pieceOnBoards;
    }

    public static PieceOnBoards create(List<PieceOnBoard> pieceOnBoards) {
        return new PieceOnBoards(pieceOnBoards);
    }

    public List<PieceOnBoard> getPieceOnBoards() {
        return Collections.unmodifiableList(this.pieceOnBoards);
    }

    public Optional<PieceOnBoard> find(String position) {
        Optional<PieceOnBoard> targetPiece = Optional.ofNullable(this.pieceOnBoards.stream()
                .filter(p -> p.getPosition().equals(position))
                .findFirst()
                .orElse(null));

        return targetPiece;
    }
}
