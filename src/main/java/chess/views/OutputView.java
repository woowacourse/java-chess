package chess.views;

import chess.domain.chesspieces.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import chess.domain.status.Result;
import chess.domain.status.Status;

import java.util.Map;
import java.util.Optional;

public class OutputView {
    private final static String NEW_LINE = System.lineSeparator();
    private final static String EMPTY = ".";

    public static void printInitialGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source 위치 target위치 - 예. move b2 b3");
        System.out.println("> 게임 상황 : status");
    }

    public static void printChessBoard(Map<Position, Piece> chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NEW_LINE);

        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                String display = Optional.ofNullable(chessBoard.get(Positions.of(row, column)))
                        .map(Piece::getDisplay)
                        .orElse(EMPTY);
<<<<<<< HEAD
                stringBuilder.append(display);
=======
                    stringBuilder.append(display);
>>>>>>> da51a79... refactor : 전반적인 컨벤션 수정 및 OutputView 체스판 출력 리팩토링
            }
            stringBuilder.append(NEW_LINE);
        }
        System.out.println(stringBuilder.toString());
    }

    public static void printStatus(Result result) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Status status : result.getStatuses()) {
            stringBuilder.append(status.getPlayer());
            stringBuilder.append(" : ");
            stringBuilder.append(status.getScore());
            stringBuilder.append(NEW_LINE);
        }

        if (result.isDraw()) {
            stringBuilder.append("무승부");
        }

        if (!result.isDraw()) {
            stringBuilder.append("승자 : ");
            stringBuilder.append(result.getWinners());
        }

        System.out.println(stringBuilder.toString());
    }

    public static void printGameOver() {
        System.out.println("King이 잡혀서 게임을 종료합니다.");
    }
}
