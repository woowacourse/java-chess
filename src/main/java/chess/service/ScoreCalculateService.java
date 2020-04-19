package chess.service;

import chess.db.dao.*;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;

public class ScoreCalculateService {

    private ChessPieceDAO chessPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ScoreCalculateService() {
        chessPieceDAO = new ChessPieceDAO();
        chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public double calculateScore() {
        ChessBoard chessBoard =
                CreateChessBoardService.createChessBoard(chessPieceDAO, chessBoardStateDAO);
        return chessBoard.calculateScore();
    }

    public String getColor() {
        ChessBoard chessBoard =
                CreateChessBoardService.createChessBoard(chessPieceDAO, chessBoardStateDAO);
        PieceColor pieceColor = chessBoard.getPlayerColor();

        return pieceColor.getColor();
    }
}
