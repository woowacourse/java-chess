package chess.view;

import chess.view.display.PieceDisplay;
import chess.view.display.RankDisplay;
import java.util.List;

public class OutputView {

    public void printInitMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작: " + Command.START.getText());
        System.out.println("> 게임 종료: " + Command.END.getText());
        System.out.print("> 게임 이동 : " + Command.MOVE.getText() + " source위치 target위치, ");
        System.out.println("예. " + Command.MOVE.getText() + " b2 b3");
        System.out.println("> 점수 확인 : " + Command.STATUS.getText());
    }

    public void printBoard(List<RankDisplay> rankDisplays) {
        rankDisplays.forEach(this::printRank);
    }

    private void printRank(RankDisplay rankDisplay) {
        List<String> notations = rankDisplay.pieceDisplays()
                .stream()
                .map(PieceDisplay::getNotation)
                .toList();
        System.out.println(String.join(" ", notations));
    }

    public void printScore(double whiteScore, double blackScore) {
        System.out.println(" --- 현재 점수 --- ");
        System.out.println("백 : " + whiteScore + "  -  " + blackScore + " 흑");
    }

    public void printEndMessage() {
        System.out.println("게임이 종료되었습니다.");
    }
}
