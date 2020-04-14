package chess.service;

import chess.db.dao.BlackPieceDAO;
import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.db.dao.WhitePieceDAO;
import chess.domain.initialChessBoard.InitialBlackChessBoardDTO;
import chess.domain.initialChessBoard.InitialChessBoardStateDTO;
import chess.domain.initialChessBoard.InitialWhiteChessBoardDTO;

public class createInitialBoard {

    private static final String SUCCESS = "";

    private PieceDAO blackPieceDAO;
    private PieceDAO whitePieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public createInitialBoard() {
        this.blackPieceDAO = new BlackPieceDAO();
        this.whitePieceDAO = new WhitePieceDAO();
        this.chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public String initialChessBoard() {
        whitePieceDAO.deleteTable();
        blackPieceDAO.deleteTable();
        chessBoardStateDAO.deleteChessBoardState();

        chessBoardStateDAO.insertChessBoardState(
                InitialChessBoardStateDTO.getInitialTurn(), InitialChessBoardStateDTO.isCaughtKing());
        InitialWhiteChessBoardDTO.getInitialChessBoard().forEach((key, value) -> whitePieceDAO.insertPiece(key, value));
        InitialBlackChessBoardDTO.getInitialChessBoard().forEach((key, value) -> blackPieceDAO.insertPiece(key, value));

        return SUCCESS;
    }
}
