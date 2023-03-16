package chess;

import chess.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.ProcessCommand;

public class Application {
    public static void main(String[] args) {
        OutputView.noticeGameStart();
        run();
    }
    
    private static void run() {
        ProcessCommand processCommand = InputView.repeat(InputView::inputProcessCommand);
        if (processCommand.isEnd()) {
            return;
        }
    
        ChessBoard chessBoard = ChessBoard.create();
        OutputView.printChessBoard(chessBoard.chessBoard());
        chessBoard.move("b1", "c3");
        OutputView.printChessBoard(chessBoard.chessBoard());
        chessBoard.move("a1", "a3");
        OutputView.printChessBoard(chessBoard.chessBoard());
        
        chessBoard.move("a2", "a4");
        OutputView.printChessBoard(chessBoard.chessBoard());
        chessBoard.move("a1", "a3");
        OutputView.printChessBoard(chessBoard.chessBoard());
        chessBoard.move("a3", "b3");
        OutputView.printChessBoard(chessBoard.chessBoard());
        chessBoard.move("b3", "c3");
        OutputView.printChessBoard(chessBoard.chessBoard());
        chessBoard.move("b3", "b7");
        OutputView.printChessBoard(chessBoard.chessBoard());
        
        run();
    }
}
