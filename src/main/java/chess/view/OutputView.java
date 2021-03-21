package chess.view;

import chess.domain.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private OutputView() {
    }

    public static void printBoard(Map<Position, Piece> chessBoard) {
        List<String> chessBoardNames = chessBoard.values().stream()
            .map(Piece::getPieceName)
            .collect(Collectors.toList());

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                System.out.print(chessBoardNames.get(i * 8 + j));
            }
            System.out.println();
        }
    }

    public static void printInitMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
}
