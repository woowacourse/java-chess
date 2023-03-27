package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;
import chess.service.ChessGameService;
import java.util.List;

public final class Start extends AbstractInitialGameState {

    private final ChessGameService chessGameService;

    Start(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    public GameState execute(final GameCommand ignoredGameCommand, final List<Position> ignored) {
        chessGameService.initializeChessGame();

        return new Play(chessGameService);
    }
}
