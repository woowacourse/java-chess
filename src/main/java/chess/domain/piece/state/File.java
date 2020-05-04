package chess.domain.piece.state;

import chess.domain.piece.Piece;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.List;

class File {
    private final List<Piece> pieces;

    File(List<Piece> pieces) {
        this.pieces = pieces;
    }

    Score calculateScoreOf(Team team) {
        Score sum = Score.zero();
        for (Piece piece : pieces) {
            sum = addScoreOfSameTeam(team, sum, piece);
        }

        return sum;
    }

    private Score addScoreOfSameTeam(Team team, Score sum, Piece piece) {
        if (piece.isSameTeam(team)) {
            Score score = piece.calculateScore(() -> hasMultiplePawn(team));
            sum = sum.add(score);
        }
        return sum;
    }

    private boolean hasMultiplePawn(Team team) {
        long pawnSize = pieces.stream()
                .filter(piece -> piece.isSameTeam(team))
                .filter(Piece::isPawn)
                .count();
        return 1 < pawnSize;
    }
}
