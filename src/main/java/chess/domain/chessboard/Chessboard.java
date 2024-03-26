package chess.domain.chessboard;

import java.util.List;

import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.attribute.Position;

public class Chessboard {

    private final List<List<Square>> chessboard;

    protected Chessboard(final List<List<Square>> chessboard) {
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

    public Square squareIn(final Position position) {
        List<Square> row = rowIn(position);
        File file = position.file();
        return row.get(file.toColumn());
    }

    public boolean isEmpty(final Position position) {
        return squareIn(position).isEmpty();
    }

    private void validateSource(final Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다: %s".formatted(position));
        }
    }

    private void remove(final Position position) {
        List<Square> row = rowIn(position);
        File file = position.file();
        row.set(file.toColumn(), Square.empty());
    }

    private void put(final Position position, final Piece piece) {
        List<Square> row = rowIn(piece.position());
        File file = position.file();
        row.set(file.toColumn(), Square.from(piece));
    }

    private List<Square> rowIn(final Position position) {
        Rank rank = position.rank();
        return chessboard.get(rank.toRow());
    }

    public List<List<Square>> getSquares() {
        return List.copyOf(chessboard.stream()
                .map(List::copyOf)
                .toList());
    }
}
