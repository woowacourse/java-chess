package chess.controller;

import chess.domain.*;
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

        Player whitePlayer = new Player(Color.WHITE);
        Player blackPlayer = new Player(Color.BLACK);

        boolean whitePieceTurn = true;

        while (true) {
            String[] gameCommand = InputView.readGameCommand();
            GameCommand command = GameCommand.of(gameCommand);

            if (command.equals(GameCommand.MOVE) && !whitePieceTurn) {
                moveBlackPiece(chessGame, blackPlayer, gameCommand);
                whitePieceTurn = true;
                continue;
            }
            if (command.equals(GameCommand.MOVE) && whitePieceTurn) {
                moveWhitePiece(chessGame, whitePlayer, gameCommand);
                whitePieceTurn = false;
                continue;
            }
            if (command.equals(GameCommand.STATUS)) {
                showStatus(chessGame, whitePlayer, blackPlayer);
            }
            if (command.equals(GameCommand.END)) {
                break;
            }
            if (chessGame.canEndGame(whitePlayer, blackPlayer)) {
                OutputView.printGameEnd();
                break;
            }
        }
    }

    private void moveWhitePiece(ChessGame chessGame, Player player, String[] gameCommand) {
        Position sourcePosition = GameCommand.getSourcePosition(gameCommand);
        Position targetPosition = GameCommand.getTargetPosition(gameCommand);
        chessGame.moveWhitePiece(player, sourcePosition, targetPosition);
        OutputView.printChessBoard(chessGame.getChessBoard());
    }

    private void moveBlackPiece(ChessGame chessGame, Player player, String[] gameCommand) {
        Position sourcePosition = GameCommand.getSourcePosition(gameCommand);
        Position targetPosition = GameCommand.getTargetPosition(gameCommand);
        chessGame.moveBlackPiece(player, sourcePosition, targetPosition);
        OutputView.printChessBoard(chessGame.getChessBoard());
    }

    private void showStatus(ChessGame chessGame, Player whitePlayer, Player blackPlayer) {
        Result whiteResult = chessGame.calculateWhiteResult(whitePlayer);
        OutputView.printWhiteResult(whiteResult);
        Result blackResult = chessGame.calculateBlackResult(blackPlayer);
        OutputView.printBlackResult(blackResult);
    }
}
