package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.CurrentPieces;
import chess.domain.piece.Piece;

import static chess.domain.piece.Position.POSITIONS;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요");
    }

    public static void printChessBoard(CurrentPieces currentPieces) {
        int index = 0;
        for (String key : POSITIONS.keySet()) {
            if (index % 8 == 0) {
                System.out.println();
            }
            index++;
            Piece piece = currentPieces.findByPosition(POSITIONS.get(key));
            System.out.print(piece.getName());
        }
        System.out.println();
    }

    public static void printStatus(CurrentPieces currentPieces) {
        System.out.println("black: " + currentPieces.sumScoreByColor(Color.BLACK));
        System.out.println("white: " + currentPieces.sumScoreByColor(Color.WHITE));
    }
}
