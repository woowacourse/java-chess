package chess.view;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 현황 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final ChessGame chessGame) {
        ChessBoard chessBoard = chessGame.getChessBoard();
        Map<Position, Piece> pieces = chessBoard.getPieces();
        for (final Row row : Row.getRowsReverseOrder()) {
            printBoardInRow(pieces, row);
            System.out.println();
        }
        System.out.println();
    }

    private static void printBoardInRow(final Map<Position, Piece> pieces, Row row) {
        for (Column column : Column.values()) {
            final Position position = Position.of(column, row);
            final Piece piece = pieces.get(position);
            System.out.print(piece.getSymbol());
        }
    }

    public static void printReplay(final Exception exception) {
        System.out.println(exception.getMessage());
        System.out.println("다시 입력 바랍니다." + System.lineSeparator());
    }

    public static void printStatus(final Color color, final double score) {
        System.out.println(color.name() + " : " + score);
    }

    public static void printWinner(final Color color) {
        System.out.println(color.name() + "팀이 이겼습니다.");
    }
}
