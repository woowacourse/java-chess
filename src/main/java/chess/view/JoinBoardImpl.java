package chess.view;

import chess.controller.room.join.JoinBoard;
import java.util.Optional;

public class JoinBoardImpl implements JoinBoard {

    private Optional<Integer> boardId = Optional.empty();

    @Override
    public void join(int boardId) {
        this.boardId = Optional.of(boardId);
    }

    public Optional<Integer> getBoardId() {
        return boardId;
    }
}
