package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

import java.util.Collections;

public class EmptyPiece extends GamePiece {

    private static final GamePiece instance = new EmptyPiece();

    private EmptyPiece() {
        super(".", Collections.emptyList(), 0, PlayerColor.NONE);
    }

    public static GamePiece getInstance() {
        return instance;
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        throw new UnsupportedOperationException("기물이 존재하지 않습니다.");
    }
}
