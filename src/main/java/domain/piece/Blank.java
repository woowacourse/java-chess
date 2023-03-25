package domain.piece;

import domain.Square;
import java.util.List;

public class Blank extends Piece {

    private static final Blank instance = new Blank();
    private static final String NO_EXIST_MSG = "기물이 존재하지 않습니다.";

    private Blank() {
        super(TeamColor.EMPTY, PieceInfo.BLANK);
    }

    public static Blank getInstance() {
        return instance;
    }

    @Override
    public List<Square> findRoutes(Square source, Square destination) {
        throw new IllegalStateException(NO_EXIST_MSG);
    }

    @Override
    public boolean isBlack() {
        throw new IllegalStateException(NO_EXIST_MSG);
    }

    @Override
    public boolean isDifferentTeam(Piece piece) {
        throw new IllegalStateException(NO_EXIST_MSG);
    }

    @Override
    public boolean isSameColor(TeamColor color) {
        throw new IllegalStateException(NO_EXIST_MSG);
    }

    @Override
    public boolean isPawn() {
        throw new IllegalStateException(NO_EXIST_MSG);
    }
}
