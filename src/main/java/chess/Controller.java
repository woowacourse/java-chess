package chess;

import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.view.InputView;
import chess.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        play(new Board());
    }

    private void play(Board board) {
        String command = inputView.readCommand();
        if (command.equals("start")) {
            playChess(board);
            return;
        }

        if (command.equals("end")) {
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void playChess(Board board) {
        outputView.printBoard(board);
        String read = inputView.readCommand();
        if (read.startsWith("move ")) {
            List<Coordinate> coordinates = parseToCoordinates(read);
            board.move(coordinates.get(0), coordinates.get(1));
            playChess(board);
            return;
        }

        if (read.equals("end")) {
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private List<Coordinate> parseToCoordinates(String read) {
        String[] moveCommand = read.split("move ");
        String[] coordinates = moveCommand[1].split(" ");
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        return List.of(parseToCoordinate(coordinates[0]), parseToCoordinate(coordinates[1]));
    }

    private Coordinate parseToCoordinate(String input) {
        String[] split = input.split("");
        return new Coordinate(Integer.parseInt(split[1]), split[0].charAt(0));
    }
}
