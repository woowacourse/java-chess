package chess.view;

import chess.domain.board.*;
import chess.domain.dto.BoardDto;
import chess.domain.dto.PointDto;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {

    public static final String EMPTY = ".";

    public static void printCommandInfo() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printWinner(Team team) {
        System.out.printf("%s팀이 승리하였습니다.\n", team.team());
    }

    public static void printBoard(BoardDto boardDto) {
        System.out.println(boardDto.team() + "팀의 차례입니다.");
        for (Rank rank : Rank.values()) {
            printPiece(boardDto.board(), rank);
            System.out.println();
        }
        System.out.println();
    }

    private static void printPiece(Board board, Rank rank) {
        for (File file : File.values()) {
            Position position = Position.of(makePositionFormat(rank, file));
            if (!board.containsPosition(position)) {
                System.out.print(EMPTY);
                continue;
            }

            Piece piece = board.pieceAt(position);
            System.out.print(piece.getName());
        }
    }

    private static String makePositionFormat(Rank rank, File file) {
        return file.getFile() + rank.getRank();
    }

    public static void printStatus(PointDto pointDto) {
        for (Map.Entry<Team, Double> entry : pointDto.result()) {
            System.out.printf("%s팀의 점수는 %.2f점 입니다.\n", entry.getKey().team(), entry.getValue());
        }
        System.out.println();
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
        System.out.println();
    }
}
