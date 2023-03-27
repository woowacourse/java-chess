package chess.controller.status;

import chess.controller.Command;
import chess.controller.dto.ChessResultDto;
import chess.controller.mapper.ChessBoardDtoMapper;
import chess.controller.mapper.ChessResultDtoMapper;
import chess.domain.chess.ChessGame;
import chess.domain.chess.ChessGameCalculator;
import chess.domain.chess.vo.ScoreVO;
import chess.service.ChessGameService;
import chess.view.OutputView;

public final class StatusController implements Controller {
    private final Long userId;
    private final ChessGame chessGame;
    private final ChessGameService chessGameService;

    StatusController(final Long userId, final ChessGameService chessGameService) {
        this.userId = userId;
        this.chessGame = chessGameService.getChessGame(userId);
        this.chessGameService = chessGameService;
    }

    @Override
    public Controller checkCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new EndController();
        }
        if (command.isMove()) {
            return new MoveController(userId, chessGameService).move(command);
        }
        return getStatus(true);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public void printBoard() {
        OutputView.printBoard(ChessBoardDtoMapper.createChessBoardDto(chessGame.getChessBoard()));
    }

    Controller getStatus(final boolean isGameRun) {
        if (!isGameRun) {
            OutputView.print("킹이 사망하여 게임이 종료되었습니다!");
            runCalculator().run();
            chessGameService.clear(userId);
            return new EndController();
        }
        runCalculator().run();
        return this;
    }

    private Runnable runCalculator() {
        return () -> {
            final ScoreVO chessResult = ChessGameCalculator.calculate(chessGame);
            final ChessResultDto chessResultDto = ChessResultDtoMapper.from(chessResult);
            OutputView.printChessResult(chessResultDto);
        };
    }
}
