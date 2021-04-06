package chess.repository;

import chess.dao.ChessDAO;
import chess.dao.DataSource;
import chess.domain.game.ChessGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepository {

    private static ChessDAO chessDAO = new ChessDAO(new DataSource());
    private static Map<String, ChessGame> repository = new HashMap<>();

    public static void saveToCache(String gameId, ChessGame chessGame) {
        repository.put(gameId, chessGame);
    }

    public static void saveToDB(String gameId, ChessGame chessGame) {
        chessDAO.addChessGame(
                gameId,
                ChessGameConvertor.chessGameToJson(chessGame)
        );
    }

    public static void updateToDB(String gameId, ChessGame chessGame) {
        chessDAO.updateChessGame(
                gameId,
                ChessGameConvertor.chessGameToJson(chessGame)
        );
    }

    public static ChessGame findByGameIdFromCache(String gameId) {
        Optional<ChessGame> chessGame = Optional.ofNullable(repository.getOrDefault(gameId, null));

        if (!chessGame.isPresent()) {
            throw new IllegalArgumentException("게임 ID가 존재하지 않습니다.");
        }

        return chessGame.get();
    }

    public static ChessGame findByGameIdFromDB(String gameId) {
        String chessGameJson = chessDAO.findChessGameByGameId(gameId);

        return ChessGameConvertor.jsonToChessGame(chessGameJson);
    }

    public static boolean isGameIdExistingInDB(String gameId) {
        return chessDAO.isGameIdExisting(gameId);
    }
}
