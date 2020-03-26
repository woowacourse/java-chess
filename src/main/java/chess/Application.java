package chess;


import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Command command = InputView.inputStart();
        if (command.isNotStart()) {
            System.exit(0);
        }

        Board board = ChessBoard.initiaize();
        OutputView.printBoard(board);

        String[] moves = InputView.inputMove();
        Position from = Position.of(moves[1]);
        Position to = Position.of(moves[2]);
        board = board.movePiece(from, to);
        OutputView.printBoard(board);
    }
}
