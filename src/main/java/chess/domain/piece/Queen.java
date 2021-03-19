package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Cross;
import chess.domain.Diagonal;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private static final Score SCORE = new Score(9);
    private static final Position INITIAL_BLACK_POSITION = Position.of('d', '8');
    private static final Position INITIAL_WHITE_POSITION = Position.of('d', '1');

    public Queen(Position position, String name, Color color) {
        super(position, name, color);
    }

    public Queen(Position position, String name, Color color, Score score) {
        super(position, name, color, score);
    }

    @Override
    public void move(Position target, CurrentPieces currentPieces) {
        // 십자인지 확인
        if (!this.position.isCross(target) && !this.position.isDiagonal(target)) {
            throw new IllegalArgumentException("[ERROR] 퀸 이동 규칙에 어긋납니다.");
        }
        if (this.position.isCross(target)) {
            Cross queenCross = Cross.findCrossByTwoPosition(this.position, target);
            // 경로에 장애물이 있는지 확인
            queenCross.hasPieceInPath(this.position, target, currentPieces);

            // 우리편 말이 있으면 예외
            Piece targetPiece = currentPieces.findByPosition(target);
            if ((Character.isUpperCase(this.name.charAt(0)) && Character.isUpperCase(targetPiece.name.charAt(0))) ||
                    (Character.isLowerCase(this.name.charAt(0)) && Character.isLowerCase(targetPiece.name.charAt(0)))) {
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
            if ((Character.isUpperCase(this.name.charAt(0)) && Character.isUpperCase(targetPiece.name.charAt(0))) ||
                    (Character.isLowerCase(this.name.charAt(0)) && Character.isLowerCase(targetPiece.name.charAt(0)))) {
                throw new IllegalArgumentException("[ERROR] taget에 같은 편 말이 있습니다.");
            }
            if (!(targetPiece instanceof Empty)) {
                currentPieces.removePieceByPosition(target);
            }
        }
        this.position = target;
    }

    public static List<Queen> generate() {
        List<Queen> queens = new ArrayList();
        queens.add(new Queen(INITIAL_BLACK_POSITION, "Q", Color.BLACK, SCORE));
        queens.add(new Queen(INITIAL_WHITE_POSITION, "q", Color.WHITE, SCORE));
        return queens;
    }
}
