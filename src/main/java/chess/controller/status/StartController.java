package chess.controller.status;

import chess.controller.Command;
import chess.controller.mapper.ChessBoardDtoMapper;
import chess.domain.chess.ChessGame;
import chess.service.ChessGameService;
import chess.view.OutputView;

public final class StartController implements Controller {
    private final Long userId;
    private final ChessGameService chessGameService;

    public StartController(final Long userId, final ChessGameService chessGameService) {
        this.userId = userId;
        this.chessGameService = chessGameService;
    }

    @Override
    public Controller checkCommand(final Command command) {
        if (command.isEnd()) {
            return new EndController();
        }
        if (!command.isStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        return new MoveController(userId, chessGameService);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public void printBoard() {
        ChessGame chessGame = chessGameService.getChessGame(userId);
        OutputView.printBoard(ChessBoardDtoMapper.createChessBoardDto(chessGame.getChessBoard()));
    }
}
