package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;

import java.util.ArrayList;
import java.util.List;

public class King extends Move {
    private static final Score SCORE = new Score(0);
    private static final Position INITIAL_BLACK_POSITION = Position.of('e', '8');
    private static final Position INITIAL_WHITE_POSITION = Position.of('e', '1');

    public King(Position position, String name, Color color) {
        super(position, name, color);
    }

    public King(Position position, String name, Color color, Score score) {
        super(position, name, color, score);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        validateKingMove(target);
        if (this.position.isCross(target)) {
            moveCross(target, currentPieces);
        }
        if (this.position.isDiagonal(target)) {
            moveDiagonal(target, currentPieces);
        }
        this.position = target;
    }

    private void validateKingMove(Position target) {
        if (!this.position.isCross(target) && !this.position.isDiagonal(target) ||
                !(this.position.subtractX(target) == 1 || this.position.subtractY(target) == 1)) {
            throw new IllegalArgumentException("[ERROR] 킹 이동 규칙에 어긋납니다.");
        }
    }

    public static List<King> generate() {
        List<King> kings = new ArrayList();
        kings.add(new King(INITIAL_BLACK_POSITION, "K", Color.BLACK, SCORE));
        kings.add(new King(INITIAL_WHITE_POSITION, "k", Color.WHITE, SCORE));
        return kings;
    }
}
