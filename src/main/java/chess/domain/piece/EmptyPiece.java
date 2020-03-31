package chess.domain.piece;

import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.player.PlayerColor;

public class EmptyPiece extends GamePiece {

    private static final GamePiece instance = new EmptyPiece();

    private EmptyPiece() {
        super(".", 0, PlayerColor.NONE);
    }

    public static GamePiece getInstance() {
        return instance;
    }

    @Override
    public void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
        throw new UnsupportedOperationException("기물이 존재하지 않습니다.");
    }

    @Override
    public List<Position> getOriginalPositions() {
        throw new UnsupportedOperationException("기물이 존재하지 않습니다.");
    }
}
