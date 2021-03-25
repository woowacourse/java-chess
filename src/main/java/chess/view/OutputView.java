package chess.view;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;
import chess.manager.Status;

import java.util.Collections;
import java.util.List;

public class OutputView {
    private static final String STAR_SHAPE = "*";

    public static void printBoard(final Board board) {
        printPieceOrStar(board);
    }

    public static void printReachableBoard(final Board board, final List<Position> ableToMove) {
        printPieceOrStar(board, ableToMove);
    }

    public static void printPieceOrStar(final Board board) {
        final List<Position> starPositions = Collections.emptyList();
        printPieceOrStar(board, starPositions);
    }

    public static void printPieceOrStar(final Board board, final List<Position> starPositions) {
        for (final Horizontal h : Horizontal.reversedValues()) {
            for (final Vertical v : Vertical.values()) {
                printSymbol(board.of(new Position(v, h)), starPositions.contains(new Position(v, h)));
            }
            System.out.println();
        }
    }

    private static void printSymbol(final Piece piece, final boolean isStar) {
        System.out.print(decideSymbol(piece, isStar));
    }

    private static String decideSymbol(final Piece piece, final boolean isStar) {
        if (isStar) {
            return STAR_SHAPE;
        }
        return PieceSymbol.symbol(piece);
    }

    public static void printScores(final Status status) {
        System.out.println("White score : " + status.whiteScore());
        System.out.println("Black score : " + status.blackScore());
    }

    public static void printGameResult(final Status status) {
        printScores(status);

        System.out.println("=== 게임 결과 ===");

        if (status.whiteScore() > status.blackScore()) {
            System.out.println("화이트의 승리입니다.");
        }

        if (status.whiteScore() < status.blackScore()) {
            System.out.println("블랙의 승리입니다.");
        }

        if (status.whiteScore() == status.blackScore()) {
            System.out.println("무승부입니다.");
        }
    }

    public static void printMenu() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작은 start, 종료는 end 명령을 입력하세요.");
        System.out.println("> 게임 이동 : move source target");
    }
}
