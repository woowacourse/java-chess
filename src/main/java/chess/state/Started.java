package chess.state;

import chess.domain.Board;
import chess.domain.Result;
import chess.domain.Score;
import chess.domain.move.Move;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.view.OutputView;

public class Started implements State {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

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
        final Position from = Position.create(toPosition(commands, SOURCE_INDEX));
        final Position to = Position.create(toPosition(commands, TARGET_INDEX));

        final Move move = new Move(board, turn);
        move.isMovable(from, to);

        if (move.isCheckmate(to)) {
            return runCheckmate();
        }
        return runMovePiece(from, to);
    }

    private String toPosition(final String[] commands, final int index) {
        try {
            return commands[index];
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("[ERROR] source 위치와 target 위치를 입력해주세요.");
        }
    }

    private State runCheckmate() {
        OutputView.printResult(turn.getName(), Result.WIN.getName());
        return new Ended();
    }

    private State runMovePiece(final Position from, final Position to) {
        board.move(from, to);
        OutputView.printBoard(board.getBoard());
        return new Started(turn.getOpposite(), board);
    }

    @Override
    public State status() {
        final Score myScore = new Score(board, turn);
        final Score opponentScore = new Score(board, turn.getOpposite());

        OutputView.printScore(turn.getName(), myScore.getValue());
        OutputView.printScore(turn.getOpposite().getName(), opponentScore.getValue());
        OutputView.printResult(turn.getName(), Result.decide(myScore, opponentScore).getName());

        return new Started(turn, board);
    }

    @Override
    public boolean isNotEnded() {
        return true;
    }

}
