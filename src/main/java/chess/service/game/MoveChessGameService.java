package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.domain.game.ChessGame;
import chess.domain.game.command.Command;
import chess.domain.game.command.MoveCommand;
import chess.repository.chess.ChessGameRepository;
import java.util.Optional;

public class MoveChessGameService {

    private final LoadChessGameService loadChessGameService;
    private final ChessGameRepository chessGameRepository;

    public MoveChessGameService(LoadChessGameService loadChessGameService, ChessGameRepository chessGameRepository) {
        this.loadChessGameService = loadChessGameService;
        this.chessGameRepository = chessGameRepository;
    }

    public void move(int boardId, String source, String target) {
        Command command = MoveCommand.of(source, target);
        Optional<ChessGame> chessGame = loadChessGameService.load(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        command.execute(chessGame.get());
        chessGameRepository.saveMoves(boardId, source, target, chessGame.get().getTurn().getValue());
    }
}
