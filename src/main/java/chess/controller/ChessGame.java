package chess.controller;

import chess.domain.Board;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.ChessException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void start() {
        OutputView.startGame();
        String command = InputView.command();

        if (command.equals("start")) {
            Board board = Board.getGamingBoard();

            Side side = Side.WHITE;
            while (true) {
                // 출력
                OutputView.printBoard(board);
                System.out.println(side.toString());
                command = InputView.command();

                try {
                    turn(board, side, command);
                    side = side.changeTurn();
                } catch (ChessException e) {
                    System.out.println(e.getMessage());
                }

                // 종료
                if (command.equals("end")) {
                    return;
                }
            }
        }
    }

    private void turn(Board board, Side side, String command) {
        // 이동
        if (command.startsWith("move")) {
            String[] s = command.split(" ");
            board.move(Position.of(s[1]), Position.of(s[2]), side);
        }
    }
}
