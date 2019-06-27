package chess.service;

import chess.model.piece.ChessPieceColor;
import chess.persistance.GameDAO;

import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static final GameService INSTANCE = new GameService();

    private GameService() {
    }

    public static GameService getInstance() {
        return INSTANCE;
    }

    public void addGame(final String gameName) {
        GameDAO gameDAO = GameDAO.getInstance();
        gameDAO.addGame(gameName);
    }

    public int getGameId(final String gameName) {
        GameDAO gameDAO = GameDAO.getInstance();
        return gameDAO.getGameId(gameName);
    }

    public ChessPieceColor getTurn(final String gameId) {
        GameDAO gameDAO = GameDAO.getInstance();
        return gameDAO.getTurn(Integer.valueOf(gameId));
    }

    public List<GameDTO> getAllGames() {
        GameDAO gameDAO = GameDAO.getInstance();
        List<GameDTO> result = new ArrayList<>();
        List<Integer> ids = gameDAO.getAllIds();
        for (Integer id : ids) {
            result.add(new GameDTO(gameDAO.getName(id), id));
        }
        return result;
    }
}
