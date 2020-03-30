package chess.game;

import chess.location.Location;
import chess.piece.type.Piece;
import chess.score.Score;

import java.util.Map;
import java.util.Set;

public class ChessSet {
    private final Map<Location, Piece> chessSet;

    public ChessSet(Map<Location, Piece> chessSet) {
        this.chessSet = chessSet;
    }

    public Score calculateScoreExceptPawnReduce() {
        return chessSet.values().stream()
                .map(Piece::getScore)
                .reduce(Score::plus)
                .get();
    }

    public Piece remove(Location location) {
        return chessSet.remove(location);
    }

    public boolean hasNotKing() {
        return chessSet.values().stream()
                .noneMatch(Piece::isKing);
    }
}
