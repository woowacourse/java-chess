package chess.model.piece;

import chess.model.Team;
import chess.model.position.Position;
import java.util.Map;

public class Blank extends Piece {

    public Blank(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        throw new IllegalArgumentException("[ERROR] 선택한 위치에 기물이 없습니다.");
    }

    @Override
    public double addTo(final double score) {
        throw new IllegalArgumentException("[ERROR] 더 할 수 없습니다.");
    }
}
