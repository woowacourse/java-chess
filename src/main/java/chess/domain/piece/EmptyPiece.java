package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

import java.util.Collections;
import java.util.Map;

public class EmptyPiece extends newGamePiece {

    private static final newGamePiece instance = new EmptyPiece();

    private EmptyPiece() {
        super(".", Collections.emptyList(), Collections.emptyList(), 0, 0, PlayerColor.NONE);
    }

    public static final newGamePiece getInstance() {
        return instance;
    }

    @Override
    public void validatePath(Map<Position, newGamePiece> board, Position source, Position target) {
    }
}
