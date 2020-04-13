package chess.domain.piece;

import java.util.Collections;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

public class EmptyPiece extends GamePiece {

    private static final GamePiece instance = new EmptyPiece();
    private static final String NAME = ".";
    private static final int SCORE = 0;
    private static final int MOVE_COUNT = 0;

    private EmptyPiece() {
        super(NAME, SCORE, PlayerColor.NONE, Collections.EMPTY_LIST, MOVE_COUNT);
    }

    public static GamePiece getInstance() {
        return instance;
    }

    @Override
    public List<Position> getOriginalPositions() {
        throw new UnsupportedOperationException("기물이 존재하지 않습니다.");
    }
}
