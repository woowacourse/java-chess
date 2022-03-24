package domain.piece.unit;

import static domain.utils.Direction.*;

import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class Rook extends CommonMovablePiece {

    private static final List<Direction> directions;

    static {
        directions = upDownLeftRightDirections();
    }

    public Rook(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Rook);
    }

    @Override
    protected List<Direction> directions() {
        return directions;
    }
}
/*
    2-1	- 자신의 턴인지
    2-2	- source != null
    2-3	- source / target 서로다른 팀

    2-4	- 지나가는길 검증
            - knight일때 -> 검증이 필요없다.
            - 나머지 -> 검증]
    source / target
 */
