package chess.view;

import java.util.Map;

import chess.domain.Position;
import chess.domain.piece.Piece;

public class OutputView {

    private OutputView() {
        throw new AssertionError();
    }

    public static void printGameGuide() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printChessBoard(Map<Position, Piece> pieces) {
        for (char i = '8'; i >= '1'; i--) {
            for (char j = 'a'; j <= 'h'; j++) {
                Position position = new Position(j, i);
                if (pieces.containsKey(position)) {
                    Piece piece = pieces.get(position);
                    System.out.print(piece.convertedName());
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
