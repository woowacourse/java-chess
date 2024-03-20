package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class ChessGame {
    private final Map<GameCommand, Runnable> COMMANDS = Map.of(
            GameCommand.MOVE, this::move,
            GameCommand.START, this::start,
            GameCommand.END, this::end
    );

    public void run() {
        String inputCommand = InputView.readGameCommand();
        GameCommand command = GameCommand.from(inputCommand);

        Board board = BoardFactory.createInitialBoard();
        OutputView.printGameStartMessage();
        OutputView.printChessBoard(board);

        // start -> 출력
        // 다음부터는 move -> 이 메서드는? command를 따라감?
        GameCommand command1 = GameCommand.from(inputCommand);

    }

    private void move() {
        return;
    }

    private void start() {

    }

    private void end() {

    }
}
