package chess.state;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Started implements State {

    private final Color turn;
    private final Board board;

    public Started(final Color turn, final Board board) {
        OutputView.printTurnMessage(turn.getName());
        this.turn = turn;
        this.board = board;
    }

    @Override
    public State start() {
        OutputView.printErrorMessage("[ERROR] 이미 start를 하여 다시 start를 할 수 없습니다.");
        return new Started(turn, board);
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(final String[] commands) {
        try {
            board.move(Position.create(commands[1]), Position.create(commands[2]), turn);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return new Started(turn, board);
        }

        OutputView.printBoard(board.getBoard());
        return new Started(turn.getOpposite(), board);
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }
}
