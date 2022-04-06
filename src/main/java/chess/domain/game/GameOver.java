package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.event.MoveCommand;
import chess.domain.game.statistics.GameResult;
import chess.domain.game.statistics.GameState;

final class GameOver extends Started {

    private static final String GAME_NOT_RUNNING_EXCEPTION_MESSAGE = "이미 종료된 게임입니다.";

    GameOver(Board board) {
        super(board);
    }

    @Override
    public Game moveChessmen(MoveCommand moveCommand) {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public GameResult getResult() {
        return new GameResult(board.toMap());
    }

    @Override
    protected GameState getState() {
        return GameState.OVER;
    }

    @Override
    public String toString() {
        return "GameOver{" + "board=" + board + '}';
    }
}
