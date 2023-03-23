package chess.view;

import chess.controller.state.BoardDTO;
import chess.domain.piece.Color;

import java.util.List;

public final class OutputView {

    private static final String DELIMITER = "";

    public void startMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(BoardDTO boardDTO) {
        List<List<String>> board = boardDTO.getSquares();
        board.forEach(this::printRank);
    }

    private void printRank(List<String> rank) {
        String format = String.join(DELIMITER, rank);

        System.out.println(format);
    }

    public void printErrorMesage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printGuideMessage() {
        System.out.println("명령어를 다시 입력하세요");
    }

    public void printResult(Color turn) {
        System.out.println(turn + "의 패배입니다.");
    }

    public void printStatus(double calculateWhiteScore, double calculateBlackScore) {
        System.out.println("흰색의 점수 : " + calculateWhiteScore);
        System.out.println("흑색의 점수 : " + calculateBlackScore);
    }
}
