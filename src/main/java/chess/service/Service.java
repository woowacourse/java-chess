package chess.service;

import chess.dao.BlackPieceDAO;
import chess.dao.PlayerTurnDAO;
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
    private BlackPieceDAO blackPieceDAO;
    private WhitePieceDAO whitePieceDAO;
    private PlayerTurnDAO playerTurnDAO;

    public Service() {
        this.blackPieceDAO = new BlackPieceDAO();
        this.whitePieceDAO = new WhitePieceDAO();
        this.playerTurnDAO = new PlayerTurnDAO();
    }

    public void initialChessBoard() {
        whitePieceDAO.deleteWhiteTable();
        blackPieceDAO.deleteBlackTable();
        playerTurnDAO.deletePlayerTurn();
        playerTurnDAO.insertInitialPlayerTurn();
        whitePieceDAO.insertInitialWhitePiece();
        blackPieceDAO.insertInitialBlackPiece();
    }

    public ChessBoard createInitialChessBoard() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        whitePieceDAO.selectWhiteBoard(chessBoard);
        blackPieceDAO.selectBlackBoard(chessBoard);

        return new ChessBoard(chessBoard);
    }

    public ChessBoard createContinuousChessBoard() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();
        whitePieceDAO.selectWhiteBoard(chessBoard);
        blackPieceDAO.selectBlackBoard(chessBoard);

        return new ChessBoard(chessBoard, playerTurnDAO.selectPlayerTurn());
    }

    public Map<String, Object> settingChessBoard(ChessBoard chessBoard) {
        Map<String, Object> model = new HashMap<>();
        List<ChessPieceDTO> chessPieceDTOArrayList = new ArrayList<>();

        chessBoard.getChessBoard().entrySet().forEach(
                (entry -> chessPieceDTOArrayList.add(
                        new ChessPieceDTO(entry.getKey().getPositionToString(), entry.getValue().getName()))
                )
        );

        model.put("chessPiece", chessPieceDTOArrayList);
        return model;
    }

    public void updatePlayerTurn(ChessBoard chessBoard) {
        playerTurnDAO.updatePlayerTurn(chessBoard.getPlayerColor());
    }

    public void moveChessBoard(ChessBoard chessBoard, ChessPositionDTO chessPositionDTO) {
        if (chessBoard.containsTargetPosition(Position.of(chessPositionDTO.getTargetPosition()))) {
            chessBoard.move(Position.of(chessPositionDTO.getSourcePosition()),
                    Position.of(chessPositionDTO.getTargetPosition())
            );
            selectPieceDaoAtCaughtSituation(chessBoard, chessPositionDTO);
            return;
        }
        chessBoard.move(Position.of(chessPositionDTO.getSourcePosition()),
                Position.of(chessPositionDTO.getTargetPosition()));
        selectPieceDao(chessBoard, chessPositionDTO);
    }

    private void selectPieceDaoAtCaughtSituation(ChessBoard chessBoard, ChessPositionDTO chessPositionDTO) {
        if (chessBoard.findSourceChessPieceFrom(Position.of(chessPositionDTO.getTargetPosition()))
                .isSamePieceColorWith(PieceColor.BLACK)) {
            whitePieceDAO.deleteCaughtPiece(chessPositionDTO);
            blackPieceDAO.updatePiece(chessPositionDTO);
            return;
        }
        blackPieceDAO.deleteCaughtPiece(chessPositionDTO);
        whitePieceDAO.updatePiece(chessPositionDTO);
    }

    private void selectPieceDao(ChessBoard chessBoard, ChessPositionDTO chessPositionDTO) {
        if (chessBoard.findSourceChessPieceFrom(Position.of(chessPositionDTO.getTargetPosition()))
                .isSamePieceColorWith(PieceColor.BLACK)) {
            blackPieceDAO.updatePiece(chessPositionDTO);
            return;
        }
        whitePieceDAO.updatePiece(chessPositionDTO);
    }
}