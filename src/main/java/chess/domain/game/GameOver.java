package chess.domain.game;

import chess.domain.board.piece.Color;
import chess.dto.MoveCommandDto;
import chess.domain.board.Board;
import chess.model.GameResult;

final class GameOver extends Started {

    private static final String GAME_NOT_RUNNING_EXCEPTION_MESSAGE = "이미 종료된 게임입니다.";

    GameOver(Board board) {
        super(board, GameState.OVER);
    }

    @Override
    public Color getCurrentTurnColor() {
        throw new UnsupportedOperationException(GAME_NOT_RUNNING_EXCEPTION_MESSAGE);
    }

    @Override
    public Game moveChessmen(MoveCommandDto moveCommand) {
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
    public String toString() {
        return "GameOver{" + "board=" + board + '}';
    }
}
