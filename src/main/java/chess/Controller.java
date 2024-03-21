package chess;

import java.util.Scanner;
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
        Board board = new Board();
        if (inputView.readStartCommand()) {
            play(board);
        }
    }

    private void play(Board board) {
        while (true) {
            outputView.printBoard(board);
            Scanner scanner = new Scanner(System.in);

            String s = scanner.nextLine();
            String[] split = s.split(" ");

            String source = split[0];
            String target = split[1];

            String[] sourceInput = source.split(",");
            int moveStartRank = Integer.parseInt(sourceInput[0]);
            char moveStartFile = sourceInput[1].toCharArray()[0];

            String[] targetInput = target.split(",");
            int moveTargetRank = Integer.parseInt(targetInput[0]);
            char moveTargetFile = targetInput[1].toCharArray()[0];

            try {
                board.move(new Coordinate(moveStartRank, (char) moveStartFile), new Coordinate(moveTargetRank, (char) moveTargetFile));
            } catch (Exception e) {
                System.out.println("[ERROR] : " + e.getMessage());
            }
        }
    }
}
