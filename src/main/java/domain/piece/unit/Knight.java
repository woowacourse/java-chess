package domain.piece.unit;

import domain.piece.CommonMovablePiece;
import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class Knight extends CommonMovablePiece {

    public Knight(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.Knight);
    }

    @Override
    public List<Direction> directions() {
        return null;
    }
}
