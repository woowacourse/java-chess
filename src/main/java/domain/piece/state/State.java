package domain.piece.state;

import domain.piece.info.Color;
import domain.piece.info.Direction;
import java.util.List;

public interface State {
    List<Direction> movableDirection(final Color color);
}
