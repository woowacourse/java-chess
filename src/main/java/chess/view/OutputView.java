package chess.view;

import chess.view.display.PieceDisplay;
import chess.view.display.RankDisplay;
import java.util.List;

public class OutputView {

    public void printInitMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작: " + Command.START.getCommandName());
        System.out.println("> 게임 종료: " + Command.END.getCommandName());
        System.out.print("> 게임 이동 : " + Command.MOVE.getCommandName() + " source위치 target위치, ");
        System.out.println("예. " + Command.MOVE.getCommandName() + " b2 b3");
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
}
