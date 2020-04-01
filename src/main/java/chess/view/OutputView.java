package chess.view;

import chess.controller.dto.PieceDto;
import chess.controller.dto.ResponseDto;
import chess.domain.player.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.HashMap;
import java.util.Map;

public class OutputView {

    private static final Map<String, String> BLACK_FIGURE_MAP = new HashMap<>();
    private static final Map<String, String> WHITE_FIGURE_MAP = new HashMap<>();

    static {
        BLACK_FIGURE_MAP.put("KING", "\u265A");
        BLACK_FIGURE_MAP.put("QUEEN", "\u265B");
        BLACK_FIGURE_MAP.put("ROOK", "\u265C");
        BLACK_FIGURE_MAP.put("BISHOP", "\u265D");
        BLACK_FIGURE_MAP.put("KNIGHT", "\u265E");
        BLACK_FIGURE_MAP.put("PAWN", "\u265F");
        WHITE_FIGURE_MAP.put("KING", "\u2654");
        WHITE_FIGURE_MAP.put("QUEEN", "\u2655");
        WHITE_FIGURE_MAP.put("ROOK", "\u2656");
        WHITE_FIGURE_MAP.put("BISHOP", "\u2657");
        WHITE_FIGURE_MAP.put("KNIGHT", "\u2658");
        WHITE_FIGURE_MAP.put("PAWN", "\u2659");
    }

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printResponse(ResponseDto responseDto) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        Map<Position, PieceDto> board = responseDto.getBoard();
        for (Rank rank : Rank.values()) {
            appendByRank(stringBuilder, board, rank);
        }
        stringBuilder.append(getFileNames());
        System.out.println(stringBuilder.toString());

        if (responseDto.getScores() != null) {
            printStatus(responseDto.getScores());
        }
    }

    private void appendByRank(StringBuilder stringBuilder, Map<Position, PieceDto> board, Rank rank) {
        stringBuilder.append(getRankString(board, rank));
        stringBuilder.append(" ( rank " + rank.getRank() + " )");
        stringBuilder.append("\n");
    }

    private String getRankString(Map<Position, PieceDto> board, Rank rank) {
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

    private String getPiece(Map<Position, PieceDto> board, Position position) {
        PieceDto piece = board.get(position);
        if (board.get(position) == null) {
            return " ";
        }
        return getPieceFigure(piece);
    }

    private String getPieceFigure(PieceDto pieceDto) {
        if (pieceDto.getTeam().equalsIgnoreCase("WHITE")) {
            return WHITE_FIGURE_MAP.get(pieceDto.getPieceType());
        }
        return BLACK_FIGURE_MAP.get(pieceDto.getPieceType());
    }

    private void printStatus(Map<Team, Double> status) {
        status.entrySet()
                .stream()
                .map(entry -> entry.getKey().toString() + " : " + entry.getValue())
                .forEach(System.out::println);
    }
}
