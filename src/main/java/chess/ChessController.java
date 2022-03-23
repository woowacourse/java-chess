package chess;

import chess.view.OutputView;

import java.util.StringTokenizer;

import static chess.view.InputView.inputCommand;
import static chess.view.OutputView.printStartMessage;

public class ChessController {


    public void run(){
        ChessGame chessGame = new ChessGame();
        printStartMessage();
        while(!chessGame.isFinished()){
            playTurn(chessGame);
         }
    }

    private void playTurn(ChessGame chessGame){
        String input = inputCommand();
        StringTokenizer commands = new StringTokenizer(input);
        String command = commands.nextToken();

        if (command.equals("start")) {
            chessGame.start();
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (command.equals("end")) {
            chessGame.end();
            OutputView.printBoard(chessGame.getChessBoard());
        }
        if (command.contains("move")) {
            String source = commands.nextToken();
            String target = commands.nextToken();
            chessGame.move(source,target);
            OutputView.printBoard(chessGame.getChessBoard());
        }
    }
}
