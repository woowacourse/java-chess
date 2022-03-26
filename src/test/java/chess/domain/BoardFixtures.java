package chess.domain;

import chess.domain.generator.EmptyBoardGenerator;
import chess.domain.generator.InitBoardGenerator;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.List;

public class BoardFixtures {

    public static ChessBoard generateEmptyChessBoard() {
        return new ChessBoard(new EmptyBoardGenerator());
    }

    public static ChessBoard generateInitChessBoard() {
        ChessBoard chessBoard = new ChessBoard(new InitBoardGenerator());

        return chessBoard;
    }

    public static ChessBoard generatePawnChessBoard() {
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());
        List<List<Piece>> board = chessBoard.getBoard();

        board.get(0).set(0, new Pawn(Color.BLACK));
        board.get(1).set(0, new Pawn(Color.BLACK));
        board.get(2).set(0, new Pawn(Color.BLACK));

        return chessBoard;
    }
}
