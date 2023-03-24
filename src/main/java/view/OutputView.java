package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Piece;
import dto.ScoreDto;

public class OutputView {
    public static void printChessBoard(Map<Square, Piece> board) {
        System.out.println();
        List<String> messages = new ArrayList<>();
        for (Rank value : Rank.values()) {
            StringBuilder stringBuilder = new StringBuilder();
            addPieceName(board, value, stringBuilder);
            messages.add(stringBuilder.toString());
        }
        Collections.reverse(messages);

        for (String message : messages) {
            System.out.println(message);
        }
    }

    private static void addPieceName(Map<Square, Piece> board, Rank value, StringBuilder stringBuilder) {
        for (File file : File.values()) {
            stringBuilder.append(pieceToString(board.get(Square.of(file, value))));
        }
    }

    private static char pieceToString(Piece piece) {
        char label = piece.getType().getLabel();
        if (!piece.isWhite()) {
            return Character.toUpperCase(label);
        }
        return label;
    }

    public static void printChessInfo() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 현재 점수 출력 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printErrorMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public static void printScores(List<ScoreDto> scoreDto) {
        System.out.println();
        for (ScoreDto dto : scoreDto) {
            System.out.println(String.join(" : ", dto.getCamp(), String.valueOf(dto.getScore())));
        }
    }

    public static void printGameRoomInfo() {
        System.out.println();
        System.out.println(">> 방 생성 : CREATE - 예. CREATE test");
        System.out.println(">> 방 입장 : JOIN - 예. JOIN test");
        System.out.println(">> 목록 출력 : LIST");
        System.out.println(">> 종료 : EXIT");
    }

    public static void printGameRooms(List<String> gameRooms) {
        System.out.println();
        if (gameRooms.size() == 0) {
            System.out.println("생성된 게임이 없습니다.");
            return;
        }
        for (int i = 0; i < gameRooms.size(); i++) {
            System.out.println(i + 1 + ". " + gameRooms.get(i));
        }
    }

    public static void printIsSavedGame(int size) {
        System.out.println();
        if (size > 0) {
            System.out.println("저장된 게임을 불러옵니다.");
            return;
        }
        System.out.println("저장된 내역이 없습니다. 게임을 새로 시작합니다.");
    }
}
