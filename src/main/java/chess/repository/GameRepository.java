package chess.repository;

import chess.domain.game.ChessGame;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepository {
    static Gson gson = new Gson();

    private static Map<String, ChessGame> repository = new HashMap<>();
    private static Map<String, String> stringRepository = new HashMap<>();

    public static void save(String gameId, ChessGame chessGame) {
        stringRepository.put(gameId, ChessGameConvertor.chessGameToJson(chessGame));
        repository.put(gameId, chessGame);
    }

    public static Optional<ChessGame> findByGameId(String gameId) {
        return Optional.ofNullable(repository.getOrDefault(gameId, null));
    }

    public static Optional<ChessGame> findByGameIdViaStringRepo(String gameId) {
        String s = stringRepository.get(gameId);

        ChessGame chessGame = ChessGameConvertor.jsonToChessGame(s);
        return Optional.of(chessGame);
    }

}
