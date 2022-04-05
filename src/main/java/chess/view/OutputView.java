package chess.view;

import static chess.domain.piece.PieceColor.BLACK;

import java.util.Map;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.game.Status;
import chess.domain.piece.Piece;

public class OutputView {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String ARROW = "> ";
    private static final String TEAM_STATUS = "%s 점수 : %.1f졈" + SEPARATOR;
    private static final String WINNER_TEAM = "게임 결과 : %s 승" + SEPARATOR;
    private static final String DRAW = "무승부";
    private static final String INIT_MESSAGE =
            ARROW + "체스 게임을 시작합니다." + SEPARATOR
                    + ARROW + "게임 시작 : start" + SEPARATOR
                    + ARROW + "게임 상태 : status" + SEPARATOR
                    + ARROW + "게임 종료 : end" + SEPARATOR
                    + ARROW + "게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public static void printInitMessage() {
        System.out.println(INIT_MESSAGE);
    }

    public static void printChessBoard(ChessBoard chessBoard) {
        final Map<Position, Piece> board = chessBoard.getBoard();
        for (int column = 7; column >= 0; column--) {
            printPieces(board, column);
        }
        System.out.println();
    }

    private static void printPieces(Map<Position, Piece> board, int column) {
        for (int row = 0; row <=7; row++) {
            final Piece piece = board.get(Position.valueOf(getKey(row, column)));
            System.out.print(getPiece(piece));
        }
        System.out.println();
    }

    private static String getKey(int row, int column) {
        return (char) ('a' + row) + String.valueOf(1 + column);
    }

    private static String getPiece(Piece piece) {
        if (piece.getPieceColor() == BLACK) {
            return piece.getPieceType().getName().toUpperCase();
        }
        return piece.getPieceType().getName();
    }

    public static void printStatus(Status status) {
        System.out.printf(TEAM_STATUS, "BLACK", status.getBlackScore().getScore());
        System.out.printf(TEAM_STATUS, "WHITE", status.getWhiteScore().getScore());
    }

    public static void printGameResult(Status status) {
        System.out.printf(WINNER_TEAM, status.getWinnerColor());
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage + SEPARATOR);
    }
}
