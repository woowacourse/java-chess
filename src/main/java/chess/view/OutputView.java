package chess.view;

import chess.controller.BoardDto;
import chess.controller.ScoresDto;
import java.util.Map;

public final class OutputView {

    public static void startGame() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void startGameBoard(final BoardDto boardDto) {
        String joinedPieces = String.join("", boardDto.getPieces());
        for (int i = 8; i <= joinedPieces.length(); i += 8) {
            System.out.println(joinedPieces.substring(i - 8, i));
        }
    }

    public static void printStatus(ScoresDto dto) {
        // 그거를 컨트롤러에서 toStatusDto 로 만들어서 어때여??
        for (String name : dto.getScores().keySet()) {
            System.out.println(name + " : " + dto.getScores().get(name));
        }
        System.out.println();
        System.out.printf("승부 결과 : %s\n", dto.getWinner());
    }
}
