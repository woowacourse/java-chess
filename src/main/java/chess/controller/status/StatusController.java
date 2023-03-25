package chess.controller.status;

import chess.controller.Command;
import chess.controller.dto.ChessResultDto;
import chess.controller.mapper.ChessResultDtoMapper;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.chess.ChessGameCalculator;
import chess.view.OutputView;

public final class StatusController implements Controller {
    private final ChessGame chessGame;
    private final CampType campType;

    StatusController(final ChessGame chessGame, final CampType campType) {
        this.chessGame = chessGame;
        this.campType = campType;
    }

    @Override
    public Controller checkCommand(final Command command, final Runnable runnable) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new EndController();
        }
        if (command.isMove()) {
            return new MoveController(chessGame, campType).move(command, runnable);
        }
        return getStatus(true);
    }

    @Override
    public boolean isRun() {
        return true;
    }

    Controller getStatus(final boolean isGameRun) {
        if (!isGameRun) {
            OutputView.print("킹이 사망하여 게임이 종료되었습니다!");
            runCalculator().run();
            return new EndController();
        }
        runCalculator().run();
        return this;
    }

    private Runnable runCalculator() {
        return () -> {
            ChessResultDto chessResultDto = ChessResultDtoMapper.from(ChessGameCalculator.calculate(chessGame));
            OutputView.printChessResult(chessResultDto);
        };
    }
}
