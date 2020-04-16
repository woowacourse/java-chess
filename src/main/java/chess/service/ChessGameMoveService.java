package chess.service;

import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.ChessPieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.Position;

public class ChessGameMoveService {

    private static final String SUCCESS = "";

    private ChessPieceDAO chessPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ChessGameMoveService() {
        this.chessPieceDAO = new ChessPieceDAO();
        chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public String moveChessBoard(String sourcePosition, String targetPosition) {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(chessPieceDAO, chessBoardStateDAO);

        chessBoard.move(Position.of(sourcePosition), Position.of(targetPosition));
        if (chessBoard.containsPosition(Position.of(targetPosition))) {
            chessPieceDAO.deletePiece(targetPosition);
        }
        chessPieceDAO.updatePiece(sourcePosition, targetPosition);
        updatePlayerTurn();
        return isCaughtKing(chessBoard);
    }

    private void updatePlayerTurn() {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(chessPieceDAO, chessBoardStateDAO);
        chessBoard.playerTurnChange();
        chessBoardStateDAO.updatePlayerTurn(chessBoard.getPlayerColor());
    }

    private String isCaughtKing(ChessBoard chessBoard) {
        if (chessBoard.isCaughtKing()) {
            chessBoardStateDAO.updateCaughtKing(chessBoard);
            return chessBoard.getPlayerColor().getColor() + "이 승리했습니다!";
        }
        return SUCCESS;
    }
}
