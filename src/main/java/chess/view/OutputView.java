package chess.view;

import chess.dto.response.ChessBoardDto;
import chess.dto.response.StatusDto;

import java.util.List;

public final class OutputView {

    private static final String COMMAND_INFORMATION = "> 게임 시작 : start\n" +
            "> 게임 종료 : end \n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    public static final String GAME_ROOM_DELEMITER = "\n";

    private OutputView() {
    }

    public static void printGameRooms(List<String> users) {
        System.out.println("<진행 중인 체스 게임 목록>");
        System.out.println(convertToText(users));
        System.out.println();
    }

    private static String convertToText(List<String> users) {
        if (users.isEmpty()) {
            return "진행 중인 게임이 없습니다.";
        }
        return String.join(GAME_ROOM_DELEMITER, users);
    }

    public static void printInsertUserMessage() {
        System.out.println("사용자 이름을 입력하세요.");
    }

    public static void printStartMessage(String user) {
        System.out.println("> " + user + "의 체스 게임을 시작합니다.");
        System.out.println(COMMAND_INFORMATION);
    }

    public static void printNotStartedGameMessage() {
        System.out.println("아직 게임이 시작되지 않았습니다.");
    }

    public static void printChessBoard(ChessBoardDto chessBoardDto) {
        List<String> chessBoard = chessBoardDto.getChessBoard();
        System.out.println();
        for (String oneRow : chessBoard) {
            System.out.println(oneRow);
        }
        System.out.println();
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printStatus(StatusDto statusDto) {
        System.out.println("검은색 점수 : " + statusDto.getBlackScore());
        System.out.println("흰색 점수 : " + statusDto.getWhiteScore());
        System.out.println("승자 : " + statusDto.getWinner());
        System.out.println();
    }

    public static void printTurn(String turn) {
        System.out.println("===========");
        System.out.println(turn + "의 차례");
    }

    public static void printKingDeadMessage() {
        System.out.println("King이 죽었으므로 게임을 종료합니다.");
    }
}
