package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.result.StatusResult;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionConvertor;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ChessController {

    private static final String GAME_START_ERROR = "게임 시작을 먼저 해야 합니다.";
    private static final String ALREADY_GAME_START_ERROR = "게임이 이미 시작되었습니다.";
    private final Board board;

    public ChessController() {
        this.board = new Board();
    }

    public void run() {
        InputView.printCommandGuide();
        processStart();
        OutputView.printBoard(board);
        processCommand();
        OutputView.printWinner(board.getWinner());
    }

    private void processCommand() {
        while (!board.isFinished()) {
            List<String> inputCommand = InputView.requestCommand();
            processEachCommand(inputCommand);
        }
    }

    private void processStart() {
        List<String> inputCommand = InputView.requestCommand();
        if (!Command.isStart(inputCommand.get(0))) {
            throw new IllegalArgumentException(GAME_START_ERROR);
        }
    }

    private void processEachCommand(List<String> inputCommand) {
        if (Command.isMove(inputCommand.get(0))) {
            processMove(inputCommand);
            return;
        }

        Map<Command, Runnable> functionByCommand = makeFunctionByCommand();
        Command command = Command.find(inputCommand.get(0));
        functionByCommand.get(command).run();
    }

    private Map<Command, Runnable> makeFunctionByCommand() {
        Map<Command, Runnable> functionByCommand = new EnumMap<>(Command.class);
        functionByCommand.put(Command.END, board::endGame);
        functionByCommand.put(Command.START, () -> {
            throw new IllegalArgumentException();
        });
        functionByCommand.put(Command.STATUS, this::processStatus);
        return functionByCommand;
    }

    private void processMove(final List<String> inputCommand) {
        Position source = PositionConvertor.to(inputCommand.get(1));
        Position target = PositionConvertor.to(inputCommand.get(2));
        board.move(source, target);
        OutputView.printBoard(board);
    }

    private void processStatus() {
        double blackScore = board.calculateScore(Team.BLACK);
        double whiteScore = board.calculateScore(Team.WHITE);
        StatusResult result = new StatusResult(blackScore, whiteScore);
        OutputView.printScore(result);
    }
}
