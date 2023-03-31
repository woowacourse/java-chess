package chess.view.resposne;

import chess.controller.game.status.StatusOutput;
import chess.controller.game.status.StatusResponse;

public class StatusOutputView implements StatusOutput {

    @Override
    public void printStatus(StatusResponse statusResponse) {
        System.out.println("현재 점수");
        System.out.println("흰색 : " + statusResponse.getWhiteScore());
        System.out.println("검정색 : " + statusResponse.getBlackScore());
    }
}
