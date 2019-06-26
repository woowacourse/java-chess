package view;

import chess.domain.chess.ChessGame;
import chess.domain.chess.unit.Unit;
import chess.domain.geometric.Position;

import java.util.Optional;

public class OutputView {
    public static void printInitialStatement() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printCheckBoard(ChessGame chessGame) {
        for (int i = Position.MIN_POSITION; i < Position.MAX_POSITION; i++) {
            printCheckBoardRow(chessGame, i);
            System.out.println();
        }
        System.out.println();
    }

    private static void printCheckBoardRow(ChessGame chessGame, int i) {
        for (int j = Position.MIN_POSITION; j < Position.MAX_POSITION; j++) {
            Optional<Unit> optional = chessGame.getUnit(Position.create(j, Position.MAX_POSITION - 1 - i));
            System.out.print(printUnit(optional));
        }
    }

    private static String printUnit(Optional<Unit> optional) {
        if (optional.isPresent()) {
            return optional.get().toString();
        }
        return ".";
    }
}
