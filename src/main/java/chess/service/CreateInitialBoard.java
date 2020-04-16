package chess.service;

import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.ChessPieceDAO;
import chess.domain.initialChessBoard.InitialChessBoardStateDTO;
import chess.domain.initialChessBoard.InitialChessPiecesDTO;

public class CreateInitialBoard {

    private static final String SUCCESS = "";

    private ChessPieceDAO chessPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public CreateInitialBoard() {
        chessPieceDAO = new ChessPieceDAO();

        this.chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public String initialChessBoard() {
        chessPieceDAO.deleteTable();
        chessBoardStateDAO.deleteChessBoardState();

        chessBoardStateDAO.insertChessBoardState(
                InitialChessBoardStateDTO.getInitialTurn(), InitialChessBoardStateDTO.isCaughtKing());
        InitialChessPiecesDTO.getInitialChessBoard().forEach((key, value) -> chessPieceDAO.insertPiece(key, value));

        return SUCCESS;
    }
}
