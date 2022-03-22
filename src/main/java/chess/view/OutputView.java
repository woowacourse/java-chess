package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.util.stream.Collectors;

public class OutputView {

    public static void printStartView() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {
        for (int i = 8; i >= 1; i--) {
            String rankLine = board.getRank(i).getPieces().stream()
                    .map(Piece::getSignature)
                    .collect(Collectors.joining());

            System.out.println(rankLine);
        }
    }
}
