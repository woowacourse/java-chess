package chess.service;

import chess.db.dao.BlackPieceDAO;
import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.db.dao.WhitePieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.CreateBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;

import java.util.Map;

public class createBoard {

    protected PieceDAO blackPieceDAO;
    protected PieceDAO whitePieceDAO;
    protected ChessBoardStateDAO chessBoardStateDAO;

    protected createBoard() {
        this.blackPieceDAO = new BlackPieceDAO();
        this.whitePieceDAO = new WhitePieceDAO();
        this.chessBoardStateDAO = new ChessBoardStateDAO();
    }

    protected ChessBoard createChessBoard() {
        Map<Position, ChessPiece> chessBoard =
                CreateBoard.chessBoard(whitePieceDAO.selectBoard(), blackPieceDAO.selectBoard());

        return new ChessBoard(chessBoard, chessBoardStateDAO.selectPlayerTurn());
    }
}
