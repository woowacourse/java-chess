package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurrentGameRoom {
    private final Map<Long, ChessGame> chessGames = new HashMap<>();

    public Optional<ChessGame> loadGame(Long gameId) {
        return Optional.ofNullable(chessGames.get(gameId));
    }

    public void save(Long gameId, ChessGame chessGame) {
        chessGames.put(gameId, chessGame);
    }

    public void restart(Long gameId) {
        chessGames.put(gameId, ChessGameImpl.initialGame());
    }

    public void remove(Long gameId) {
        chessGames.remove(gameId);
    }
}
