package chess.service;

import chess.dao.ChessGameDAO;
import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.dto.ChessGameDTO;

public class ChessGameService {
    private static final int NEW_GAME_ID = 1;
    private static final int WHITE_TURN = 1;

    private static ChessGameDAO chessGameDAO = ChessGameDAO.getInstance();

    private ChessGameService() {
    }

    private static class ChessGameServiceHolder {
        static final ChessGameService CHESS_GAME_SERVICE = new ChessGameService();
    }

    public static ChessGameService getInstance() {
        return ChessGameService.ChessGameServiceHolder.CHESS_GAME_SERVICE;
    }

    public ChessGameDTO.GameLoading createNewGame() {
        return createGameBy(NEW_GAME_ID);
    }

    public ChessGameDTO.GameLoading createLatestGame() {
        int latestId = chessGameDAO.findLatestChessGameId();
        return createGameBy(latestId);
    }

    private ChessGameDTO.GameLoading createGameBy(int gameId) {
        String newBoard = chessGameDAO.findChessGameById(gameId);
        int turn = chessGameDAO.findTurnById(gameId);

        Board board = BoardGenerator.createBoard(newBoard);
        String currentTeam = (turn == WHITE_TURN) ? "WHITE" : "BLACK";

        return new ChessGameDTO.GameLoading(board.getBoard(), currentTeam);
    }
}
