package chess.domain.game;

import chess.dto.MoveCommandDto;
import chess.domain.board.Board;

final class GameOver extends Started {

    GameOver(Board board) {
        super(board);
    }

    @Override
    public Game moveChessmen(MoveCommandDto moveCommand) {
        throw new UnsupportedOperationException("이미 종료된 게임입니다.");
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
