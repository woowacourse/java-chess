package chess.view;

import chess.dto.BoardDTO;
import chess.dto.PiecesDTO;

public class OutputView {

    public static void printStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 프로그램 종료 : exit");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(PiecesDTO boardDTO) {
        System.out.println(boardDTO.getPieces());
    }

    public static void printStatus(BoardDTO boardDTO) {
        System.out.println("### 진행 사항");
        System.out.println("BLACK - 점수 : " + boardDTO.getBlackScore());
        System.out.println("WHITE - 점수 : " + boardDTO.getWhiteScore());
        System.out.println("WINNER : " + boardDTO.getWinner());
        System.out.println();
    }
}
