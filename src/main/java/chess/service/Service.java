package chess.service;

import chess.dao.BlackPieceDAO;
import chess.dao.ChessBoardStateDAO;
import chess.dao.WhitePieceDAO;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;
import chess.dto.ChessPieceDTO;
import chess.dto.ChessPositionDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service {

    private static final String SUCCESS = "";

    private BlackPieceDAO blackPieceDAO;
    private WhitePieceDAO whitePieceDAO;
    private ChessBoardStateDAO chessBoardStateDAO;

    public Service() {
        this.blackPieceDAO = new BlackPieceDAO();
        this.whitePieceDAO = new WhitePieceDAO();
        this.chessBoardStateDAO = new ChessBoardStateDAO();
    }

    public String initialChessBoard() {
        whitePieceDAO.deleteWhiteTable();
        blackPieceDAO.deleteBlackTable();
        chessBoardStateDAO.deleteChessBoardState();
        chessBoardStateDAO.insertInitialChessBoardState();
        whitePieceDAO.insertInitialWhitePiece();
        blackPieceDAO.insertInitialBlackPiece();

        return SUCCESS;
    }

    private ChessBoard createChessBoard() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        whitePieceDAO.selectWhiteBoard(chessBoard);
        blackPieceDAO.selectBlackBoard(chessBoard);

        return new ChessBoard(chessBoard, chessBoardStateDAO.selectPlayerTurn());
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

    public String moveChessBoard(ChessPositionDTO chessPositionDTO) {
        ChessBoard chessBoard = createChessBoard();
        if (chessBoard.containsPosition(Position.of(chessPositionDTO.getTargetPosition()))) {
            chessBoard.move(Position.of(chessPositionDTO.getSourcePosition()),
                    Position.of(chessPositionDTO.getTargetPosition())
            );
            determinePieceDaoAtCaughtSituation(chessBoard, chessPositionDTO);
            updatePlayerTurn();
            return isCaughtKing(chessBoard);
        }
        chessBoard.move(Position.of(chessPositionDTO.getSourcePosition()),
                Position.of(chessPositionDTO.getTargetPosition()));
        determinePieceDao(chessBoard, chessPositionDTO);
        updatePlayerTurn();
        return isCaughtKing(chessBoard);
    }

    private void updatePlayerTurn() {
        ChessBoard chessBoard = createChessBoard();
        chessBoard.playerTurnChange();
        chessBoardStateDAO.updatePlayerTurn(chessBoard.getPlayerColor());
    }

    private void determinePieceDaoAtCaughtSituation(ChessBoard chessBoard, ChessPositionDTO chessPositionDTO) {
        if (chessBoard.findSourcePieceSamePlayerColorBy(Position.of(chessPositionDTO.getTargetPosition()))
                .isSamePieceColorWith(PieceColor.BLACK)) {
            whitePieceDAO.deleteCaughtPiece(chessPositionDTO);
            blackPieceDAO.updatePiece(chessPositionDTO);
            return;
        }
        blackPieceDAO.deleteCaughtPiece(chessPositionDTO);
        whitePieceDAO.updatePiece(chessPositionDTO);
    }

    private void determinePieceDao(ChessBoard chessBoard, ChessPositionDTO chessPositionDTO) {
        if (chessBoard.findSourcePieceSamePlayerColorBy(Position.of(chessPositionDTO.getTargetPosition()))
                .isSamePieceColorWith(PieceColor.BLACK)) {
            blackPieceDAO.updatePiece(chessPositionDTO);
            return;
        }
        whitePieceDAO.updatePiece(chessPositionDTO);
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
            chessBoardStateDAO.updateCaughtKing(createChessBoard());
            return chessBoard.getPlayerColor().getColor() + "이 승리했습니다!";
        }
        return SUCCESS;
    }
}