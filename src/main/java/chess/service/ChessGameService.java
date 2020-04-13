package chess.service;

import chess.db.SQLExecuteException;
import chess.db.dao.BlackPieceDAO;
import chess.db.dao.ChessBoardStateDAO;
import chess.db.dao.PieceDAO;
import chess.db.dao.WhitePieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.CreateBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.initialChessBoard.InitialBlackChessBoardDTO;
import chess.domain.initialChessBoard.InitialChessBoardDTO;
import chess.domain.initialChessBoard.InitialChessBoardStateDTO;
import chess.domain.initialChessBoard.InitialWhiteChessBoardDTO;
import chess.domain.position.Position;
import chess.dto.ChessPieceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {

    private static final String SUCCESS = "";

    private PieceDAO blackPieceDAO;
    private PieceDAO whitePieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public ChessGameService() {
        this.blackPieceDAO = new BlackPieceDAO();
        this.whitePieceDAO = new WhitePieceDAO();
        this.chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public String initialChessBoard() {
        whitePieceDAO.deleteTable();
        blackPieceDAO.deleteTable();
        chessBoardStateDAO.deleteChessBoardState();

        InitialChessBoardDTO initialWhiteChessBoard = new InitialWhiteChessBoardDTO();
        InitialChessBoardDTO initialBlackChessBoard = new InitialBlackChessBoardDTO();
        InitialChessBoardStateDTO initialChessBoardStateDTO = new InitialChessBoardStateDTO();

        chessBoardStateDAO.insertChessBoardState(initialChessBoardStateDTO.getChessBoardState());
        whitePieceDAO.insertPiece(initialWhiteChessBoard.getInitialChessBoard());
        blackPieceDAO.insertPiece(initialBlackChessBoard.getInitialChessBoard());

        return SUCCESS;
    }

    private ChessBoard createChessBoard() {
        try (ResultSet whitePieceResultSet = whitePieceDAO.selectBoard();
             ResultSet blackPieceResultSet = blackPieceDAO.selectBoard()) {
            Map<Position, ChessPiece> chessBoard = CreateBoard.chessBoard(whitePieceResultSet, blackPieceResultSet);

            return new ChessBoard(chessBoard, chessBoardStateDAO.selectPlayerTurn());
        } catch (SQLException e) {
            throw new SQLExecuteException("ResultSet 에러!!!");
        }
    }

    public Map<String, Object> settingChessBoard() {
        Map<String, Object> model = new HashMap<>();
        List<ChessPieceDTO> chessPieceDTOArrayList = new ArrayList<>();

        createChessBoard().getChessBoard().entrySet().forEach(
                (entry -> chessPieceDTOArrayList.add(
                        new ChessPieceDTO(entry.getKey().getPositionToString(), entry.getValue().getName()))
                )
        );

        model.put("chessPiece", chessPieceDTOArrayList);
        return model;
    }

    public String moveChessBoard(String sourcePosition, String targetPosition) {
        ChessBoard chessBoard = createChessBoard();

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
        ChessBoard chessBoard = createChessBoard();
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

    public double calculateScore() {
        ChessBoard chessBoard = createChessBoard();
        return chessBoard.calculateScore();
    }

    public String getColor() {
        ChessBoard chessBoard = createChessBoard();
        PieceColor pieceColor = chessBoard.getPlayerColor();

        return pieceColor.getColor();
    }

    private String isCaughtKing(ChessBoard chessBoard) {
        if (chessBoard.isCaughtKing()) {
            chessBoardStateDAO.updateCaughtKing(chessBoard);
            return chessBoard.getPlayerColor().getColor() + "이 승리했습니다!";
        }
        return SUCCESS;
    }
}