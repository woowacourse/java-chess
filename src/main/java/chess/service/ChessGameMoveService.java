package chess.service;

import chess.db.dao.BlackPieceDAO;
import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.db.dao.WhitePieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public class ChessGameMoveService {

    private static final String SUCCESS = "";

    private PieceDAO whitePieceDAO;
    private PieceDAO blackPieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ChessGameMoveService() {
        this.whitePieceDAO = new WhitePieceDAO();
        this.blackPieceDAO = new BlackPieceDAO();
        chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public String moveChessBoard(String sourcePosition, String targetPosition) {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(whitePieceDAO, blackPieceDAO, chessBoardStateDAO);

        if (chessBoard.containsPosition(Position.of(targetPosition))) {
            chessBoard.move(Position.of(sourcePosition), Position.of(targetPosition));
            updatePieceDaoAtCaughtSituation(chessBoard, sourcePosition, targetPosition);
            updatePlayerTurn();
            return isCaughtKing(chessBoard);
        }
        chessBoard.move(Position.of(sourcePosition),
                Position.of(targetPosition));
        updatePieceDao(chessBoard, sourcePosition, targetPosition);
        updatePlayerTurn();
        return isCaughtKing(chessBoard);
    }

    private void updatePlayerTurn() {
        ChessBoard chessBoard =
                CreateChessBoardFromDB.createChessBoard(whitePieceDAO, blackPieceDAO, chessBoardStateDAO);
        chessBoard.playerTurnChange();
        chessBoardStateDAO.updatePlayerTurn(chessBoard.getPlayerColor());
    }

    private void updatePieceDaoAtCaughtSituation(ChessBoard chessBoard, String sourcePosition, String targetPosition) {
        if (chessBoard.findSourcePieceSamePlayerColorBy(Position.of(targetPosition))
                .isSamePieceColorWith(PieceColor.BLACK)) {
            whitePieceDAO.deleteCaughtPiece(targetPosition);
            blackPieceDAO.updatePiece(sourcePosition, targetPosition);
            return;
        }
        blackPieceDAO.deleteCaughtPiece(targetPosition);
        whitePieceDAO.updatePiece(sourcePosition, targetPosition);
    }

    private void updatePieceDao(ChessBoard chessBoard, String sourcePosition, String targetPosition) {
        if (chessBoard.findSourcePieceSamePlayerColorBy(Position.of(targetPosition))
                .isSamePieceColorWith(PieceColor.BLACK)) {
            blackPieceDAO.updatePiece(sourcePosition, targetPosition);
            return;
        }
        whitePieceDAO.updatePiece(sourcePosition, targetPosition);
    }

    private String isCaughtKing(ChessBoard chessBoard) {
        if (chessBoard.isCaughtKing()) {
            chessBoardStateDAO.updateCaughtKing(chessBoard);
            return chessBoard.getPlayerColor().getColor() + "이 승리했습니다!";
        }
        return SUCCESS;
    }
}
