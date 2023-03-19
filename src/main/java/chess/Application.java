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
        runChessGame(ChessBoard.create());
    }
    
    private static void runChessGame(ChessBoard chessBoard) {
        String command = InputView.repeatAtExceptionCase(InputView::inputCommand);
        String[] splitedCommand = command.split(" ");
    
        commandMoveCase(chessBoard, splitedCommand);
        if (isCommandEnd(splitedCommand)) {
            return;
        }
        
        commandStartCase(splitedCommand);
    }
    
    private static void commandMoveCase(ChessBoard chessBoard, String[] splitedCommand) {
        if (isCommandMove(splitedCommand)){
            move(chessBoard, splitedCommand);
        }
    }
    
    private static boolean isCommandMove(String[] splitedCommand) {
        return "move".equals(splitedCommand[COMMAND_INDEX]);
    }
    
    private static void move(ChessBoard chessBoard, String[] splitedCommand) {
        String sourceCoordinate = splitedCommand[SOURCE_COORDINATE_INDEX];
        String destinationCoordinate = splitedCommand[DESTINATION_COORDINATE_INDEX];
        
        chessBoard.move(sourceCoordinate,destinationCoordinate);
        OutputView.printChessBoard(chessBoard.chessBoard());
        runChessGame(chessBoard);
    }
    
    private static boolean isCommandEnd(String[] splitedCommand) {
        return "end".equals(splitedCommand[COMMAND_INDEX]);
    }
    
    private static void commandStartCase(String[] splitedCommand) {
        if (isCommandStart(splitedCommand)){
            runNewChessGame();
        }
    }
    
    private static boolean isCommandStart(String[] splitedCommand) {
        return "start".equals(splitedCommand[COMMAND_INDEX]);
    }
    
    private static void runNewChessGame() {
        ChessBoard newChessBoard = ChessBoard.create();
        OutputView.printChessBoard(newChessBoard.chessBoard());
        runChessGame(newChessBoard);
    }
}
