package chess.domain.board;

import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Notation;

public final class Game {

    private static final int VALID_KING_COUNT = 2;

    private final Board board;

    public Game(Board board) {
        this.board = board;
    }

    public static Game initiate() {
        // todo
        return new Game(Board.ofEmpty());
    }

    public Game move(final Notation sourceNotation, final Notation targetNotation) {
        if (!board.hasAvailablePath(sourceNotation, targetNotation)) {
            throw new IllegalArgumentException("경로 안에서 체스말이 이동할 수 없습니다.");
        }
        return new Game(board.movePiece(sourceNotation, targetNotation));
    }

    public boolean containsEnemyPiece(final Notation sourceNotation, final PieceColor enemyColor) {
        return board.hasPieceColor(sourceNotation, enemyColor);
    }

    public boolean isGameOver() {
        return board.kingCount() != VALID_KING_COUNT;
    }

    public int pawnCount(final Column column, final PieceColor color) {
        return board.pawnCount(column, color);
    }
}
