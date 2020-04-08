package chess.view;

import chess.controller.dto.ResponseDto;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;
import java.util.Objects;

public class OutputView {

    public static void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printResponse(ResponseDto responseDto) {
        if (Objects.nonNull(responseDto.getStatus())) {
            printStatus(responseDto);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        makeOutputResponse(responseDto, stringBuilder);
        stringBuilder.append(getFileNames());
        stringBuilder.append("\n");
        System.out.println(stringBuilder.toString());
    }

    private static void makeOutputResponse(final ResponseDto responseDto, final StringBuilder stringBuilder) {
        Map<Position, String> board = responseDto.getBoard();
        for (Rank rank : Rank.values()) {
            stringBuilder.append(getRankString(board, rank));
            stringBuilder.append(" ( rank " + rank.getRank() + " )");
            stringBuilder.append("\n");
        }
    }

    private static String getRankString(Map<Position, String> board, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            String piece = getPiece(board, position);
            stringBuilder.append("[" + piece + "]");
        }
        return stringBuilder.toString();
    }

    private static String getFileNames() {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            stringBuilder.append(" " + file.toString() + " ");
        }
        return stringBuilder.toString();
    }

    private static String getPiece(Map<Position, String> board, Position position) {
        String piece = board.get(position);
        if (board.get(position) == null) {
            return " ";
        }
        return piece;
    }

    public static void printStatus(ResponseDto responseDto) {
        responseDto.getStatus()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey().toString() + " : " + entry.getValue())
                .forEach(System.out::println);
        System.out.println("우승자는 : " + responseDto.getWinner() + " 입니다.");
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }
}
