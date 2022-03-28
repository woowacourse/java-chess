package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.generator.InitBoardGenerator;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFixtures {

    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 7;

    public static ChessBoard generateEmptyChessBoard() {
        return new ChessBoard(() -> generateEmptyBoard());
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
        ChessBoard chessBoard = new ChessBoard(new InitBoardGenerator());

        return chessBoard;
    }

    public static ChessBoard generatePawnChessBoard() {
        ChessBoard chessBoard = new ChessBoard(() -> generateEmptyBoard());
        List<List<Piece>> board = chessBoard.getBoard();

        board.get(0).set(0, new Pawn(Color.BLACK));
        board.get(1).set(0, new Pawn(Color.BLACK));
        board.get(2).set(0, new Pawn(Color.BLACK));

        return chessBoard;
    }

    public static ChessBoard generateWhiteKingChessBoard() {
        ChessBoard chessBoard = new ChessBoard(() -> generateEmptyBoard());
        List<List<Piece>> board = chessBoard.getBoard();

        board.get(0).set(0, new King(Color.WHITE));
        board.get(1).set(0, new Queen(Color.BLACK));
        board.get(2).set(0, new King(Color.BLACK));

        return chessBoard;
    }

    public static ChessBoard generateBlackKingChessBoard() {
        ChessBoard chessBoard = new ChessBoard(() -> generateEmptyBoard());
        List<List<Piece>> board = chessBoard.getBoard();

        board.get(0).set(0, new King(Color.BLACK));
        board.get(1).set(0, new Queen(Color.WHITE));
        board.get(2).set(0, new King(Color.WHITE));

        return chessBoard;
    }
}
