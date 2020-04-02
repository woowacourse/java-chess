package chess.domain.piece;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

public class EmptyPiece extends GamePiece {

    private static final GamePiece instance = new EmptyPiece();

    private EmptyPiece() {
        super(".", 0, PlayerColor.NONE, Collections.EMPTY_LIST, 0);
    }

    public static GamePiece getInstance() {
        return instance;
    }

    @Override
    public List<Position> getOriginalPositions() {
        throw new UnsupportedOperationException("기물이 존재하지 않습니다.");
    }
}
