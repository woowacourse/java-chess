package chess.game;

import chess.board.Board;
import chess.board.BoardInitializer;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.display.BoardDisplayConverter;
import chess.view.display.RankDisplay;
import java.util.List;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Board board = BoardInitializer.createBoard();
        BoardDisplayConverter converter = new BoardDisplayConverter();

        outputView.printInitMessage();
        outputView.printCommandRequestMessage();
        if (inputView.isCommandStart()) {
            List<RankDisplay> rankDisplays = converter.convert(board.getPieces());
            outputView.printBoard(rankDisplays);
        }
    }
}
