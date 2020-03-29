package chess.view;

import chess.controller.dto.ResponseDto;
import chess.domain.player.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;

public class OutputView {

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printResponse(ResponseDto responseDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        Map<Position, String> board = responseDto.getBoard();
        for (Rank rank : Rank.values()) {
            appendByRank(stringBuilder, board, rank);
        }
        stringBuilder.append(getFileNames());
        System.out.println(stringBuilder.toString());

        if (responseDto.getScores() != null) {
            printStatus(responseDto.getScores());
        }
    }

    private void appendByRank(StringBuilder stringBuilder, Map<Position, String> board, Rank rank) {
        stringBuilder.append(getRankString(board, rank));
        stringBuilder.append(" ( rank " + rank.getRank() + " )");
        stringBuilder.append("\n");
    }

    private String getRankString(Map<Position, String> board, Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            Position position = Position.of(file, rank);
            String piece = getPiece(board, position);
            stringBuilder.append("[" + piece + "]");
        }
        return stringBuilder.toString();
    }

    private String getFileNames() {
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : File.values()) {
            stringBuilder.append(" " + file.toString() + " ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String getPiece(Map<Position, String> board, Position position) {
        String piece = board.get(position);
        if (board.get(position) == null) {
            return " ";
        }
        return piece;
    }

    private void printStatus(Map<Team, Double> status) {
        status.entrySet()
                .stream()
                .map(entry -> entry.getKey().toString() + " : " + entry.getValue())
                .forEach(System.out::println);
    }
}
