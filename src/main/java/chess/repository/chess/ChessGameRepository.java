package chess.repository.chess;

import chess.domain.game.ChessGame;
import chess.domain.game.state.GameState;
import java.util.List;
import java.util.Optional;

public interface ChessGameRepository {

    int create(int userId);

    Optional<ChessGame> findById(int id);

    List<Integer> findAllIdsByUserId(int userId);

    void saveGameState(int boardId, GameState gameState);

    void saveMoves(int boardId, String origin, String destination, int turn);
}
