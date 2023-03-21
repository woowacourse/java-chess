package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.strategy.MoveCommand;
import chess.controller.command.strategy.StrategyCommand;
import chess.domain.Board;
import chess.domain.team.Team;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;

import static chess.domain.team.Team.BLACK;


public class ChessController {

    public void run() {
        Team turn = BLACK;
        final Board board = Board.create(new HashMap<>());

        OutputView.printStart();
        ChessState state = ChessState.INIT;
        while (state != ChessState.END) {
            state = play(state, board, turn);
            turn = turn.reverse();
        }
    }

    private ChessState play(final ChessState state, final Board board, final Team team) {
        StrategyCommand command = Command.bind(InputView.read());
        return command.execute(state, board, team);
    }
}
