package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.Map;
import java.util.Set;

public class GameResult {

    private final Set<Pieces> piecesByFile;

    private GameResult(final Set<Pieces> piecesByFile) {
        this.piecesByFile = piecesByFile;
    }

    public static GameResult from(final Map<Position, Piece> piecesByPosition) {
        return new GameResult(collectPiecesByFile(piecesByPosition));
    }

    private static Set<Pieces> collectPiecesByFile(final Map<Position, Piece> piecesByPosition) {
        return File.collectPiecesByFile(piecesByPosition);
    }

    public double calculateScoreOfTeam(final TeamColor color) {
        return piecesByFile.stream()
            .map(pieces -> pieces.calculateScoreOfTeam(color))
            .reduce(Score.INITIAL_SCORE, Score::add)
            .getValue();
    }

}
