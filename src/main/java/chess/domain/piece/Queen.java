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
        validateQueenMove(target);
        if (this.position.isCross(target)) {
            moveCross(target, currentPieces);
        }
        if (this.position.isDiagonal(target)) {
            moveDiagonal(target, currentPieces);
        }
        this.position = target;
    }

    private void validateQueenMove(Position target) {
        if (!this.position.isCross(target) && !this.position.isDiagonal(target)) {
            throw new IllegalArgumentException("[ERROR] 퀸 이동 규칙에 어긋납니다.");
        }
    }

    private void moveCross(Position target, CurrentPieces currentPieces) {
        Cross queenCross = Cross.findCrossByTwoPosition(this.position, target);
        queenCross.hasPieceInPath(this.position, target, currentPieces);
        Piece targetPiece = currentPieces.findByPosition(target);
        validateSameColor(targetPiece);
        currentPieces.removePieceIfNotEmpty(targetPiece);
    }

    private void moveDiagonal(Position target, CurrentPieces currentPieces) {
        Diagonal queenDiagonal = Diagonal.findDiagonalByTwoPosition(this.position, target);
        queenDiagonal.hasPieceInPath(this.position, target, currentPieces);
        Piece targetPiece = currentPieces.findByPosition(target);
        validateSameColor(targetPiece);
        currentPieces.removePieceIfNotEmpty(targetPiece);
    }

    public static List<Queen> generate() {
        List<Queen> queens = new ArrayList();
        queens.add(new Queen(INITIAL_BLACK_POSITION, "Q", Color.BLACK, SCORE));
        queens.add(new Queen(INITIAL_WHITE_POSITION, "q", Color.WHITE, SCORE));
        return queens;
    }
}
