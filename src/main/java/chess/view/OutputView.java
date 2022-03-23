package chess.view;

import chess.controller.BoardDto;

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
}
