package chess.controller;

import java.util.List;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.service.ChessService;
import chess.utils.MoveInfo;
import chess.view.OutputView;

public class ChessController {
    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final String DELIMITER = " ";
    private static List<String> startOrEnd = List.of(START, END);
    private static List<String> moveOrStatus = List.of(MOVE, STATUS);
    private static Team team = Team.WHITE;

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

    public static void playTurn(String input, Board board) {
        if (!moveOrStatus.contains(List.of(input.split(DELIMITER)).get(0))) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }

        if (STATUS.equals(input)) {
            status(board);
        }
        move(input, board);
    }

    private static void move(String input, Board board) {
        ChessService.of(board, team).move(MoveInfo.of(input));

        OutputView.printBoard(board.getBoard());
        team = team.next();

        if (board.isEnd()) {
            exit();
        }
    }

    private static void status(Board board) {
        OutputView.printScore(Status.result(board));
        OutputView.printWinner(Status.winner(board));
        exit();
    }
}
