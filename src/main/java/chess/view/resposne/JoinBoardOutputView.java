package chess.view.resposne;

import chess.controller.room.join.JoinBoardOutput;
import chess.domain.game.state.StatusType;

public class JoinBoardOutputView implements JoinBoardOutput {

    @Override
    public void printJoinBoardSuccess(StatusType statusType) {
        System.out.println("게임에 참여했습니다");
        System.out.println("현재 게임 상태");
        System.out.println(StatusMapper.map(statusType));
    }
}
