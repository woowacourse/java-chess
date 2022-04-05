package chess.view;

import chess.domain.Score;
import chess.domain.WinResult;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.StatusDto;

import java.util.Map;

public class OutputView {

    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
    }

    public static void printPlayingCommandMessage() {
        System.out.println("> 게임 이동 : move source 위치 target 위치 (예. move b2 b3)");
        System.out.println("> 게임 현황 : status");
        System.out.println("> 게임 종료 : end");
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printStatus(StatusDto status) {
        Map<PieceColor, Score> scores = status.getScoresByColor();
        WinResult winResult = status.getWinResult();

        for (PieceColor color : scores.keySet()) {
            Score score = scores.get(color);
            System.out.println(color.name() + ": " + score.getValue() + "점");
        }
        if (winResult == WinResult.DRAW) {
            System.out.println("동점입니다.");
            return;
        }
        System.out.println(winResult + " 진영이 이기고 있습니다.");
        System.out.println();
    }

    public static void printBoard(Map<Position, Piece> pieces) {
        for (Row row : Row.values()) {
            printBoardByRow(pieces, row);
        }
    }

    private static void printBoardByRow(Map<Position, Piece> pieces, Row row) {
        for (Column column : Column.values()) {
            Position position = Position.of(column, row);
            System.out.print(getPieceSignature(pieces, position));
        }
        System.out.println();
    }

    private static String getPieceSignature(Map<Position, Piece> pieces, Position position) {
        if (pieces.containsKey(position)) {
            return pieces.get(position).getSignature();
        }
        return ".";
    }
}
