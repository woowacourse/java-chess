package chess.console.view;

import chess.board.Team;
import chess.console.controller.dto.PieceDto;

import java.util.List;

public final class OutputView {

    private OutputView() {
    }

    public static void startGame() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(List<PieceDto> pieceDtos) {
        for (PieceDto pieceDto : pieceDtos) {
            System.out.print(pieceDto.getName());
            printNewLine(pieceDto);
        }
    }

    private static void printNewLine(PieceDto pieceDto) {
        if (pieceDto.isLastFile()) {
            System.out.println();
        }
    }


    public static void printFinalResult(Team team, double whiteScore, double blackScore) {
        System.out.println("우승팀은 " + team.name() + "입니다.");
        System.out.println("블랙 팀: " + blackScore);
        System.out.println("화이트 팀: " + whiteScore);
    }

    public static void printFinishMessage() {
        System.out.println("게임이 끝났습니다.");
        System.out.println("결과를 확인하려면 status를 입력해주고, 그냥 끝내려면 아무 키나 입력하세요.");
    }
}
