package chess.dao;

import chess.dto.GameDTO;
import chess.dto.GameIdDTO;
import chess.dto.TurnDTO;

import java.util.HashMap;
import java.util.Map;

public class FakeGameDAO implements GameDAO {

    private final Map<Integer, Map<String, String>> games = new HashMap<>();
    private int id = 0;

    @Override
    public void saveGame(GameDTO gameDTO, TurnDTO turnDTO) {
        id ++;
        Map<String, String> game = new HashMap<>();
        game.put("white", gameDTO.getWhiteUserName());
        game.put("black", gameDTO.getBlackUserName());
        game.put("turn", turnDTO.getTurn());
        games.put(id, game);
    }

    @Override
    public int findGameIdByUser(GameDTO gameDTO) {
        for (int id : games.keySet()) {
            if (games.get(id).get("white").equals(gameDTO.getWhiteUserName()) && (games.get(id).get("black").equals(gameDTO.getBlackUserName()))) {
                return id;
            }
        }
        return 0;
    }

    @Override
    public String findTurn(GameIdDTO gameIdDTO) {
        return games.get(gameIdDTO.getId()).get("turn");
    }

    @Override
    public void updateTurn(GameIdDTO gameIdDTO, String turn) {
        games.get(gameIdDTO.getId()).put("turn", turn);
    }

    @Override
    public void deleteGame(GameIdDTO gameIdDTO) {
        games.remove(gameIdDTO.getId());
    }
}
