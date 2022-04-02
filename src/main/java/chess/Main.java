package chess;

import chess.controller.ChessController;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        Map<ChessCommand, Consumer<List<String>>> commandRun = new HashMap<>();
        initCommands(chessController, commandRun);
        while (!chessController.isEnd()) {
            List<String> inputCommand = Arrays.asList(InputView.inputStartOrEndGame());
            ChessCommand command = ChessCommand.findCommand(inputCommand);
            commandRun.get(command).accept(inputCommand);

        }
    }

    private static void initCommands(ChessController chessController,
                                     Map<ChessCommand, Consumer<List<String>>> commandRun) {
        commandRun.put(ChessCommand.START, (param) -> start(chessController));
        commandRun.put(ChessCommand.MOVE, (input) -> move(chessController, input));
        commandRun.put(ChessCommand.STATUS, (param) -> status(chessController));
        commandRun.put(ChessCommand.END, (param) -> end(chessController));
    }

    private static void end(ChessController chessController) {
        chessController.finishGame();
    }

    private static void status(ChessController chessController) {
        ScoreDto scoreDto = chessController.score();
        OutputView.announceScoreDto(scoreDto);
    }

    private static void move(ChessController chessController, List<String> squares) {
        BoardDto boardDto = chessController.move(squares.get(1), squares.get(2));
        OutputView.announce(boardDto);
    }

    private static void start(ChessController chessController) {
        BoardDto boardDto = chessController.startGame();
        OutputView.announce(boardDto);
    }
}
