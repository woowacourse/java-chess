package chess.controller;

import chess.domain.grid.Grid;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {

    public void run() {
        OutputView.printStartChess();
        playRound();
    }

    public void playRound(){
        Grid grid = new Grid();
        while (true) {
            String command = InputView.inputCommand();
            if (command.equals("end")) {
                return;
            }
            if (command.startsWith("move")) {
                move(grid, command);
            }
            OutputView.printGridStatus(grid);
        }
    }

    private void move(Grid grid, String command) {
        List<String> strGroup = Arrays.asList(command.split(" "));
        String sourceString = strGroup.get(1);
        String targetString = strGroup.get(2);
        Position source = new Position(sourceString.charAt(0), sourceString.charAt(1));
        Position target = new Position(targetString.charAt(0), targetString.charAt(1));

        grid.move(grid.findPiece(source), grid.findPiece(target));
    }
}
