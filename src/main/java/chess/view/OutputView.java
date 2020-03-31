package chess.view;

import chess.domains.board.PlayingPiece;
import chess.domains.piece.PieceColor;

import java.util.List;

public class OutputView {

    private static final String STARTING_MSG = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" + "> 게임 종료 : end\n" + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n";
    private static final String END = "end";
    public static final int COLUMN_SIZE = 8;
    public static final int ROW_SIZE = 8;
    public static final int PLAYING_PIECES_COUNT = 64;

    public static void printStartMSG() {
        System.out.println(STARTING_MSG);
    }

    public static void printBoard(List<PlayingPiece> showingBoard) {
        StringBuilder sb = new StringBuilder(PLAYING_PIECES_COUNT);

        for (PlayingPiece piece : showingBoard) {
            sb.append(piece.showPieceName());
        }

        for (int i = 0; i < ROW_SIZE; i++) {
            int startIndex = i * COLUMN_SIZE;
            System.out.println(sb.substring(startIndex, startIndex + COLUMN_SIZE));
        }
    }

    public static void printEnd() {
        System.out.println(END);
    }

    public static void printTeamColor(PieceColor teamColor) {
        System.out.println(teamColor.name() + "의 차례입니다.");
    }

    public static void printScore(double calculateScore) {
        System.out.println(calculateScore + "점");
    }
}
