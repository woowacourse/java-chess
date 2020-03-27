import chess.board.Location;
import chess.piece.type.King;
import chess.piece.type.Piece;
import chess.score.Score;

import java.util.Map;

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

    public boolean contain(Location location) {
        return chessSet.containsKey(location);
    }

    public void remove(Location location) {
        chessSet.remove(location);
    }

    public boolean hasNotKing() {
        return chessSet.values().stream()
                .anyMatch(Piece::isKing) == false;

    }
}
