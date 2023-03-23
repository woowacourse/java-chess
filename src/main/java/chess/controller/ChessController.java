package chess.controller;

import chess.controller.converter.BoardConverter;
import chess.controller.util.InputExceptionHandler;
import chess.domain.Camp;
import chess.domain.ChessGame;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class ChessController {

    private final InputExceptionHandler inputExceptionHandler;
    private final Map<Command, CommandAction> actionMapper = new EnumMap<>(Command.class);
    private AppStatus appStatus;
    private ChessGame chessGame;

    public ChessController() {
        this.inputExceptionHandler = new InputExceptionHandler(OutputView::printInputErrorMessage);
        this.appStatus = AppStatus.RUNNING;
        actionMapper.put(Command.START, this::start);
        actionMapper.put(Command.MOVE, this::move);
        actionMapper.put(Command.END, this::end);
        actionMapper.put(Command.EXIT, this::forceQuit);
    }

    public void run() {
        OutputView.printGuideMessage();
        while (appStatus == AppStatus.RUNNING) {
            appStatus = inputExceptionHandler.retryExecuteIfInputIllegal(InputView::requestGameCommand, this::execute);
        }
    }

    private AppStatus execute(CommandRequest commandRequest) {
        CommandAction action = actionMapper.getOrDefault(commandRequest.getCommand(),
                request -> {
                    throw new IllegalArgumentException("해당 요청으로 실행할 수 있는 기능이 없습니다.");
                });
        return action.execute(commandRequest);
    }

    private AppStatus start(CommandRequest commandRequest) {
        chessGame = new ChessGame(Camp.WHITE, Camp::transfer);
        chessGame.start(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        return AppStatus.RUNNING;
    }

    private AppStatus move(CommandRequest commandRequest) {
        chessGame.move(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
        return AppStatus.RUNNING;
    }

    private AppStatus end(CommandRequest commandRequest) {
        chessGame.end(commandRequest);
        OutputView.printGuideMessage();
        return AppStatus.RUNNING;
    }

    private AppStatus forceQuit(CommandRequest commandRequest) {
        return AppStatus.TO_EXIT;
    }

}
