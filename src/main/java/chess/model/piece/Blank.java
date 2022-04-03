package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import java.util.Map;

public class Blank extends Piece {

    private final String symbol = "none";

    public Blank(final Team team) {
        super(team);
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        throw new IllegalArgumentException("[ERROR] 선택한 위치에 기물이 없습니다.");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        throw new IllegalArgumentException("[ERROR] 기물의 점수를 계산 할 수 없습니다.");
    }
}
