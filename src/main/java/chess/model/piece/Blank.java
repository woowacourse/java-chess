package chess.model.piece;

import static chess.model.Team.NONE;

import chess.model.position.Position;
import java.util.Map;

public class Blank extends Piece {

    public Blank() {
        super(NONE);
    }

    @Override
    public String getSymbol() {
        return "NONE";
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
