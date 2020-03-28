package chess.controller;

import chess.domain.MoveInfo;
import chess.domain.Status;
import chess.domain.Turn;
import chess.domain.board.Boards;
import chess.service.ChessService;
import chess.view.OutputView;

import java.util.List;

public class ChessController {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";

    private static List<String> startOrEnd = List.of(START, END);
    private static List<String> moveOrStatus = List.of(MOVE, STATUS);
    private static Turn turn = Turn.LOWER;

    public static void start(String input) {
        if (!startOrEnd.contains(input)) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }

        if (END.equals(input)) {
            exit();
        }
    }

    private static void exit() {
        System.exit(0);
    }

    public static void playTurn(String input, Boards boards) {
        if (!moveOrStatus.contains(List.of(input.split(" ")).get(0))) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }

        if (STATUS.equals(input)) {
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
