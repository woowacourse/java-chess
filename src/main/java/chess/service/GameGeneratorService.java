package chess.service;

import chess.domain.piece.core.Team;
import chess.persistence.dao.ChessGameDAO;
import chess.persistence.dto.ChessGameDTO;

public class GameGeneratorService {
    private static final int IS_ZERO = 0;

    private GameGeneratorService() {

    }

    public static GameGeneratorService getInstance() {
        return GameGeneratorHolder.INSTANCE;
    }

    public ChessGameDTO request() {
        ChessGameDTO chessGameDTO = ChessGameDAO.getInstance().findByMaxGameId();

        if (chessGameDTO.getGameId() == IS_ZERO || chessGameDTO.getGameStatus()) {
            chessGameDTO.setGameStatus(false);
            chessGameDTO.setLastUser(Team.WHITE.getTeam());
            ChessGameDAO.getInstance().addGameStatus(chessGameDTO);

            return ChessGameDAO.getInstance().findByMaxGameId();
        }

        return chessGameDTO;
    }

    private static class GameGeneratorHolder {
        static final GameGeneratorService INSTANCE = new GameGeneratorService();
    }
}
