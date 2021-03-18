package chess;

import chess.domain.grid.Grid;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        OutputView.printStartChess();
        Grid grid = new Grid();
        while (true) {
            String command = InputView.inputCommand();
            if (command.equals("end")) {
                return;
            }

            if (command.startsWith("move")) {
                List<String> strGroup = Arrays.asList(command.split(" "));
                Position source = new Position(strGroup.get(1).charAt(0), strGroup.get(1).charAt(1));
                Position target = new Position(strGroup.get(2).charAt(0), strGroup.get(2).charAt(1));
                grid.move(source, target);
            }
            OutputView.printGridStatus(grid);
        }
    }}
