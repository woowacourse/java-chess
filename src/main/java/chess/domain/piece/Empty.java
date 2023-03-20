package chess.domain.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.Optional;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty(Color.EMPTY);

    private Empty(final Color color) {
        super(color);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public Path searchPathTo(final Position from, final Position to, final Optional<Piece> destination) {
        return null;
    }
}
