package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Game;
import chess.domain.game.GameVisual;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;

public abstract class PlayingState extends StageState {

    private final PieceColor currentTurnColor;

    protected PlayingState(final Game game, PieceColor color) {
        super(game);
        currentTurnColor = color;
    }

    @Override
    public GameState execute(final CommandAsString command) {
        System.out.println("launched execute from playing state");
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
        Game newGame = moveInBoard(sourceName, targetName, currentTurnColor);
        if (newGame.isGameOver()) {
            return new EndState(newGame);
        }
        return otherTurnState(newGame);
    }

    protected abstract GameState otherTurnState(final Game game);

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
