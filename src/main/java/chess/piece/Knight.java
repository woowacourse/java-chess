package chess.piece;

import chess.Path;
import chess.Position;
import java.util.Optional;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        return null;
    }
}
