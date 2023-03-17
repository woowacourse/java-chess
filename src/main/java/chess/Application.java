package chess;

import chess.board.ChessBoard;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_COORDINATE_INDEX = 1;
    private static final int DESTINATION_COORDINATE_INDEX = 2;
    
    public static void main(String[] args) {
        OutputView.noticeGameStart();
        run(ChessBoard.create());
    }
    
    private static void run(ChessBoard chessBoard) {
        String command = InputView.repeat(InputView::inputCommand);
        String[] splitedCommand = command.split(" ");
    
        if ("move".equals(splitedCommand[COMMAND_INDEX])){
            String sourceCoordinate = splitedCommand[SOURCE_COORDINATE_INDEX];
            String destinationCoordinate = splitedCommand[DESTINATION_COORDINATE_INDEX];
            
            chessBoard.move(sourceCoordinate,destinationCoordinate);
            OutputView.printChessBoard(chessBoard.chessBoard());
            run(chessBoard);
        }
        
        if ("end".equals(splitedCommand[COMMAND_INDEX])) {
            return;
        }
        
        if ("start".equals(splitedCommand[COMMAND_INDEX])){
            ChessBoard newChessBoard = ChessBoard.create();
            OutputView.printChessBoard(newChessBoard.chessBoard());
            run(newChessBoard);
        }
    }
}
