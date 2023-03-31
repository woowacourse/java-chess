package chess.controller.room.join;

import chess.domain.game.state.StatusType;

public interface JoinBoardOutput {

    void printJoinBoardSuccess(StatusType statusType);
}
