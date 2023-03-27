package chess.domain.state;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import chess.domain.state.command.Command;

public class Run extends Runnable {

    private final Turn turn;

    public Run(final Turn turn) {
        this.turn = turn;
    }

    @Override
    public ChessState changeStateByCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 진행중입니다");
        }
        if (command.isEnd()) {
            return new End();
        }
        validateSize(command);
        return this;
    }

    private void validateSize(final Command command) {
        if (command.sizeParameters() != 2) {
            throw new IllegalArgumentException("제대로 입력되지 않았습니다.");
        }
    }

    @Override
    public ChessState changeTurn() {
        return new Run(turn.change());
    }

    @Override
    public boolean isInCorrectTurn(final Color color) {
        return this.turn.isIncorrect(color);
    }

    @Override
    public ChessState finish() {
        return new Checkmate(turn);
    }

    @Override
    public String findCurrentTurn() {
        return turn.convertToColorLabel();
    }
}
