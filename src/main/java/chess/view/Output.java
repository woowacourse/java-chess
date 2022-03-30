package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.notation.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.state.Status;

import java.util.Locale;
import java.util.Map;

public class Output {

    private static final String EXCEPTION_PREFIX = "[ERROR] ";

    private Output() {
    }

    public static void printExceptionMessage(final String exceptionMessage) {
        System.out.println(EXCEPTION_PREFIX + exceptionMessage);
    }

    public static void printChessGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
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
            printPieceName(target);
            return;
        }
        System.out.print(".");
    }

    private static void printPieceName(final Piece piece) {
        if (piece.isSameColor(Color.WHITE)) {
            System.out.print(piece.getNotation().getValue());
            return;
        }
        System.out.print(piece.getNotation().getValue().toUpperCase(Locale.ROOT));
    }

    public static void printStatus(final Status status) {
        System.out.println(status.getResult());
        System.out.println(status.getBlackScore());
        System.out.println(status.getWhiteScore());
    }
}
