package controller;

import chessboard.ChessBoard;
import coordinate.Coordinate;
import java.util.List;
import position.Column;
import position.Row;
import view.InputView;
import view.OutputView;
import view.util.ColumnSymbol;
import view.util.Command;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printInfo();
        ChessBoard chessBoard = new ChessBoard();
        List<String> command = inputView.receiveCommands();

        if (Command.START.isSameIdentifier(command.get(0))) {
            outputView.printBoard(chessBoard.getBoard());
            startGame(chessBoard);
        }
    }

    private void startGame(ChessBoard chessBoard) {
        boolean startTurn = false;
        while (true) {
            List<String> commands = inputView.receiveCommands();
            if (Command.END.isSameIdentifier(commands.get(0))) {
                break;
            }
            if (Command.START.isSameIdentifier(commands.get(0))) {
                throw new IllegalArgumentException("이미 시작한 상태 입니다.");
            }
            Coordinate start = createCoordinate(commands.get(1));
            Coordinate to = createCoordinate(commands.get(2));
            chessBoard.playTurn(start, to, startTurn);
            startTurn = !startTurn;
            outputView.printBoard(chessBoard.getBoard());
        }
    }

    private Coordinate createCoordinate(String input) {
        String[] split = input.split("");
        int column = ColumnSymbol.from(split[0]).getPosition();
        int row = 8 - Integer.parseInt(split[1]);

        return new Coordinate(new Row(row), new Column(column));
    }
}
