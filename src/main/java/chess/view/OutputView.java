package chess.view;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;
import chess.manager.Status;

import java.util.List;

public class OutputView {

    public static void printBoard(final Board board) {
        for (final Horizontal h : Horizontal.values()) {
            for (final Vertical v : Vertical.values()) {
                final Piece piece = board.getBoard().get(new Position(v, h));
                System.out.print(piece.decideUpperOrLower());
            }
            System.out.println();
        }
    }

    public static void printAbleToMove(final Board board, final List<Position> ableToMove) {
        for (final Horizontal h : Horizontal.values()) {
            for (final Vertical v : Vertical.values()) {
                final Position p = new Position(v, h);

                if (ableToMove.contains(p)) {
                    System.out.print("*");
                    continue;
                }

                final Piece piece = board.getBoard().get(p);
                System.out.print(piece.decideUpperOrLower());
            }
            System.out.println();
        }
    }


    public static void printStatus(final Status status) {
        System.out.println("White score : " + status.whiteScore());
        System.out.println("Black score : " + status.blackScore());
    }

    public static void printGameResult(final Status status) {
        printStatus(status);

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
}
