package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();

        OutputView.printCommandGuide();

        // TODO: depth 줄이기
        // TODO: while 대신 재귀 고려하기
        // TODO: 메소드 길이를 줄이기 위해 메소드 추출하기
        while (true) {
            try {
                List<String> commands = InputView.inputCommand();
                String command = commands.get(0);

                if (command.equals("start")) {
                    chessGame.startGame();
                    OutputView.printBoard(chessGame.getBoard());
                }

                if (command.equals("move")) {
                    Position fromPosition = Position.from(commands.get(1));
                    Position toPosition = Position.from(commands.get(2));

                    chessGame.movePiece(fromPosition, toPosition);
                    OutputView.printBoard(chessGame.getBoard());
                }

                if (command.equals("status")) {
                    chessGame.showStatus();
                    OutputView.printScore(chessGame.getBoard());
                }

                if (command.equals("end")) {
                    break;
                }
            } catch (Exception e) {
                OutputView.printException(e);
            }
        }
    }
}
