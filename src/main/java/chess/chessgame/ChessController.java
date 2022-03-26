package chess.chessgame;

import chess.piece.Color;
import chess.view.OutputView;

import java.util.StringTokenizer;

import static chess.view.InputView.inputCommand;
import static chess.view.OutputView.printStartMessage;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        printStartMessage();
        while (!chessGame.isFinished()) {
            playTurn(chessGame);
        }
        OutputView.printScore(chessGame.computeScore(Color.BLACK), chessGame.computeScore(Color.WHITE));
    }

    private void playTurn(ChessGame chessGame) {
        String input = inputCommand();
        StringTokenizer commands = new StringTokenizer(input);
        String command = commands.nextToken();

        if (command.equals("start")) {
            chessGame.start();
            OutputView.printBoard(chessGame.getChessBoard());
            return;
        }
        if (command.equals("end")) {
            chessGame.end();
            OutputView.printBoard(chessGame.getChessBoard());
            return;
        }
        if (command.equals("move")) {
            chessGame.move(new Position(commands.nextToken(), commands.nextToken()));
            OutputView.printBoard(chessGame.getChessBoard());
            return;
        }
        if (command.equals("status")) {
            OutputView.printScore(chessGame.computeScore(Color.BLACK), chessGame.computeScore(Color.WHITE));
            return;
        }
        throw new IllegalArgumentException("올바른 명령어를 입력해주세요");
    }

}
