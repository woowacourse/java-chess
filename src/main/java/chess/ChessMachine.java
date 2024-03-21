package chess;

import chess.domain.ChessBoard;
import chess.domain.ChessSpaceGenerator;
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

            if (fromTo.size() != 2) {
                break;
            }

            chessBoard.move(fromTo.get(0), fromTo.get(1));
            outputView.printChessBoard(chessBoard);
        }
    }
}
