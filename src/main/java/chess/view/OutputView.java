package chess.view;

import chess.view.display.PieceDisplay;
import chess.view.display.RankDisplay;
import java.util.List;

public class OutputView {

    public void printInitMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printCommandRequestMessage() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(List<RankDisplay> rankDisplays) {
        rankDisplays.forEach(this::printRank);
    }

    private void printRank(RankDisplay rankDisplay) {
        List<String> notations = rankDisplay.pieceDisplays()
                .stream()
                .map(PieceDisplay::getNotation)
                .toList();
        System.out.println(String.join("", notations));
    }
}
