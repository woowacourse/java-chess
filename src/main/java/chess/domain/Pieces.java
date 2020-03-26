package chess.domain;

import java.util.List;

public class Pieces {
    private List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }


    public double sumScore() {
        double totalScore = 0;

        for (Piece piece : pieces) {
            totalScore += piece.getScore();
        }

        return totalScore;
    }
}
