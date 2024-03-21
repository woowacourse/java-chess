package chess;

import chess.domain.ChessBoard;
import chess.domain.ChessSpaceGenerator;
import chess.domain.position.Position;
import java.util.List;

public class ChessMachine {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessMachine(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printStartGameMessage();
        outputView.printCommandGuideMessage();

        ChessBoard chessBoard = new ChessBoard(new ChessSpaceGenerator());

        if (!inputView.getCommand().equals("start")) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        outputView.printChessBoard(chessBoard);

        while (true) {
            List<String> fromTo = inputView.getMoveCommand();

            if (!fromTo.get(0).equals("move")) {
                break;
            }

            chessBoard.move(Position.of(fromTo.get(1)), Position.of(fromTo.get(2)));
            outputView.printChessBoard(chessBoard);
        }
    }
}
