package chess.service;

import chess.db.dao.*;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessGameCalculatorScoreService {

    private ChessPieceDAO chessPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ChessGameCalculatorScoreService() {
        chessPieceDAO = new ChessPieceDAO();
        chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public double calculateScore() {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(chessPieceDAO, chessBoardStateDAO);
        return chessBoard.calculateScore();
    }

    public String getColor() {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(chessPieceDAO, chessBoardStateDAO);
        PieceColor pieceColor = chessBoard.getPlayerColor();

        return pieceColor.getColor();
    }
}
