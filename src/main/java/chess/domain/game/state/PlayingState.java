package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board2;
import chess.domain.game.GameVisual;
import chess.domain.piece.PieceColor;
import chess.domain.position.PositionName;

public abstract class PlayingState extends StageState {

    private final PieceColor currentTurnColor;

    protected PlayingState(final Board2 board, PieceColor color) {
        super(board);
        currentTurnColor = color;
    }

    @Override
    public GameState execute(final CommandAsString command) {
        if (command.isEnd()) {
            return new EndState(board());
        }
        if (command.isMove()) {
            return executeMove(command.source(), command.target());
        }
        if (command.isStatus()) {
            return this;
        }
        throw new IllegalArgumentException("가능한 명령이 아닙니다.");
    }

    protected GameState executeMove(final PositionName source, final PositionName target) {
        moveInBoard(source, target, currentTurnColor);
        if (kingDead()) {
            return new EndState(board());
        }
        return otherTurnState();
    }

    protected abstract GameState otherTurnState();

    @Override
    public GameVisual gameVisual() {
        return null;
    }

    @Override
    public GameVisual statusVisual() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
