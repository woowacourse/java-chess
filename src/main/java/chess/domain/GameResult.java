package chess.domain;

import chess.domain.piece.Pieces;
import java.util.Set;

public class GameResult {

    private final Set<Pieces> piecesByFile;

    public GameResult(final Set<Pieces> piecesByFile) {
        this.piecesByFile = piecesByFile;
    }

    public double calculateScoreOfTeam(final TeamColor color) {
        return piecesByFile.stream()
            .mapToDouble(pieces -> pieces.calculateScoreOfTeam(color))
            .sum();
    }

}
