package chess.view;

import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.dto.RankDto;
import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printCommandNotice() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
        System.out.println();
    }

    public static void printRequestInputStart() {
        System.out.println("체스보드가 비워졌습니다. 게임 시작을 원한다면 'start'를 입력해주세요.");
    }

    public static void printChessStarted() {
        System.out.println("체스판 초기 세팅이 완료되었습니다.");
        System.out.println("체스게임을 시작합니다.");
        System.out.println();
    }

    public static void printChessBoard(List<RankDto> rankDtos) {
        int size = rankDtos.size();
        List<String> yPointNames = yPointNames();

        for (int i = 0; i < size; i++) {
            printRank(rankDtos.get(i), yPointNames.get(i));
        }

        System.out.println("--------");
        System.out.println(xPointNames());
        System.out.println();
    }

    private static void printRank(RankDto rankDto, String yPoint) {
        for (String piece : rankDto.getPieces()) {
            System.out.print(piece);
        }
        System.out.println(" | " + yPoint);
    }

    private static List<String> yPointNames() {
        return Arrays.stream(Ypoint.values())
            .map(Ypoint::getName)
            .collect(Collectors.toList());
    }

    private static String xPointNames() {
        return Arrays.stream(Xpoint.values())
            .map(Xpoint::getName)
            .collect(Collectors.joining());
    }

    public static void printFinishWithReason(String color) {
        System.out.println(color + "의 승리로 체스게임이 종료 되었습니다.");
        System.out.println();
    }

    public static void printWinner(String color) {
        System.out.println(color + "의 승리입니다.");
    }

    public static void printScoreStatus(double totalWhiteScore, double totalBlackScore) {
        System.out.println(Color.BLACK + " : " + totalBlackScore);
        System.out.println(Color.WHITE + " : " + totalWhiteScore);
        System.out.println();
    }

    public static void printErrorException(String message) {
        System.out.println(message);
        System.out.println();
    }
}
