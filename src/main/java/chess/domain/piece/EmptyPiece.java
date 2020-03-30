package chess.domain.piece;

import java.util.Collections;
import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

public class EmptyPiece extends GamePiece {

    private static final GamePiece instance = new EmptyPiece();

    private EmptyPiece() {
        super("EMPTY", Collections.emptyList(), 0, PlayerColor.NONE);
    }

    public static GamePiece getInstance() {
        return instance;
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        throw new UnsupportedOperationException("기물이 존재하지 않습니다.");
    }
}
