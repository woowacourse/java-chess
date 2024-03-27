package controller;

import command.Command;
import domain.chessboard.ChessBoard;
import domain.coordinate.Coordinate;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameGuide();

        ChessBoard chessBoard = initializeBoard();
        outputView.printBoard(chessBoard.getBoard());

        startGame(chessBoard);
    }

    private ChessBoard initializeBoard() {
        List<String> commands = inputView.receiveCommands();
        if (Command.isStartCommand(commands.get(0))) {
            return new ChessBoard();
        }
        throw new IllegalArgumentException("게임을 먼저 시작하세요.");
    }

    private void startGame(ChessBoard chessBoard) {

        boolean isPlaying;
        do {
            isPlaying = playGame(chessBoard);
        } while (isPlaying);
    }


    private boolean playGame(ChessBoard chessBoard) {
        List<String> commands = inputView.receiveCommands();

        if (Command.isCommandMove(commands.get(0))) {
            move(chessBoard, commands);
            outputView.printBoard(chessBoard.getBoard());
            return true;
        }
        return false;
    }

    private void move(ChessBoard chessBoard, List<String> commands) {
        Coordinate start = Coordinate.from(commands.get(1));
        Coordinate destination = Coordinate.from(commands.get(2));

        chessBoard.playTurn(start, destination);
    }
}
