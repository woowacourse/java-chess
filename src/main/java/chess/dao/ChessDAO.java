package chess.dao;

import chess.domain.ChessGame;
import chess.domain.CurrentGameRoom;
import java.util.Optional;

public interface ChessDAO {

    Long saveGame(ChessGame chessGame, Long gameId);

    Optional<ChessGame> loadGame(Long gameId);

    void removeGame(Long gameId);

    class Fake implements ChessDAO {

        private CurrentGameRoom currentGameRoom;

        public Fake() {
            this.currentGameRoom = new CurrentGameRoom();
        }

        @Override
        public Long saveGame(ChessGame chessGame, Long gameId) {
            currentGameRoom.save(gameId, chessGame);
            return gameId;
        }

        @Override
        public Optional<ChessGame> loadGame(Long gameId) {
            return currentGameRoom.loadGame(gameId);
        }

        @Override
        public void removeGame(Long gameId) {
            currentGameRoom.remove(gameId);
        }
    }
}