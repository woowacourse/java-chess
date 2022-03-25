package chess;

import chess.piece.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static chess.File.*;
import static chess.Player.*;
import static chess.Rank.*;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        initBoard();
        createBlackPieces();
        createWhitePieces();
    }

    private void initBoard() {
        for (Rank rank : Rank.values()) {
            createBlankIn(rank);
        }
    }

    private void createBlankIn(Rank rank) {
        for (File file : File.values()) {
            board.put(new Position(rank, file), new Blank(NONE, "."));
        }
    }

    private void createBlackPieces() {
        board.put(new Position(EIGHT, A), new Rook(BLACK, "R"));
        board.put(new Position(EIGHT, B), new Knight(BLACK, "N"));
        board.put(new Position(EIGHT, C), new Bishop(BLACK, "B"));
        board.put(new Position(EIGHT, D), new Queen(BLACK, "Q"));
        board.put(new Position(EIGHT, E), new King(BLACK, "K"));
        board.put(new Position(EIGHT, F), new Bishop(BLACK, "B"));
        board.put(new Position(EIGHT, G), new Knight(BLACK, "N"));
        board.put(new Position(EIGHT, H), new Rook(BLACK, "R"));
        for (File file : File.values()) {
            board.put(new Position(SEVEN, file), new Pawn(BLACK, "P"));
        }
    }

    private void createWhitePieces() {
        board.put(new Position(ONE, A), new Rook(WHITE, "r"));
        board.put(new Position(ONE, B), new Knight(WHITE, "n"));
        board.put(new Position(ONE, C), new Bishop(WHITE, "b"));
        board.put(new Position(ONE, D), new Queen(WHITE, "q"));
        board.put(new Position(ONE, E), new King(WHITE, "k"));
        board.put(new Position(ONE, F), new Bishop(WHITE, "b"));
        board.put(new Position(ONE, G), new Knight(WHITE, "n"));
        board.put(new Position(ONE, H), new Rook(WHITE, "r"));
        for (File file : File.values()) {
            board.put(new Position(TWO, file), new Pawn(WHITE, "p"));
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
