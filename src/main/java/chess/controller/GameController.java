package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {

    public void run() {
        try {
            GameCommand command = GameCommand.of(InputView.readStartOrEndCommand());
            if (command.equals(GameCommand.START)) {
                startGame();
                return;
            }
            if (command.equals(GameCommand.END)) {
                return;
            }
            throw new IllegalArgumentException("[ERROR] 'Start' 또는 'End'만 입력 가능합니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private void startGame() {
        try {
            ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
            ChessGame chessGame = new ChessGame(chessBoard);
            OutputView.printChessBoard(chessGame.getChessBoard());

            playGame(chessGame);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            startGame();
        }
    }

    private void playGame(ChessGame chessGame) {

        while (true) {
            try {
            if (chessGame.canEndGame()) {
                OutputView.printGameEnd();
                break;
            }
                String[] gameCommand = InputView.readGameCommand();
                GameCommand command = GameCommand.of(gameCommand);

                if (command.equals(GameCommand.MOVE)) {
                    movePiece(chessGame, gameCommand);
                    continue;
                }
            if (command.equals(GameCommand.STATUS)) {
                showStatus(chessGame);
            }
                if (command.equals(GameCommand.END)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void movePiece(ChessGame chessGame, String[] gameCommand) {
        Position sourcePosition = GameCommand.getSourcePosition(gameCommand);
        Position targetPosition = GameCommand.getTargetPosition(gameCommand);
        chessGame.movePiece(sourcePosition, targetPosition);
        OutputView.printChessBoard(chessGame.getChessBoard());
    }

    private void showStatus(ChessGame chessGame) {
        OutputView.printResults(chessGame.calculateResults());
    }
}
