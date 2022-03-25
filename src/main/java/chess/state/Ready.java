package chess.state;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.BoardDto;
import chess.view.OutputView;

public class Ready implements State {
    private OutputView outputView = OutputView.getInstance();

    @Override
    public State start() {
        Board board = BoardFactory.newInstance();
        outputView.printBoard(BoardDto.from(board));

        return new Playing(board);
    }

    @Override
    public State move(String from, String to) {
        return alertReady();
    }

    @Override
    public State status() {
        return alertReady();
    }

    @Override
    public State end() {
        return new Finished();
    }

    private Ready alertReady() {
        throw new IllegalStateException("게임 시작 전엔 start 또는 end만 가능합니다.");
    }
}
