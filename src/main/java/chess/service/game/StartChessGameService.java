package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.domain.game.ChessGame;
import chess.domain.game.command.Command;
import chess.domain.game.command.StartCommand;
import chess.repository.chess.ChessGameRepository;
import java.util.Optional;

public class StartChessGameService {

    private final ChessGameRepository chessGameRepository;
    private final LoadChessGameService loadChessGameService;

    public StartChessGameService(LoadChessGameService loadChessGameService, ChessGameRepository chessGameRepository) {
        this.loadChessGameService = loadChessGameService;
        this.chessGameRepository = chessGameRepository;
    }

    public void start(int boardId) {
        Command command = new StartCommand();
        Optional<ChessGame> chessGame = loadChessGameService.load(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        command.execute(chessGame.get());
        chessGameRepository.saveGameState(boardId, chessGame.get().getGameState());
    }
}
