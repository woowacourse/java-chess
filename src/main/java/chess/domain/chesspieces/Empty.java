package chess.domain.chesspieces;

import chess.domain.Player;
import chess.domain.position.Position;

public class Empty extends Piece {
    private static final Empty instance = new Empty();

    public Empty() {
        super(Player.NONE, PieceInfo.EMPTY);
    }

    public static Empty getInstance() {
        return instance;
    }

    @Override
    public boolean validateTileSize(Position from, Position to) {
        throw new UnsupportedOperationException("Empty는 이동 여부를 판단할 수 없습니다.");
    }

    @Override
    public boolean movable(Position from, Position to) {
        throw new UnsupportedOperationException("empty는 이동할 수 없습니다.");
    }
}
