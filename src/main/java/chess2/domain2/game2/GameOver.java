package chess2.domain2.game2;

import chess2.dto2.MoveCommandDto;
import chess2.domain2.board2.Board;

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
    public GameResult result() {
        return new GameResult(board.getBoard());
    }

    @Override
    public String toString() {
        return "GameOver{" + "board=" + board + '}';
    }
}
