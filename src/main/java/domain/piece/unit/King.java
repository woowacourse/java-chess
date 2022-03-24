package domain.piece.unit;

import domain.piece.CommonMovablePiece;
import domain.piece.property.PieceSymbol;
import domain.piece.property.TeamColor;
import domain.utils.Direction;
import java.util.List;

public final class King extends CommonMovablePiece {

    public King(final TeamColor teamColor) {
        super(teamColor, PieceSymbol.King);
    }

    @Override
    public List<Direction> directions() {
        return null;
    }
}
