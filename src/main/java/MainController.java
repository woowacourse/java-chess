import domain.ChessBoard;
import domain.Square;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import view.*;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, ChessboardExecuter> commandsMapper = new HashMap<>();


    public MainController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        commandsMapper.put(Command.START, (chessBoard, ignored) -> outputView.printChessBoard(chessBoard));
        commandsMapper.put(Command.MOVE, this::executeMoveCommand);
        commandsMapper.put(Command.END, (ignored1, ignored2) -> System.out.println("끝"));
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        inputView.printStartMessage();
        Command command = Command.INIT;
        while (command != Command.END) {
            command = playByCommand(chessBoard);
        }
    }

    private Command playByCommand(ChessBoard chessBoard) {
        try {
            List<String> input = repeatInputReader(inputView::readInput);
            Command command = Command.from(input);
            ChessboardExecuter executer = commandsMapper.get(command);
            executer.execute(chessBoard, input);
            return command;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return playByCommand(chessBoard);
        }
    }

    private <T> T repeatInputReader(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeatInputReader(inputReader);
        }
    }

    private void executeMoveCommand(ChessBoard chessBoard, List<String> inputs) {
        Square src = Square.of(inputs.get(1));
        Square dest = Square.of(inputs.get(2));
        chessBoard.move(src, dest);
        outputView.printChessBoard(chessBoard);
    }

}
