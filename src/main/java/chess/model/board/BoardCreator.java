package chess.model.board;

import static chess.model.Team.BLACK;
import static chess.model.Team.NONE;
import static chess.model.Team.WHITE;
import static chess.model.position.File.A;
import static chess.model.position.File.B;
import static chess.model.position.File.C;
import static chess.model.position.File.D;
import static chess.model.position.File.E;
import static chess.model.position.File.F;
import static chess.model.position.File.G;
import static chess.model.position.File.H;
import static chess.model.position.Rank.EIGHT;
import static chess.model.position.Rank.ONE;
import static chess.model.position.Rank.SEVEN;
import static chess.model.position.Rank.TWO;

import chess.model.piece.Bishop;
import chess.model.piece.BlackPawn;
import chess.model.piece.Blank;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.WhitePawn;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
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
            board.put(Position.of(SEVEN, file), new BlackPawn(BLACK, "P"));
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
            board.put(Position.of(TWO, file), new WhitePawn(WHITE, "p"));
        }
    }
}
