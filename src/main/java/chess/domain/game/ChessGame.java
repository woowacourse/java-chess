package chess.domain.game;

import static chess.console.view.InputView.FROM_POSITION_INDEX;
import static chess.console.view.InputView.TO_POSITION_INDEX;

import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    private Board board;

    public void start(BoardGenerator boardGenerator) {
        board = boardGenerator.create();
    }

    public void move(List<String> inputs) {
        validCheck();
        board.move(Position.of(inputs.get(FROM_POSITION_INDEX))
                , Position.of(inputs.get(TO_POSITION_INDEX)));
    }

    public Score status() {
        validCheck();
        return board.createResult();
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

    public Team getTurn() {
        return board.getTurn();
    }

    public void end() {
        board = null;
    }
}
