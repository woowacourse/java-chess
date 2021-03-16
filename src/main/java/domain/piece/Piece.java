package domain.piece;

import java.util.List;

public interface Piece {
    boolean canMove(List<String> from, List<String> to);
}
