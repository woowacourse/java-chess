package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board;
import chess.domain.result.Result;
import chess.domain.result.StatusResult;
import chess.domain.result.TurnResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public abstract class TurnState extends PlayingState {

    private final Color currentTurnColor;

    protected TurnState(final Board board, Color color) {
        super(board);
        currentTurnColor = color;
    }

    @Override
    public GameState execute(final CommandAsString command) {
        if (command.isEnd()) {
            return new EndState(currentBoard());
        }
        if (command.isMove()) {
            return executeMove(command.source(), command.target());
        }
        if (command.isStatus()) {
            return this;
        }
        throw new IllegalArgumentException("가능한 명령이 아닙니다.");
    }

    protected GameState executeMove(final Position sourceName, final Position targetName) {
        final Board newBoard = moveInBoard(sourceName, targetName, currentTurnColor);
        if (newBoard.kingCount() != 2) {
            return new EndState(newBoard);
        }
        return otherTurnState(newBoard);
    }

    protected abstract GameState otherTurnState(final Board board);

    @Override
    public Result turnResult() {
        return new TurnResult(currentBoard());
    }

    @Override
    public Result statusResult() {
        return new StatusResult(currentBoard());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
