package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.chessboard.attribute.File;
import chess.domain.chessboard.attribute.Rank;
import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.StartingPawn;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public class Chessboard {

    private final List<List<Square>> chessboard;

    private Chessboard(final List<List<Square>> chessboard) {
        this.chessboard = chessboard;
    }

    public static Chessboard create() {
        List<List<Square>> chessboard = emptySquares();
        putPieces(chessboard);
        return new Chessboard(chessboard);
    }

    public static Chessboard empty() {
        return new Chessboard(emptySquares());
    }

    private static List<List<Square>> emptySquares() {
        List<List<Square>> chessboard = new ArrayList<>();
        for (final Rank ignored : Rank.values()) {
            initialize(chessboard);
        }
        return chessboard;
    }

    private static void initialize(final List<List<Square>> chessboard) {
        List<Square> squares = new ArrayList<>();
        for (final File ignored : File.values()) {
            squares.add(Square.empty());
        }
        chessboard.add(squares);
    }

    private static void putPieces(final List<List<Square>> chessboard) {
        for (final Piece piece : initialPieces()) {
            Position position = piece.position();
            Rank rank = position.rank();
            File file = position.file();
            List<Square> squares = chessboard.get(rank.toRow());
            squares.set(file.toColumn(), Square.from(piece));
        }
    }

    private static Set<Piece> initialPieces() {
        Set<Piece> pieces = new HashSet<>();
        for (Color color : Color.values()) {
            pieces.add(King.ofInitialPosition(color));
            pieces.add(Queen.ofInitialPosition(color));
            pieces.addAll(Bishop.ofInitialPositions(color));
            pieces.addAll(Knight.ofInitialPositions(color));
            pieces.addAll(Rook.ofInitialPositions(color));
            pieces.addAll(StartingPawn.ofInitialPositions(color));
        }
        return pieces;
    }

    public static boolean isInBoard(final int column, final int row) {
        return File.isInRange(column) && Rank.isInRange(row);
    }

    public void move(final Position source, final Position target) {
        validateSource(source);
        Piece sourcePiece = squareIn(source).piece();
        Piece targetPiece = sourcePiece.move(this, target);
        removePieceIn(source);
        put(target, targetPiece);
    }

    private void validateSource(final Position position) {
        if (squareIn(position).isEmpty()) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다: %s".formatted(position));
        }
    }

    public boolean isEmpty(final Position position) {
        return squareIn(position).isEmpty();
    }

    public Square squareIn(final Position position) {
        List<Square> squares = rowIn(position);
        File file = position.file();
        return squares.get(file.toColumn());
    }

    private void removePieceIn(final Position position) {
        Rank rank = position.rank();
        File file = position.file();
        List<Square> squares = chessboard.get(rank.toRow());
        squares.set(file.toColumn(), Square.empty());
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
