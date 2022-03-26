package chess;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;
import chess.domain.piece.NormalPiecesGenerator;
import chess.domain.piece.PiecesGenerator;
import chess.view.InputView;
import chess.view.ResultView;

public class ConsoleApplication {

    static ChessBoard chessBoard;

    public static void main(String[] args) {

        ResultView.printStartMessage();
        playGame();
    }

    private static void playGame() {
        GameCommand gameCommand;
        do {
            gameCommand = requestCommand();
        } while (!gameCommand.isEnd());
    }

    private static GameCommand requestCommand() {
        try {
            GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            playGameByCommand(gameCommand);
            return gameCommand;
        } catch (RuntimeException exception) {
            ResultView.printReplay(exception.getMessage());
            return requestCommand();
        }
    }

    private static void playGameByCommand(GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            PiecesGenerator piecesGenerator = new NormalPiecesGenerator();
            chessBoard = new ChessBoard(piecesGenerator);
            ResultView.printChessBoard(chessBoard.getPieces());
        }
        if (gameCommand.isMove() && chessBoard == null) {
            throw new IllegalStateException("체스판이 초기화되지 않았습니다.");
        }
        if (gameCommand.isEnd()) {
            return;
        }
    }
}
