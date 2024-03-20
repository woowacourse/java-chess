package controller;

import domain.board.Board;
import domain.command.Command;
import domain.position.Position;
import domain.position.PositionGenerator;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.mapper.CommandInput;

public class Game {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        outputView.printStartNotice();
        List<String> rawCommand = inputView.readCommand();
        Command command = CommandInput.asCommand(rawCommand.get(0));
        if (command.isStart()) {
            Board board = Board.create();
            outputView.printInitBoard(board);
        }
        if (command.isEnd()) {
            return;
        }
        PositionGenerator positionGenerator = new PositionGenerator();
        Position sourcePosition = positionGenerator.generate(rawCommand.get(1));
        Position targetPosition = positionGenerator.generate(rawCommand.get(2));
    }
}
