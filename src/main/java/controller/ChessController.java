package controller;

import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardGenerator;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public class ChessController {

    public void start() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = InputView.startCommand();

        if (input.equals(InputView.START)) {
            OutputView.printBoard(chessBoard);
            play(chessBoard);
        }
    }

    private void play(ChessBoard chessBoard) {
        try {
            String input = InputView.playCommand();
            if (input.equals(InputView.END)) {
                System.out.println("종료되었습니다.");
                return;
            }

            move(input, chessBoard);
            play(chessBoard);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            play(chessBoard);
        }
    }

    private void move(String input, ChessBoard chessBoard) {
        String[] positions = input.split(InputView.DELIMITER);
        Position source = generatePosition(positions[0]);
        Position target = generatePosition(positions[1]);
        chessBoard.move(source, target);
        OutputView.printBoard(chessBoard);
    }

    private Position generatePosition(String value) {
        String[] position = value.split("");
        return Position.of(position[1], position[0]);
    }
}
