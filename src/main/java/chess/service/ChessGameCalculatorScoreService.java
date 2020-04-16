package chess.service;

import chess.db.dao.BlackPieceDAO;
import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.db.dao.WhitePieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;

public class ChessGameCalculatorScoreService {

    private PieceDAO whitePieceDAO;
    private PieceDAO blackPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ChessGameCalculatorScoreService() {
        this.whitePieceDAO = new WhitePieceDAO();
        this.blackPieceDAO = new BlackPieceDAO();
        chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public double calculateScore() {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(whitePieceDAO, blackPieceDAO, chessBoardStateDAO);
        return chessBoard.calculateScore();
    }

    public String getColor() {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(whitePieceDAO, blackPieceDAO, chessBoardStateDAO);
        PieceColor pieceColor = chessBoard.getPlayerColor();

        return pieceColor.getColor();
    }
}
