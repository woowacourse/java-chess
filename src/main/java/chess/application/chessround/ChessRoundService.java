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

    private ChessRound chessRound;
    private String errorMessage = EMPTY;

    private ChessRoundService() {
        chessRound = recoverFromSavedChessRound();
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

        chessRound = ChessRound.of(whitePlayer, blackPlayer);

        clearAllChessPlayer();
        saveCurrentRound();
    }

    private void saveCurrentRound() {
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
            cleanErrorMessage();

            // TODO: chessRound 내부의 상태가 변한 후 상태를 저장하게 된다.
            // 어떻게 보면 중복된 정보가 각각 존재하는 건데......
            // 중복된 정보를... 너무 멀리서? 다루는 거 아닐까???
            // 여기서 변경된 것이... 딴 쪽에서 볼 때.. 생각이 안 날 것 같은데...
            // 해결하려면... 한 쪽에서 고칠 때 동시에 고쳐주거나
            // 한 쪽에서 고칠 때 다른 쪽에 ... 먼가 등록?! 해주는 무언가가 필요할 듯
            chessRound.move(source, target);
            saveCurrentMove(source, target);
        } catch (InvalidChessPositionException ex) {
            errorMessage = ex.getMessage();
        }
    }

    private void saveCurrentMove(ChessPoint source, ChessPoint target) {
        // chessRound.move 이 끝난 후 호출되기에 chessRound 의 turn 이 변경되어 있음
        // 따라서 !chessRound.isWhiteTurn() 으로 chessRound.move 당시의 turn 을 표현
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
        ChessPlayer currentPlayer = isWhiteTurn ? chessRound.getWhitePlayer() : chessRound.getBlackPlayer();

        ChessPiece chessPiece = currentPlayer.get(point);
        ChessRoundAssembler chessRoundAssembler = ChessRoundAssembler.getInstance();
        ChessPieceDTO targetPieceDTO = chessRoundAssembler.makeChessPieceDTO(point, chessPiece);

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
