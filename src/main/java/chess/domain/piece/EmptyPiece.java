package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

import java.util.Collections;
import java.util.Map;

public class EmptyPiece extends GamePiece {

    private static final GamePiece instance = new EmptyPiece();

    private EmptyPiece() {
        super(".", Collections.emptyList(), Collections.emptyList(), 0, 0, PlayerColor.NONE);
    }

    public static final GamePiece getInstance() {
        return instance;
    }

    @Override
    public void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
    }
}
