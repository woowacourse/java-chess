package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.generator.InitBoardGenerator;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFixtures {

    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 7;

    public static ChessBoard generateEmptyChessBoard() {
        Board board = new Board(generateEmptyBoard());
        return new ChessBoard(board);
    }

    private static List<List<Piece>> generateEmptyBoard() {
        return IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());
    }

    private static List<Piece> generatePieces() {
        return IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(ignored -> new EmptyPiece())
                .collect(toList());
    }

    public static ChessBoard generateInitChessBoard() {
        return new ChessBoard(new InitBoardGenerator());
    }

    public static ChessBoard generatePawnChessBoard() {
        Board board = new Board(generateEmptyBoard());

        board.place(new Position("a8"), new Pawn(Color.BLACK));
        board.place(new Position("a7"), new Pawn(Color.BLACK));
        board.place(new Position("a6"), new Pawn(Color.BLACK));

        return new ChessBoard(board);
    }

    public static ChessBoard generateWhiteKingChessBoard() {
        Board board = new Board(generateEmptyBoard());

        board.place(new Position("a8"), new King(Color.WHITE));
        board.place(new Position("a7"), new Queen(Color.BLACK));
        board.place(new Position("a6"), new King(Color.BLACK));

        return new ChessBoard(board);
    }

    public static ChessBoard generateBlackKingChessBoard() {
        Board board = new Board(generateEmptyBoard());

        board.place(new Position("a8"), new King(Color.BLACK));
        board.place(new Position("a7"), new Queen(Color.WHITE));
        board.place(new Position("a6"), new King(Color.WHITE));

        return new ChessBoard(board);
    }
}
