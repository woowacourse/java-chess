package chess.board;

import static chess.File.A;
import static chess.File.B;
import static chess.File.C;
import static chess.File.D;
import static chess.File.E;
import static chess.File.F;
import static chess.File.G;
import static chess.File.H;
import static chess.Player.BLACK;
import static chess.Player.NONE;
import static chess.Player.WHITE;
import static chess.Rank.EIGHT;
import static chess.Rank.ONE;
import static chess.Rank.SEVEN;
import static chess.Rank.TWO;

import chess.File;
import chess.Position;
import chess.Rank;
import chess.piece.Bishop;
import chess.piece.Blank;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardCreator {

    public static Map<Position, Piece> create() {
        final Map<Position, Piece> board = new HashMap<>();
        initBoard(board);
        createBlackPieces(board);
        createWhitePieces(board);
        return board;
    }

    private static void initBoard(final Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            createBlankIn(board, rank);
        }
    }

    private static void createBlankIn(final Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            board.put(Position.of(rank, file), new Blank(NONE, "."));
        }
    }

    private static void createBlackPieces(final Map<Position, Piece> board) {
        board.put(Position.of(EIGHT, A), new Rook(BLACK, "R"));
        board.put(Position.of(EIGHT, B), new Knight(BLACK, "N"));
        board.put(Position.of(EIGHT, C), new Bishop(BLACK, "B"));
        board.put(Position.of(EIGHT, D), new Queen(BLACK, "Q"));
        board.put(Position.of(EIGHT, E), new King(BLACK, "K"));
        board.put(Position.of(EIGHT, F), new Bishop(BLACK, "B"));
        board.put(Position.of(EIGHT, G), new Knight(BLACK, "N"));
        board.put(Position.of(EIGHT, H), new Rook(BLACK, "R"));
        for (File file : File.values()) {
            board.put(Position.of(SEVEN, file), new Pawn(BLACK, "P"));
        }
    }

    private static void createWhitePieces(final Map<Position, Piece> board) {
        board.put(Position.of(ONE, A), new Rook(WHITE, "r"));
        board.put(Position.of(ONE, B), new Knight(WHITE, "n"));
        board.put(Position.of(ONE, C), new Bishop(WHITE, "b"));
        board.put(Position.of(ONE, D), new Queen(WHITE, "q"));
        board.put(Position.of(ONE, E), new King(WHITE, "k"));
        board.put(Position.of(ONE, F), new Bishop(WHITE, "b"));
        board.put(Position.of(ONE, G), new Knight(WHITE, "n"));
        board.put(Position.of(ONE, H), new Rook(WHITE, "r"));
        for (File file : File.values()) {
            board.put(Position.of(TWO, file), new Pawn(WHITE, "p"));
        }
    }
}
