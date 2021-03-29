package chess.repository;

import chess.domain.game.ChessGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepository {

    private static Map<String, ChessGame> repository = new HashMap<>();

    public static void save(String gameId, ChessGame chessGame) {
        repository.put(gameId, chessGame);
    }

    public static Optional<ChessGame> findByGameId(String gameId) {
        return Optional.ofNullable(repository.getOrDefault(gameId, null));
    }

}
