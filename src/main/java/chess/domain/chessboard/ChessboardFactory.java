package chess.domain.chessboard;

import java.util.ArrayList;
import java.util.Collections;
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

public class ChessboardFactory {

    private ChessboardFactory() {
    }

    public static Chessboard empty() {
        return new Chessboard(emptySquares());
    }

    public static Chessboard create() {
        List<List<Square>> chessboard = emptySquares();
        putInitialPieces(chessboard);
        return new Chessboard(chessboard);
    }

    public static Chessboard of(final Piece... pieces) {
        List<List<Square>> chessboard = emptySquares();
        for (final Piece piece : pieces) {
            Position position = piece.position();
            Rank rank = position.rank();
            File file = position.file();
            List<Square> row = chessboard.get(rank.toRow());
            row.set(file.toColumn(), Square.from(piece));
        }
        return new Chessboard(chessboard);
    }

    private static List<List<Square>> emptySquares() {
        List<List<Square>> chessboard = new ArrayList<>();
        for (final Rank ignored : Rank.values()) {
            initialize(chessboard);
        }
        return Collections.unmodifiableList(chessboard);
    }

    private static void initialize(final List<List<Square>> chessboard) {
        List<Square> squares = new ArrayList<>();
        for (final File ignored : File.values()) {
            squares.add(Square.empty());
        }
        chessboard.add(squares);
    }

    private static void putInitialPieces(final List<List<Square>> chessboard) {
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
}
