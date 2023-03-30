package chess.model.domain.piece;

import chess.model.domain.position.Movement;
import chess.model.domain.position.Position;
import chess.model.exception.EmptyCantExecuteException;

public class Empty extends Piece {

    private static final Empty INSTANCE = new Empty(Color.EMPTY);

    private Empty(final Color color) {
        super(color, PieceType.EMPTY);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public Movement searchMovement(final Position from, final Position to, final Piece destination) {
        throw new EmptyCantExecuteException();
    }
}
