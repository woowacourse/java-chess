package chess.view;

import chess.domain.board.Pieces;

public class OutputView {

    private final ViewDataConverter converter = new ViewDataConverter();

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printPieces(Pieces pieces) {
        CharSequence viewData = converter.convertToViewData(pieces);
        System.out.println(viewData);
    }

    public void printExceptionMessage(Exception exception) {
        System.out.printf("[ERROR] %s%n", exception.getMessage());
    }
}
