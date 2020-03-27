package chess.view;

import chess.domains.board.PlayingPiece;
import chess.domains.piece.PieceColor;

import java.util.List;

public class OutputView {

    private static final String STARTING_MSG = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" + "> 게임 종료 : end\n" + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n";
    private static final String END = "end";

    public static void printStartMSG() {
        System.out.println(STARTING_MSG);
    }

    public static void printBoard(List<PlayingPiece> showingBoard) {
        StringBuilder sb = new StringBuilder();
        for (PlayingPiece piece : showingBoard) {
            sb.append(piece.showPieceName());
            if (sb.length() == 8) {
                System.out.println(sb);
                sb = new StringBuilder();
            }
        }
    }

    public static void printEnd() {
        System.out.println(END);
    }

    public static void printTeamColor(PieceColor teamColor) {
        System.out.println(teamColor.name() + "의 차례입니다.");
    }
}
