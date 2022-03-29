package chess.model.piece;

import chess.model.Team;

public class Blank extends Piece {

    public Blank(Team team, String symbol) {
        super(team, symbol);
    }

    @Override
    public double addTo(double score) {
        throw new IllegalArgumentException("[ERROR] 더 할 수 없습니다.");
    }
}
