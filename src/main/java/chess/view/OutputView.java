package chess.view;

import static java.util.stream.Collectors.joining;

import chess.domain.piece.Piece;
import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(List<List<Piece>> board) {
        board.stream()
                .map(OutputView::generateRow)
                .forEach(System.out::println);

        System.out.println();
    }

    private static String generateRow(List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::getNotation)
                .collect(joining());
    }
}
