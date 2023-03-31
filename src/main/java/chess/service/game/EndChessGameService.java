package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.domain.game.ChessGame;
import chess.domain.game.command.Command;
import chess.domain.game.command.EndCommand;
import chess.domain.game.state.EndState;
import chess.repository.chess.ChessGameRepository;
import java.util.Optional;

public class EndChessGameService {

    private final LoadChessGameService loadChessGameService;
    private final ChessGameRepository chessGameRepository;

    public EndChessGameService(LoadChessGameService loadChessGameService, ChessGameRepository chessGameRepository) {
        this.loadChessGameService = loadChessGameService;
        this.chessGameRepository = chessGameRepository;
    }

    public void end(int boardId) {
        Command command = new EndCommand();
        Optional<ChessGame> chessGame = loadChessGameService.load(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        command.execute(chessGame.get());
        chessGameRepository.saveGameState(boardId, EndState.getInstance());
    }
}
