package chess.view;

import chess.domain.piece.Piece;
import chess.domain.result.Score;
import chess.domain.side.Color;
import chess.domain.side.Side;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 상태 : status");
    }

    public void printBoard(final List<List<Piece>> pieces) {
        Collections.reverse(pieces);
        for (List<Piece> rank : pieces) {
            printRank(rank);
            System.out.println();
        }
    }

    private void printRank(List<Piece> rank) {
        for (Piece piece : rank) {
            System.out.print(convertPiece(piece));
        }
    }

    private String convertPiece(final Piece piece) {
        return PieceMapper.getPattern(piece);
    }

    public void printEndMessage() {
        System.out.println("게임 종료");
    }

    public void printStatus(Map<Side, Score> status) {
        for (Side side : status.keySet()) {
            System.out.printf("%s : %.1f 점\n", side.getColor().name(), status.get(side).getValue());
        }
    }

    public void printHigherScoreSide(Color winnerColor) {
        System.out.println(winnerColor + " 승");
    }
}
