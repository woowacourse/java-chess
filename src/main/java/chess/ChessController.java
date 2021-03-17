package chess;

import chess.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        OutputView.printStart();
        String command = InputView.askCommand();
        
        if (command.equals("start")) {
            Board board = new Board();
            board.init();
            OutputView.printBoard(board);
            
            while (true) {
                command = InputView.askCommand();
                
                if (command.equals("end")) {
                    break;
                }
                String[] commands = command.split(" ");
                if ("move".equals(commands[0])) {
                    board.movePiece(commands[1], commands[2]);
                }
                OutputView.printBoard(board);
                
            }
        }
    }
}
