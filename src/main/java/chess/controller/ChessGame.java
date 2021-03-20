package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.Start;
import chess.domain.Board;
import chess.domain.Side;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void start() {
        OutputView.startGame();
        Command command = command();

        if (command.isStart()) {
            startGame();
        }
    }

    private void startGame() {
        final Board board = Board.getGamingBoard();
        State state = new Ready(board);

        // TODO
        state = state.start();
        Side side = Side.BLACK;

        Command command = new Start();

        // TODO try Catch
        while (!command.isEnd()) {
            if (state.isGameSet()) {
                state = state.gameSet();
                //Side가 이겼습니다! 출력
            }
            OutputView.print(board, side);

            command = command();
            if (command.isMove()) {
                state = state.move(command.source(), command.target(), side);
                side = side.changeTurn();
            }
            if (command.isStatus()) {
                state = state.status();
                OutputView.print(state.score());
                // TODO 누가 승리? -> 킹이 잡혔을 때 추가
            }
        }
    }

    private Command command() {
        return Command.from(InputView.command());
    }
}
