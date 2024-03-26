package chess.domain.chessboard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.chessboard.attribute.Rank;
import chess.domain.chessboard.attribute.Squares;
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
        return new Chessboard(emptyBoard());
    }

    public static Chessboard create() {
        List<Squares> chessboard = emptyBoard();
        putInitialPieces(chessboard);
        return new Chessboard(chessboard);
    }

    public static Chessboard of(final Piece... pieces) {
        List<Squares> chessboard = emptyBoard();
        for (final Piece piece : pieces) {
            Position position = piece.position();
            Rank rank = position.rank();
            Squares squares = chessboard.get(rank.toRow());
            squares.put(position, piece);
        }
        return new Chessboard(chessboard);
    }

    private static List<Squares> emptyBoard() {
        return Arrays.stream(Rank.values())
                .map(__ -> Squares.empty())
                .toList();
    }

    private static void putInitialPieces(final List<Squares> chessboard) {
        for (final Piece piece : initialPieces()) {
            Position position = piece.position();
            Rank rank = position.rank();
            Squares squares = chessboard.get(rank.toRow());
            squares.put(position, piece);
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
