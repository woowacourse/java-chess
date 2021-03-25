package chess.view;

import chess.domain.Board;
import chess.domain.PrintBoardDto;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;

public class OutputView {

    public static final String EMPTY = ".";

    public static void printCommandInfo() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printTeamWin(Team team) {
        System.out.printf("%s팀이 승리하였습니다.\n", team.getName());
    }

    public static void printBoard(PrintBoardDto dto) {
        for (Rank rank : Rank.values()) {
            printPiece(dto.getBoard(), rank);
            System.out.println();
        }
        System.out.printf("%s팀의 턴입니다. \n", dto.getTurn().getName());
    }

    private static void printPiece(Board board, Rank rank) {
        for (File file : File.values()) {
            Position position = Position.of(makePositionFormat(rank, file));
            if (!board.hasPieceAt(position)) {
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

    public static void printStatus(Map<Team, Double> result) {
        for (Map.Entry<Team, Double> entry : result.entrySet()) {
            System.out.printf("%s팀의 점수는 %.2f점 입니다.\n", entry.getKey().getName(), entry.getValue());
        }
        System.out.println();
    }
}
