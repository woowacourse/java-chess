package chess.contoller;

import chess.board.ChessBoard;
import chess.board.Position;
import chess.piece.Team;
import chess.util.ErrorHandler;
import chess.view.InputView;
import chess.view.MoveCommand;
import chess.view.OutputView;

public class ChessGameManager {

    private final Turn turn = new Turn(Team.BLACK);

    public void run() {
        ChessBoard board = new ChessBoard();
        Turn turn = new Turn(Team.BLACK);
        while (!board.isEnd()) {
            OutputView.printBoard(board);
            ErrorHandler.retryUntilSuccess(() -> {
                System.out.printf("현재 %s팀의 턴입니다.", turn.team.getTitle());

                MoveCommand moveCommand = ErrorHandler.retryUntilSuccessWithReturn(InputView::inputMoveCommand);
                Position source = moveCommand.source();
                Position destination = moveCommand.destination();

                if (!board.existsPiece(source)) {
                    throw new IllegalArgumentException(source + ": 위치에 기물이 존재하지 않습니다.");
                }

                if (!board.hasProperTeam(source, turn.team)) {
                    throw new IllegalArgumentException("현재 " + turn.team.getTitle() + "팀의 턴입니다.");
                }

                if (!board.canAttack(source, destination) && !board.canMove(source, destination)) {
                    throw new IllegalArgumentException("해당 경로로 이동할 수 없습니다.");
                }

                if (board.canAttack(source, destination)) {
                    board.attack(source, destination);
                } else if (board.canMove(source, destination)) {
                    board.move(source, destination);
                }
            });
            turn.inverse();
        }
    }

    static class Turn {
        private Team team;

        public Turn(Team team) {
            this.team = team;
        }

        public void inverse() {
            team = team.inverse();
        }
    }
}
