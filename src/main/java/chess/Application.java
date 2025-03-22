package chess;

import static chess.Color.*;
import static chess.Column.*;
import static chess.Row.*;

import chess.board.Board;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        Board board = new Board(makeInitialBoard());
    }

    private static Map<Position, Piece> makeInitialBoard() {
        Map<Position, Piece> board = new HashMap<>();
        initPawns(board);
        board.put(new Position(ONE, A), new Rook(WHITE));
        board.put(new Position(ONE, H), new Rook(WHITE));
        board.put(new Position(EIGHT, A), new Rook(BLACK));
        board.put(new Position(EIGHT, H), new Rook(BLACK));

        board.put(new Position(ONE, B), new Knight(WHITE));
        board.put(new Position(ONE, G), new Knight(WHITE));
        board.put(new Position(EIGHT, B), new Knight(BLACK));
        board.put(new Position(EIGHT, G), new Knight(BLACK));

        board.put(new Position(ONE, C), new Bishop(WHITE));
        board.put(new Position(ONE, F), new Bishop(WHITE));
        board.put(new Position(EIGHT, C), new Bishop(BLACK));
        board.put(new Position(EIGHT, F), new Bishop(BLACK));

        board.put(new Position(ONE, D), new Queen(WHITE));
        board.put(new Position(EIGHT, D), new Queen(BLACK));

        board.put(new Position(ONE, E), new King(WHITE));
        board.put(new Position(EIGHT, E), new King(BLACK));

        return board;
    }

    private static void initPawns(Map<Position, Piece> board) {
        for (Column column : Column.values()) {
            board.put(new Position(TWO, column), new Pawn(WHITE));
            board.put(new Position(SEVEN, column), new Pawn(BLACK));
        }
    }
}
