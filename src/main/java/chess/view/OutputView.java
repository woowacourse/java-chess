package chess.view;

import chess.domain.Color;
import chess.domain.piece.CurrentPieces;
import chess.domain.piece.Piece;

import static chess.domain.piece.Position.POSITIONS;

public class OutputView {
    public static final int CHANGE_LINE_POINT = 8;

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요");
    }

    public static void printRequestCommandMessage() {
        System.out.println("명령어를 입력해주세요.");
    }

    public static void printChessBoard(CurrentPieces currentPieces) {
        for (int i = 0; i < POSITIONS.size(); i++) {
            checkChangeLinePoint(i);
            Piece piece = currentPieces.findByPosition(POSITIONS.get(i));
            System.out.print(piece.getName());
        }
        System.out.println();
    }

    private static void checkChangeLinePoint(int i) {
        if (i % CHANGE_LINE_POINT == 0) {
            System.out.println();
        }
    }

    public static void printStatus(CurrentPieces currentPieces) {
        System.out.println("black: " + currentPieces.sumScoreByColor(Color.BLACK));
        System.out.println("white: " + currentPieces.sumScoreByColor(Color.WHITE));
    }
}
