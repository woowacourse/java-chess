package domain.piece;

import domain.Square;
import java.util.List;

public class Blank extends Piece {

    private static final Blank instance = new Blank();

    private Blank() {
        super(TeamColor.EMPTY, PieceInfo.BLANK);
    }

    public static Blank getInstance() {
        return instance;
    }

    @Override
    public List<Square> findRoutes(Square src, Square dest) {
        throw new IllegalStateException("기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isBlack() {
        throw new IllegalStateException("기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isDifferentTeam(Piece piece) {
        throw new IllegalStateException("기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isSameColor(TeamColor color) {
        throw new IllegalStateException("기물이 존재하지 않습니다.");
    }

    @Override
    public boolean isPawn() {
        throw new IllegalStateException("기물이 존재하지 않습니다.");
    }
}
