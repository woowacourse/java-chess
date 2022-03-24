package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.stream.Collectors;

public class OutputView {

    public static void printStartView() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Board board) {
        for (int i = 7; i >= 0; i--) {
            String rankLine = board.getRank(i).getPieces().stream()
                    .map(Piece::getSignature)
                    .collect(Collectors.joining());

            System.out.println(rankLine);
        }
    }
}
