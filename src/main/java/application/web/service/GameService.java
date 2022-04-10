package application.web.service;

import java.util.Map;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.repository.GameRepository;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Map<Long, Boolean> listGames() {
        return gameRepository.findIdAndFinished();
    }

    public ChessGame createNewGame() {
        final ChessGame chessGame = ChessGame.initializeAndStartChessGame();
        return gameRepository.save(chessGame);
    }

    public ChessGame loadGame(final Long gameId) {
        return gameRepository.findById(gameId);
    }

    public ChessGame movePiece(final Long gameId, final String source, final String target) {
        final ChessGame chessGame = gameRepository.findById(gameId);
        chessGame.movePiece(Position.from(source), Position.from(target));
        return gameRepository.update(chessGame);
    }

    public ChessGame promotion(final Long gameId, final String pieceName) {
        final ChessGame chessGame = gameRepository.findById(gameId);
        chessGame.promotePiece(pieceName);
        return gameRepository.update(chessGame);
    }

    public Map<Color, Double> calculatePlayerScores(final Long gameId) {
        final ChessGame chessGame = gameRepository.findById(gameId);
        return chessGame.getPlayerScores();
    }

    public ChessGame endGame(final Long gameId) {
        final ChessGame chessGame = gameRepository.findById(gameId);
        chessGame.end();
        return gameRepository.update(chessGame);
    }
}
