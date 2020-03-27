package chess.controller;

import chess.domain.MoveInfo;
import chess.domain.Status;
import chess.domain.Turn;
import chess.domain.board.Boards;
import chess.service.ChessService;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static List<String> startOrEnd = List.of("start", "end");
    private static List<String> moveOrStatus = List.of("move", "status");
    private static Turn turn = Turn.LOWER;

    public static void start(String input) {
        if (!startOrEnd.contains(input)) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }

        if ("end".equals(input)) {
            exit();
        }
    }

    private static void exit() {
        System.exit(0);
    }

    public static void playTurn(String input, Boards boards) {
        if (!moveOrStatus.contains(input)) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }

        if ("status".equals(input)) {
            status(boards);
        }
        move(input, boards);
    }

    private static void move(String input, Boards boards) {
        ChessService.move(boards, turn, MoveInfo.of(input));
        OutputView.printBoard(boards.getTotal());
        turn = turn.next();

        if (boards.isKingDead()) {
            exit();
        }
    }

    private static void status(Boards boards) {
        OutputView.printScore(Status.result(boards));
        OutputView.printWinner(Status.winner(boards));
        exit();
    }
}
