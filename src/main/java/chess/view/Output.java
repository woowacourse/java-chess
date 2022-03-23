package chess.view;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;

public class Output {

    private Output() {
    }

    public static void printChessGameStart() {
        System.out.println("체스 게임을 시작합니다.\n게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        for (final Rank rank : Rank.reversed()) {
            printBoard(rank, board);
        }
    }

    private static void printBoard(final Rank rank, final Map<Position, Piece> board) {
        for (final File file : File.sorted()) {
            printBoard(file, rank, board);
        }
        System.out.println();
    }

    private static void printBoard(final File file, final Rank rank, final Map<Position, Piece> board) {
        final Piece target = board.get(Position.of(file, rank));
        if (target != null) {
            System.out.print(target.getName());
            return;
        }
        System.out.print(".");
    }
}
