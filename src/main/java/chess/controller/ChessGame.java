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
        Side side = Side.BLACK; // TODO 첫 턴이 블랙? 가독성 좋지 않음

        do {
            // TODO 가독성 안좋은 do-while 개선
            side = side.changeTurn();
            OutputView.printBoard(board);
            System.out.println(side.toString()); // TODO 출력 로직 OutputView 이동
        } while (playerTurn(board, side));
        // TODO 점수계산?
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
        // TODO command -> 객체로 변경
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
