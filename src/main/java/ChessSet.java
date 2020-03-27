import chess.board.Location;
import chess.piece.type.Piece;
import chess.score.Score;
import chess.team.Team;

import java.util.Map;
import java.util.Optional;

public class ChessSet {
    private final Map<Location, Piece> set;

    public ChessSet(Map<Location, Piece> set) {
        this.set = set;
    }

    public Score calculateScoreExceptPawnReduce() {
        return set.values().stream()
                .map(Piece::getScore)
                .reduce(Score::plus)
                .get();
    }
}
