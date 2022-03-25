package chess.domain;

import chess.domain.generator.EmptyBoardGenerator;

public class BoardFixtures {

    public static ChessBoard generateEmptyChessBoard() {
        return new ChessBoard(new EmptyBoardGenerator());
    }

    public static ChessBoard generateInitChessBoard() {
        ChessBoard chessBoard = new ChessBoard(new EmptyBoardGenerator());
        chessBoard.init();

        return chessBoard;
    }
}
