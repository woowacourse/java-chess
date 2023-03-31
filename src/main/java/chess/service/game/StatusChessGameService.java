package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.controller.game.status.StatusResponse;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import java.util.Map;
import java.util.Optional;

public class StatusChessGameService {

    private final LoadChessGameService loadChessGameService;

    public StatusChessGameService(LoadChessGameService loadChessGameService) {
        this.loadChessGameService = loadChessGameService;
    }

    public StatusResponse status(int boardId) {
        Optional<ChessGame> chessGame = loadChessGameService.load(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        Map<Color, Double> status = chessGame.get().getStatus();
        return new StatusResponse(status.get(Color.WHITE), status.get(Color.BLACK));
    }
}
