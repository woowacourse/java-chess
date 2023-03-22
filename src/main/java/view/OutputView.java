package view;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Rank;
import domain.piece.Side;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void printChessBoard(Map<Position, Piece> chessBoard) {
        Rank currentRank = Rank.EIGHT;
        for (Map.Entry<Position, Piece> positionPieceEntry : chessBoard.entrySet()) {
            currentRank = moveNextRankIfLast(currentRank, positionPieceEntry);
            System.out.print(PieceMapper.convertPieceCategoryToText(positionPieceEntry.getValue().getCategory()));
        }
        System.out.println();
        System.out.println();
    }

    private static Rank moveNextRankIfLast(Rank currentRank, Entry<Position, Piece> positionPieceEntry) {
        if (!currentRank.equals(positionPieceEntry.getKey().getRank())) {
            currentRank = currentRank.getPrevious();
            System.out.println();
        }
        return currentRank;
    }

    public void printGameGuideMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void printSideOfTurn(Side side) {
        System.out.println(side + " 진영의 말을 움직이세요");
    }
}
