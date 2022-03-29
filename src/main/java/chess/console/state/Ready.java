package chess.console.state;

import static chess.console.view.InputView.MENU_INDEX;

import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.generator.BasicBoardGenerator;
import chess.domain.board.generator.BoardGenerator;

public class Ready implements State {

    private final BoardGenerator boardGenerator = new BasicBoardGenerator();

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State run(String[] inputs) {
        Command command = Command.of(inputs[MENU_INDEX]);

        if (command.isEnd()) {
            return new End();
        }

        if (!command.isStart()) {
            throw new IllegalArgumentException("게임이 시작하지 않았습니다.");
        }

        Board board = new Board();
        board.initBoard(boardGenerator);
        OutputView.printBoard(board.getBoard());
        return new Running(board);
    }
}
