package chess.domain.game;

import static chess.console.view.InputView.FROM_POSITION_INDEX;
import static chess.console.view.InputView.TO_POSITION_INDEX;

import chess.console.view.OutputView;
import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    public Board start(BoardGenerator boardGenerator) {
        return boardGenerator.create();
    }

    public Board move(List<String> inputs, Board board) {
        try {
            validCheck(board);
            board.move(Position.of(inputs.get(FROM_POSITION_INDEX))
                    , Position.of(inputs.get(TO_POSITION_INDEX)));
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return board;
    }

    public Score status(Board board) {
        Score score = null;
        try {
            validCheck(board);
            score = board.createResult();

        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return score;
    }

    private void validCheck(Board board) {
        if (board != null && board.check()) {
            throw new IllegalStateException("현재 check 상황입니다.");
        }
    }

    public boolean isEnd(Command command, Board board) {
        return command.isEnd() || isCheckmate(board);
    }

    private boolean isCheckmate(Board board) {
        return board != null && board.checkmate();
    }
}
