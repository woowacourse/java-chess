package chess.view;

import chess.domain.Color;
import chess.domain.ScoreStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import static chess.domain.position.Position.CHANGE_LINE_POINT;
import static chess.domain.position.Position.POSITIONS;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printRequestCommandMessage() {
        System.out.println("명령어를 입력해주세요.");
    }

    public static void printChessBoard(Pieces pieces) {
        for (int i = 0; i < POSITIONS.size(); i++) {
            checkChangeLinePoint(i);
            Piece piece = pieces.findByPosition(POSITIONS.get(i));
            System.out.print(piece.getName());
        }
        System.out.println();
    }

    private static void checkChangeLinePoint(int i) {
        if (i % CHANGE_LINE_POINT == 0) {
            System.out.println();
        }
    }

    public static void printStatus(ScoreStatus scoreStatus) {
        System.out.println("black: " + scoreStatus.totalScoreByColor().get(Color.BLACK));
        System.out.println("white: " + scoreStatus.totalScoreByColor().get(Color.WHITE));
    }
}
