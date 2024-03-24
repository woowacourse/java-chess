package controller;

import domain.chessboard.ChessBoard;
import domain.coordinate.Coordinate;
import view.InputView;
import view.OutputView;
import view.dto.Commands;
import view.dto.CoordinateRequest;
import view.util.Command;

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
        Commands commands = inputView.receiveCommands();

        if (Command.START != commands.command()) {
            throw new IllegalArgumentException("게임을 먼저 시작하세요.");
        }
        return new ChessBoard();
    }

    private void startGame(ChessBoard chessBoard) {
        boolean isPlaying;

        do {
            isPlaying = playGame(chessBoard);
        } while (isPlaying);
    }

    private boolean playGame(ChessBoard chessBoard) {
        Commands commands = inputView.receiveCommands();

        if (isCommandMove(commands)) {
            move(chessBoard, commands);
            outputView.printBoard(chessBoard.getBoard());
            return true;
        }
        return false;
    }

    private static boolean isCommandMove(Commands commands) {
        if (commands.command() == Command.END) {
            return false;
        }
        if (commands.command() == Command.START) {
            throw new IllegalArgumentException("이미 시작한 상태 입니다.");
        }
        return true;
    }

    private void move(ChessBoard chessBoard, Commands commands) {
        Coordinate start = createCoordinate(commands.startCoordinate());
        Coordinate destination = createCoordinate(commands.destinationCoordinate());

        chessBoard.playTurn(start, destination);
    }

    private Coordinate createCoordinate(CoordinateRequest coordinateRequest) {
        return new Coordinate(coordinateRequest.row(), coordinateRequest.column());
    }
}
