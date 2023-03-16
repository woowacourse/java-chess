package view;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Rank;

import java.util.Map;

public class OutputView {
    public void printChessBoard(Map<Position, Piece> chessBoard) {
        Rank currentRank = Rank.EIGHT;
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            if (!currentRank.equals(positionPieceEntry.getKey().getRank())) {
                currentRank = currentRank.getPrevious();
                System.out.println();
            }
            System.out.print(positionPieceEntry.getValue().getCategory().getPieceValue());
        }
        System.out.println();
        System.out.println();
    }

    public void printGameGuideMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

}
