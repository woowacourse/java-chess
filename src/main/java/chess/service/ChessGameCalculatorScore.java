package chess.service;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessGameCalculatorScore extends createBoard {

    public ChessGameCalculatorScore() {
        super();
    }

    public double calculateScore() {
        ChessBoard chessBoard = createChessBoard();
        return chessBoard.calculateScore();
    }

    public String getColor() {
        ChessBoard chessBoard = createChessBoard();
        PieceColor pieceColor = chessBoard.getPlayerColor();

        return pieceColor.getColor();
    }
}
