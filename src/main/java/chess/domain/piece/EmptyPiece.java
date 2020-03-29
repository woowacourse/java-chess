package chess.domain.piece;

import java.util.Collections;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

public class EmptyPiece extends GamePiece {

    private static final GamePiece instance = new EmptyPiece();

    private EmptyPiece() {
        super(".", Collections.emptyList(), 0, PlayerColor.NONE);
    }

    public static GamePiece getInstance() {
        return instance;
    }

    @Override
    public void validatePath(Map<Position, GamePiece> board, Position source, Position target) {}
}
