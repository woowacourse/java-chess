package chess.controller;

import static chess.controller.GameStatus.READY;
import static chess.controller.GameStatus.RUNNING;

import chess.controller.converter.BoardConverter;
import chess.controller.util.InputExceptionHandler;
import chess.domain.Camp;
import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.dto.CommandRequest;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class ChessController {

    private final InputExceptionHandler inputExceptionHandler;
    private final Map<Command, Function<CommandRequest, GameStatus>> actionMapper = new EnumMap<>(Command.class);
    private GameStatus gameStatus;
    private ChessBoard chessBoard;

    public ChessController() {
        this.inputExceptionHandler = new InputExceptionHandler(OutputView::printInputErrorMessage);
        this.gameStatus = READY;
        actionMapper.put(Command.START, this::start);
        actionMapper.put(Command.MOVE, this::move);
        actionMapper.put(Command.END, this::end);
    }

    public void run() {
        OutputView.printGuideMessage();
        while (true) {
            gameStatus = inputExceptionHandler.retryExecuteIfInputIllegal(InputView::requestGameCommand, this::execute);
        }
    }

    private GameStatus execute(CommandRequest commandRequest) {
        Function<CommandRequest, GameStatus> action = actionMapper.getOrDefault(commandRequest.getCommand(),
                request -> {
                    throw new IllegalArgumentException("해당 요청으로 실행할 수 있는 기능이 없습니다.");
                });
        return action.apply(commandRequest);
    }

    private GameStatus start(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        chessBoard = new ChessBoard(Camp.WHITE, Camp::transfer);
        OutputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return RUNNING;
    }

    private GameStatus move(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        chessBoard.move(Position.from(commandRequest.getSourceCoordinate()),
                Position.from(commandRequest.getDestinationCoordinate()));
        OutputView.printBoard(BoardConverter.convertToBoard(chessBoard.piecesByPosition()));
        return RUNNING;
    }

    private GameStatus end(CommandRequest commandRequest) {
        gameStatus.validateCommand(commandRequest.getCommand());
        chessBoard = new ChessBoard(Camp.WHITE, Camp::transfer);
        OutputView.printGuideMessage();
        return READY;
    }

}
