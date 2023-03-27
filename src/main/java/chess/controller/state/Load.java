package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;
import chess.service.ChessGameService;
import java.util.List;

public final class Load extends AbstractInitialGameState {

    private final ChessGameService chessGameService;

    public Load(final ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
    }

    @Override
    public GameState execute(final GameCommand ignoredGameCommand, final List<Position> ignoredPositions) {
        chessGameService.loadChessGame();

        return new Play(chessGameService);
    }
}
