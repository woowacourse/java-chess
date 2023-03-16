package chess.piece;

import chess.Path;
import chess.Position;
import java.util.Optional;

public class Queen extends Piece {

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Path searchPathTo(final Position to, final Optional<Piece> destination) {
        return null;
    }
}
