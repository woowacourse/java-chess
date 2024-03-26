package chess.domain.chessboard;

import java.util.List;

import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.chessboard.attribute.Square;
import chess.domain.chessboard.attribute.Squares;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Position;

public class Chessboard {

    private final List<Squares> chessboard;

    protected Chessboard(final List<Squares> chessboard) {
        this.chessboard = chessboard;
    }

    public static boolean isInBoard(final int column, final int row) {
        return File.isInRange(column) && Rank.isInRange(row);
    }

    public void move(final Position source, final Position target) {
        validateSource(source);
        Piece sourcePiece = squareIn(source).piece();
        Piece targetPiece = sourcePiece.move(this, target);
        remove(source);
        put(target, targetPiece);
    }

    private void validateSource(final Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다: %s".formatted(position));
        }
    }

    public boolean isEmpty(final Position position) {
        return squareIn(position).isEmpty();
    }

    private void remove(final Position position) {
        Rank rank = position.rank();
        Squares squares = chessboard.get(rank.toRow());
        squares.remove(position);
    }

    private void put(final Position position, final Piece piece) {
        Squares squares = squaresIn(piece.position());
        squares.put(position, piece);
    }

    public Square squareIn(final Position position) {
        Squares squares = squaresIn(position);
        return squares.squareIn(position);
    }

    private Squares squaresIn(final Position position) {
        Rank rank = position.rank();
        return chessboard.get(rank.toRow());
    }

    public List<List<Square>> getSquares() {
        return List.copyOf(chessboard.stream()
                .map(Squares::getSquares)
                .toList());
    }
}
