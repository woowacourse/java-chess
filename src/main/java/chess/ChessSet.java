package chess;

import chess.board.Location;
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

    public Piece remove(Location location) {
        return chessSet.remove(location);
    }

    public boolean hasNotKing() {
        return chessSet.values().stream()
                .anyMatch(Piece::isKing) == false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        chessSet.keySet().stream()
                .forEach(location -> sb.append(location.getCol() + ", " + location.getRow() + " : " + chessSet.get(location).getName() + "\n"));
        return sb.toString();
    }
}
