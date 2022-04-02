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

    private Board board;

    public void start(BoardGenerator boardGenerator) {
        board = boardGenerator.create();
    }

    public void move(List<String> inputs) {
        try {
            validCheck();
            board.move(Position.of(inputs.get(FROM_POSITION_INDEX))
                    , Position.of(inputs.get(TO_POSITION_INDEX)));
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    public Score status() {
        Score score = null;
        try {
            validCheck();
            score = board.createResult();

        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        return score;
    }

    private void validCheck() {
        if (board != null && board.check()) {
            throw new IllegalStateException("현재 check 상황입니다.");
        }
    }

    public boolean isEnd(Command command) {
        return command.isEnd() || isCheckmate();
    }

    private boolean isCheckmate() {
        return board != null && board.checkmate();
    }

    public Board getBoard() {
        return board;
    }

    public String getTurn() {
        return board.getTurn().name();
    }
}
