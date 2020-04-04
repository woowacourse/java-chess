package chess.game;

import chess.location.Location;
import chess.piece.type.Piece;
import chess.score.Score;

import java.util.List;
import java.util.Map;

public class ChessSet {
    private final List<Piece> chessSet;

    public ChessSet(List<Piece> chessSet) {
        this.chessSet = chessSet;
    }

    public Score calculateScoreExceptPawnReduce() {
        return chessSet.stream()
                .map(Piece::getScore)
                .reduce(Score::plus)
                .get();
    }

    public void remove(Piece piece) {
        chessSet.remove(piece);
    }

    public boolean hasNotKing() {
        return chessSet.stream()
                .noneMatch(Piece::isKing);
    }

    public int getSize() {
        return chessSet.size();
    }
}
