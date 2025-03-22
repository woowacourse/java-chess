package chess.controller;

import chess.Board;
import chess.BoardInitializer;
import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    Color turn = Color.BLACK;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BoardInitializer boardInitializer = new BoardInitializer();
        Board board = boardInitializer.generateBoard();
        retry(() -> processe(board, turn));
    }

    private void processe(Board board, Color turn) {
        outputView.printBoard(board);
        String command = inputView.inputMoveCommand(turn);
        if (command.equals("Q")) {
            System.exit(0);
        }
        String[] positions = command.split(", ");
        Position movePiecePosition = parsePosition(positions[0]);
        Position targetPosition = parsePosition(positions[1]);
        Piece piece = board.findPiece(movePiecePosition, turn);
        piece.move(targetPosition);
        turn = turn.opposite();
        processe(board, turn);
    }

    private Position parsePosition(String position) {
        String[] splited = position.split("");
        return new Position(
                Row.from(Integer.parseInt(splited[0])),
                Column.from(splited[1])
        );
    }

    private void retry(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            retry(runnable);
        }
    }

}
