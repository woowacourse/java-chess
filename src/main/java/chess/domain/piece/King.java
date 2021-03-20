package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Cross;
import chess.domain.Diagonal;
import chess.domain.Name;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final Score SCORE = new Score(0);
    private static final Position INITIAL_BLACK_POSITION = Position.of('e', '8');
    private static final Position INITIAL_WHITE_POSITION = Position.of('e', '1');

    public King(Position position, Color color) {
        super(position, Name.KING, color, SCORE);
    }

    public King(Position position, Name name, Color color, Score score) {
        super(position, name, color, score);
    }

    public static List<King> initialKings() {
        List<King> kings = new ArrayList();
        kings.add(new King(INITIAL_BLACK_POSITION, Color.BLACK));
        kings.add(new King(INITIAL_WHITE_POSITION, Color.WHITE));
        return kings;
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
// 십자인지 확인
        if (!this.position.isCross(target) && !this.position.isDiagonal(target) ||
                !(this.position.subtractX(target) == 1 || this.position.subtractY(target) == 1)) {
            throw new IllegalArgumentException("[ERROR] 퀸 이동 규칙에 어긋납니다.");
        }
        if (this.position.isCross(target)) {
            Cross queenCross = Cross.findCrossByTwoPosition(this.position, target);
            // 경로에 장애물이 있는지 확인
            queenCross.hasPieceInPath(this.position, target, currentPieces);
            // 우리편 말이 있으면 예외
            Piece targetPiece = currentPieces.findByPosition(target);
            if (this.color.isSame(targetPiece.color)) {
                throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
            }
            if (!(targetPiece instanceof Empty)) {
                currentPieces.removePieceByPosition(target);
            }
        }
        if (this.position.isDiagonal(target)) {
            Diagonal queenDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
            // 경로에 장애물이 있는지 확인
            queenDiagonal.hasPieceInPath(this.position, target, currentPieces);
            // 우리편 말이 있으면 예외
            Piece targetPiece = currentPieces.findByPosition(target);
            if (this.color.isSame(targetPiece.color)) {
                throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
            }
            if (!(targetPiece instanceof Empty)) {
                currentPieces.removePieceByPosition(target);
            }
        }
        this.position = target;
    }
}
