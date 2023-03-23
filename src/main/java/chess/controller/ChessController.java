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
import java.util.function.Consumer;

public class ChessController {

    private final InputExceptionHandler inputExceptionHandler;
    private final Map<Command, Consumer<CommandRequest>> actionMapper = new EnumMap<>(Command.class);
    private ChessGame chessGame;
    private boolean toExit;

    public ChessController() {
        this.inputExceptionHandler = new InputExceptionHandler(OutputView::printInputErrorMessage);
        this.toExit = false;
        actionMapper.put(Command.START, this::start);
        actionMapper.put(Command.MOVE, this::move);
        actionMapper.put(Command.END, this::end);
        actionMapper.put(Command.EXIT, this::forceQuit);
    }

    public void run() {
        OutputView.printGuideMessage();
        while (!toExit) {
            inputExceptionHandler.retryExecuteIfInputIllegal(InputView::requestGameCommand, this::execute);
        }
    }

    private void execute(CommandRequest commandRequest) {
        Consumer<CommandRequest> action = actionMapper.getOrDefault(commandRequest.getCommand(),
                request -> {
                    throw new IllegalArgumentException("해당 요청으로 실행할 수 있는 기능이 없습니다.");
                });
        action.accept(commandRequest);
    }

    private void start(CommandRequest commandRequest) {
        chessGame = new ChessGame(Camp.WHITE, Camp::transfer);
        chessGame.start(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
    }

    // TODO 함수형으로 쓰는데 외부의 값을 변경하는 거 아닌가?
    private void move(CommandRequest commandRequest) {
        chessGame.move(commandRequest);
        OutputView.printBoard(BoardConverter.convertToBoard(chessGame.readBoard()));
    }

    private void end(CommandRequest commandRequest) {
        chessGame.end(commandRequest);
        OutputView.printGuideMessage();
    }

    private void forceQuit(CommandRequest commandRequest) {
        toExit = true;
    }

}
