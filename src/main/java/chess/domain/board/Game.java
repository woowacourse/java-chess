package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import java.util.List;

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

    public Game move(final Coordinate sourceCoordinate, final Coordinate targetCoordinate) {
        if (!board.hasAvailablePath(sourceCoordinate, targetCoordinate)) {
            throw new IllegalArgumentException("경로 안에서 체스말이 이동할 수 없습니다.");
        }
//        final Board newBoard = board.movePiece(sourceCoordinate, targetCoordinate);
//        return new Game(newBoard);
        return new Game(board.movePiece(sourceCoordinate, targetCoordinate));
    }

    public boolean containsEnemyPiece(final Coordinate sourceCoordinate, final PieceColor enemyColor) {
        return board.hasPieceColor(sourceCoordinate, enemyColor);
    }

    public boolean kingDead() {
        int kingCount = (int) board.values()
                .stream()
                .filter(Position::containsKing)
                .count();

        return kingCount != VALID_KING_COUNT;
    }

    public List<Piece>
    public int pawnCount(final Column column, final PieceColor color) {
        return (int) board.values()
                .stream()
                .filter(position -> position.isOfColumn(column))
                .filter(position -> position.containsPawn())
                .map(position -> position.)
                .filter(position -> position.holdingPieceIsColor(color))
                .count();
    }
}
