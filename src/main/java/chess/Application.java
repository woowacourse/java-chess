package chess;

import chess.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.noticeGameStart();
        run(ChessBoard.create());
    }
    
    private static void run(ChessBoard chessBoard) {
        String command = InputView.repeat(InputView::inputCommand);
        String[] splitedCommand = command.split(" ");
    
        if ("move".equals(splitedCommand[0])){
            String sourceCoordinate = splitedCommand[1];
            String destinationCoordinate = splitedCommand[2];
            chessBoard.move(sourceCoordinate,destinationCoordinate);
            OutputView.printChessBoard(chessBoard.chessBoard());
            run(chessBoard);
        }
        
        if ("end".equals(splitedCommand[0])) {
            return;
        }
        
        if ("start".equals(splitedCommand[0])){
            ChessBoard newChessBoard = ChessBoard.create();
            OutputView.printChessBoard(newChessBoard.chessBoard());
            run(newChessBoard);
        }
    }
}
