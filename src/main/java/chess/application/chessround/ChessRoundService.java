package chess.application.chessround;

import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPlayerDTO;
import chess.application.chessround.dto.ChessPointDTO;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieces;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.ChessPiecesBuilder;
import chess.domain.chessround.ChessPlayer;
import chess.domain.chessround.ChessRound;
import chess.domain.chessround.InvalidChessPositionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessRoundService {
    private static final String EMPTY = "";
    private static ChessRoundService chessRoundService = null;
    private ChessRound chessRound;
    private String errorMessage = EMPTY;

    private ChessRoundService() {
        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();
        List<ChessPieceDTO> whiteChessPieces = chessPlayerDAO.getAllChessPieces(true);
        List<ChessPieceDTO> blackChessPieces = chessPlayerDAO.getAllChessPieces(false);

        ChessPlayer whitePlayer = makeChessPlayerFrom(whiteChessPieces, true);
        ChessPlayer blackPlayer = makeChessPlayerFrom(blackChessPieces, false);

        ChessTurnDAO chessTurnDAO = ChessTurnDAO.getInstance();
        boolean isWhiteTurn = chessTurnDAO.getPlayerTurn();

        chessRound = ChessRound.of(whitePlayer, blackPlayer, isWhiteTurn);
    }

    private ChessPlayer makeChessPlayerFrom(List<ChessPieceDTO> chessPieceDTOs, boolean isWhiteTeam) {
        Map<ChessPoint, ChessPiece> alivePieces = new HashMap<>();
        for (ChessPieceDTO chessPieceDTO : chessPieceDTOs) {
            int row = chessPieceDTO.getRow();
            int column = chessPieceDTO.getColumn();
            ChessPoint chessPoint = ChessPoint.of(row, column);

            String chessPieceName = chessPieceDTO.getName();
            ChessPieces chessPieces = ChessPieces.getInstance();
            ChessPiece chessPiece = chessPieces.find(chessPieceName, isWhiteTeam);

            alivePieces.put(chessPoint, chessPiece);
        }
        return ChessPlayer.from(alivePieces);
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

        chessRound = ChessRound.of(whitePlayer, blackPlayer);

        clearAllChessPlayer();
        saveChessPlayer(whitePlayer, true);
        saveChessPlayer(blackPlayer, false);

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
        ChessPlayer whitePlayer = chessRound.getWhitePlayer();

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(whitePlayer);
    }

    public ChessPlayerDTO fetchBlackPlayer() {
        ChessPlayer blackPlayer = chessRound.getBlackPlayer();

        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        return chessRoundAssembler.makeChessPlayerDTO(blackPlayer);
    }

    public void move(String sourceId, String targetId) {
        ChessPoint source = parseChessPoint(sourceId);
        ChessPoint target = parseChessPoint(targetId);

        try {
            chessRound.move(source, target);
            chessRound.switchPlayerTurn();
            cleanErrorMessage();

            saveCurrentMove(source, target);
        } catch (InvalidChessPositionException ex) {
            errorMessage = ex.getMessage();
        }
    }

    private void saveCurrentMove(ChessPoint source, ChessPoint target) {
        boolean isLastTurnWhite = !chessRound.isWhiteTurn();

        deletePieceOn(source, isLastTurnWhite);

        deletePieceOn(target, !isLastTurnWhite);
        insertPieceOn(target, isLastTurnWhite);

        switchCurrentTurn(isLastTurnWhite);
    }

    private void deletePieceOn(ChessPoint point, boolean isWhiteTurn) {
        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();
        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        ChessPointDTO chessPointDTO = chessRoundAssembler.makeChessPointDTO(point);

        chessPlayerDAO.deleteChessPiece(chessPointDTO, isWhiteTurn);
    }

    private void insertPieceOn(ChessPoint point, boolean isWhiteTurn) {
        ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();
        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        ChessPlayer currentPlayer = isWhiteTurn ? chessRound.getWhitePlayer() : chessRound.getBlackPlayer();
        ChessPiece chessPiece = currentPlayer.get(point);
        ChessPieceDTO targetPieceDTO = chessRoundAssembler.makeChessPieceDTO(point, chessPiece);

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
        return chessRound.getWhitePlayerScore();
    }

    public double getBlackPlayerScore() {
        return chessRound.getBlackPlayerScore();
    }

    public boolean isWhiteTurn() {
        return chessRound.isWhiteTurn();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isGameFinished() {
        return chessRound.isGameFinished();
    }
}
