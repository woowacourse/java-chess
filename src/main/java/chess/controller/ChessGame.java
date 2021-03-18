package chess.controller;

import chess.domain.Board;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.ChessException;
import chess.exception.InvalidCommandException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void start() {
        OutputView.startGame();
        String command = InputView.command();

        if (command.equals("start")) {
            startGame();
        }
    }

    private void startGame() {
        final Board board = Board.getGamingBoard();
        Side side = Side.BLACK;

        do {
            side = side.changeTurn();
            OutputView.printBoard(board);
            System.out.println(side.toString());
        } while (playerTurn(board, side));
    }

    private boolean playerTurn(Board board, Side side) {
        try {
            return executeByCommand(board, side, InputView.command());
        } catch (ChessException e) {
            System.out.println(e.getMessage());
            return playerTurn(board, side);
        }
    }

    private boolean executeByCommand(Board board, Side side, String command) {
        if (command.startsWith("move")) {
            String[] sourceTarget = command.split(" ");
            board.move(Position.of(sourceTarget[1]), Position.of(sourceTarget[2]), side);
            return true;
        }
        if (command.equals("end")) {
            return false;
        }

        throw new InvalidCommandException();
    }
}
