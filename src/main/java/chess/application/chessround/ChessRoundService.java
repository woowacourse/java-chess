package chess.application.chessround;

import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPlayerDTO;
import chess.application.chessround.dto.ChessPointDTO;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.ChessPiecesBuilder;
import chess.domain.chessround.ChessPlayer;
import chess.domain.chessround.ChessRound;
import chess.domain.chessround.InvalidChessPositionException;

import java.util.List;

public class ChessRoundService {
    private static final String EMPTY = "";

    private static ChessRoundService chessRoundService = null;

    private String errorMessage = EMPTY;

    private ChessRoundService() {
    }

    private ChessRound recoverFromSavedChessRound() {
        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();
        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();

        List<ChessPieceDTO> whiteChessPieces = chessPlayerDAO.getAllChessPieces(true);
        ChessPlayer whitePlayer = chessRoundAssembler.makeChessPlayerFrom(whiteChessPieces, true);

        List<ChessPieceDTO> blackChessPieces = chessPlayerDAO.getAllChessPieces(false);
        ChessPlayer blackPlayer = chessRoundAssembler.makeChessPlayerFrom(blackChessPieces, false);

        ChessTurnDAO chessTurnDAO = ChessTurnDAO.getInstance();
        boolean isWhiteTurn = chessTurnDAO.getPlayerTurn();

        return ChessRound.of(whitePlayer, blackPlayer, isWhiteTurn);
    }

    public static ChessRoundService getInstance() {
        if (chessRoundService == null) {
            chessRoundService = new ChessRoundService();
        }
        return chessRoundService;
    }

    public void initialize() {
        ChessPiecesBuilder chessPiecesBuilder = ChessPiecesBuilder.getInstance();
        ChessPlayer whitePlayer = ChessPlayer.from(chessPiecesBuilder.initializeWhiteChessPieces());
        ChessPlayer blackPlayer = ChessPlayer.from(chessPiecesBuilder.initializeBlackChessPieces());

        clearAllChessPlayer();
        saveCurrentRound(ChessRound.of(whitePlayer, blackPlayer, true));
    }

    private void saveCurrentRound(ChessRound chessRound) {
        saveChessPlayer(chessRound.getWhitePlayer(), true);
        saveChessPlayer(chessRound.getBlackPlayer(), false);

        saveCurrentTurn(chessRound.isWhiteTurn());
    }

    private void clearAllChessPlayer() {
        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();
        chessPlayerDAO.clear();
    }

    private void saveChessPlayer(ChessPlayer chessPlayer, boolean isWhiteTeam) {
        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        ChessPlayerDTO chessPlayerDTO = chessRoundAssembler.makeChessPlayerDTO(chessPlayer);

        for (ChessPieceDTO chessPieceDTO : chessPlayerDTO.getChessPieceDTOs()) {
            chessPlayerDAO.insertChessPiece(chessPieceDTO, isWhiteTeam);
        }
    }

    private void saveCurrentTurn(boolean isWhiteTurn) {
        ChessTurnDAO chessTurnDAO = ChessTurnDAO.getInstance();
        chessTurnDAO.updatePlayerTurn(isWhiteTurn);
    }

    public ChessPlayerDTO fetchWhitePlayer() {
        ChessRound chessRound = recoverFromSavedChessRound();

        ChessPlayer whitePlayer = chessRound.getWhitePlayer();

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(whitePlayer);
    }

    public ChessPlayerDTO fetchBlackPlayer() {
        ChessRound chessRound = recoverFromSavedChessRound();

        ChessPlayer blackPlayer = chessRound.getBlackPlayer();

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(blackPlayer);
    }

    public void move(String sourceId, String targetId) {
        ChessPoint source = parseChessPoint(sourceId);
        ChessPoint target = parseChessPoint(targetId);

        ChessRound chessRound = recoverFromSavedChessRound();
        try {
            cleanErrorMessage();

            chessRound.move(source, target);

            ChessPiece movedChessPiece = chessRound.getCurrentOpponentPlayer().get(target);
            saveCurrentMove(source, target, movedChessPiece, !chessRound.isWhiteTurn());
        } catch (InvalidChessPositionException ex) {
            errorMessage = ex.getMessage();
        }
    }

    private void saveCurrentMove(ChessPoint source, ChessPoint target, ChessPiece movedChessPiece, boolean isWhiteTurn) {
        deletePieceOn(source, isWhiteTurn);

        deletePieceOn(target, !isWhiteTurn);
        insertPieceOn(target, movedChessPiece, isWhiteTurn);

        switchCurrentTurn(isWhiteTurn);
    }

    private void deletePieceOn(ChessPoint point, boolean isWhiteTurn) {
        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();
        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        ChessPointDTO chessPointDTO = chessRoundAssembler.makeChessPointDTO(point);

        chessPlayerDAO.deleteChessPiece(chessPointDTO, isWhiteTurn);
    }

    private void insertPieceOn(ChessPoint point, ChessPiece piece, boolean isWhiteTurn) {
        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        ChessPieceDTO targetPieceDTO = chessRoundAssembler.makeChessPieceDTO(point, piece);

        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();
        chessPlayerDAO.insertChessPiece(targetPieceDTO, isWhiteTurn);
    }

    private void switchCurrentTurn(boolean isWhiteTurn) {
        ChessTurnDAO chessTurnDAO = ChessTurnDAO.getInstance();
        chessTurnDAO.updatePlayerTurn(!isWhiteTurn);
    }

    private void cleanErrorMessage() {
        errorMessage = EMPTY;
    }

    private ChessPoint parseChessPoint(String pointId) {
        int row = Integer.parseInt(pointId.substring(0, 1));
        int column = Integer.parseInt(pointId.substring(1, 2));
        return ChessPoint.of(row, column);
    }

    public double getWhitePlayerScore() {
        ChessRound chessRound = recoverFromSavedChessRound();

        return chessRound.getWhitePlayerScore();
    }

    public double getBlackPlayerScore() {
        ChessRound chessRound = recoverFromSavedChessRound();

        return chessRound.getBlackPlayerScore();
    }

    public boolean isWhiteTurn() {
        ChessRound chessRound = recoverFromSavedChessRound();

        return chessRound.isWhiteTurn();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isGameFinished() {
        ChessRound chessRound = recoverFromSavedChessRound();

        return chessRound.isGameFinished();
    }
}
