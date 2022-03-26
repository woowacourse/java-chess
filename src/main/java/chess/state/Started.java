package chess.state;

import chess.domain.Board;
import chess.domain.Result;
import chess.domain.Score;
import chess.domain.move.Move;
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
            return movePiece(commands);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return new Started(turn, board);
        }
    }

    private State movePiece(final String[] commands) {
        final Position from = Position.create(commands[1]);
        final Position to = Position.create(commands[2]);

        Move move = new Move(board, turn);
        move.isMovable(from, to);

        if (move.isCheckmate(to)) {
            return runCheckmate();
        }
        return runMovePiece(from, to);
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
