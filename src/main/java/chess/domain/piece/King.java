package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Name;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import static chess.domain.piece.info.Score.ZERO;

public class King extends Piece {
    public King(Color color, Position position) {
        super(Name.KING, color, position, ZERO);
    }

    @Override
    public void checkMovable(Piece targetPiece, Direction direction) {
        if (!(Direction.crossDirection().contains(direction) ||
                Direction.diagonalDirection().contains(direction))) {
            throw new IllegalArgumentException("[ERROR] 올바른 방향이 아닙니다.");
        }
        if (!(this.position().xDistance(targetPiece.position()) == 1 ||
                this.position().yDistance(targetPiece.position()) == 1)) {
            throw new IllegalArgumentException("[ERROR] 킹 이동 규칙에 어긋납니다.");
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
