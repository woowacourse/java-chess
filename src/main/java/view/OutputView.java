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
        System.out.println("> 체스 게임을 시작합니다.\n"
                + "> 게임 종료 : end\n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printErrorMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public static void printScores(List<ScoreDto> scoreDto) {
        for (ScoreDto dto : scoreDto) {
            System.out.println(String.join(" : ", dto.getCamp(), String.valueOf(dto.getScore())));
        }
    }

    public static void printGameRoomInfo() {
        System.out.println(">> 입장할 게임의 이름일 'JOIN' 과 함께 입력해 주세요. ex) JOIN chess1\n"
                + ">> 목록을 보시려면 'LIST'를 입력해주세요.\n"
                + ">> 게임을 생성하시려면 'CREATE'을 입력해주세요.\n"
                + ">> 종료하시려면 'EXIT'를 입력해주세요.");
    }

    public static void printGameRooms(List<String> gameRooms) {
        if(gameRooms.size() == 0) {
            System.out.println("생성된 게임이 없습니다.");
            return;
        }
        for (int i = 0; i < gameRooms.size(); i++) {
            System.out.println(i + 1 + gameRooms.get(i));
        }
    }

    public static void printIsSavedGame(int size) {
        if(size>0) {
            System.out.println("저장된 게임을 불러옵니다.");
            return;
        }
        System.out.println("저장된 내역이 없습니다. 게임을 새로 시작합니다.");
    }
}
