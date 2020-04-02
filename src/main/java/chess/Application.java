package chess;

import chess.domain.board.BoardSerializer;
import chess.ui.Command;
import chess.domain.UserInterface;
import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.ui.Console;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        UserInterface userInterface = new Console();
        //todo: refac start variable
        Command start = userInterface.inputStart();
        Board board = InitializedBoard.initiaize(userInterface);
        OutputView.printBoard(BoardSerializer.serialize(board));
        System.out.println();
        board = board.movePiece();
        OutputView.printBoard(BoardSerializer.serialize(board));
        System.out.println();
        OutputView.printEnd();
    }
}
