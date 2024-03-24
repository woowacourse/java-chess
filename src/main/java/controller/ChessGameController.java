package controller;

import domain.chessboard.ChessBoard;
import domain.coordinate.Coordinate;
import domain.piece.Color;
import java.util.List;
import view.InputView;
import view.OutputView;
import view.util.Command;

public class ChessGameController {

    private static final Color DEFAULT_START_COLOR = Color.WHITE;

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
        Color currentTurn = DEFAULT_START_COLOR;

        boolean isPlaying;
        do {
            isPlaying = playGame(chessBoard, currentTurn);
            currentTurn = changeTurn(currentTurn);
        } while (isPlaying);
    }

    private Color changeTurn(Color currentTurn) {
        if (currentTurn == Color.BLACK) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    private boolean playGame(ChessBoard chessBoard, Color currentTurn) {
        List<String> commands = inputView.receiveCommands();

        if (isCommandMove(commands.get(0))) {
            move(chessBoard, currentTurn, commands);
            outputView.printBoard(chessBoard.getBoard());
            return true;
        }
        return false;
    }

    private static boolean isCommandMove(String commandIdentifier) {
        if (Command.isEndCommand(commandIdentifier)) {
            return false;
        }
        if (Command.isStartCommand(commandIdentifier)) {
            throw new IllegalArgumentException("이미 시작한 상태 입니다.");
        }
        return true;
    }

    private void move(ChessBoard chessBoard, Color currentTurn, List<String> commands) {
        Coordinate start = Coordinate.from(commands.get(1));
        Coordinate destination = Coordinate.from(commands.get(2));

        chessBoard.playTurn(start, destination, currentTurn);
    }
}
