package chess.domain.piece.state;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.policy.score.CalculateScoreStrategy;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.List;

public class File {
    private final List<Piece> pieces;

    File(List<Piece> pieces) {
        this.pieces = pieces;
    }

    Score calculateScoreOf(Team team) {
        Score sum = Score.zero();
        CalculateScoreStrategy calculateScoreStrategy = createCalculateStrategy(team);
        for (Piece piece : pieces) {
            //todo: refac
            if (piece.isSameTeam(team)) {
                Score score = piece.calculateScore(calculateScoreStrategy);
                sum = sum.add(score);
            }
        }

        return sum;
    }

    private CalculateScoreStrategy createCalculateStrategy(Team team) {
        return () -> hasMultiplePawn(team);
    }

    private boolean hasMultiplePawn(Team team) {
        long pawnSize = pieces.stream()
                .filter(piece -> piece.isSameTeam(team))
                .filter(piece -> piece instanceof Pawn)
                .count();
        return 1 < pawnSize;
    }
}
