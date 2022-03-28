package console;

import chess.BoardInitializer;
import chess.ChessBoard;
import chess.command.Command;
import chess.command.StartCommand;
import chess.piece.Color;
import console.view.InputView;
import console.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitChessGameMessage();
        Command command = inputCommand();
        ChessBoard chessBoard = null;

        while (true) {
            try {
                if (command instanceof StartCommand) {
                    if(chessBoard != null) {
                        throw new IllegalStateException("체스 게임이 이미 진행중입니다.");
                    }

                    chessBoard = new ChessBoard(BoardInitializer.init(), Color.WHITE);
                    OutputView.printChessBoard(chessBoard.getPieces());
                }

                if (command.isMove()) {
                    if (chessBoard == null) {
                        throw new IllegalStateException("체스 게임이 시작되지 않았습니다.");
                    }

                    chessBoard.move(command.from(), command.to());
                    OutputView.printChessBoard(chessBoard.getPieces());
                }

                if (command.isStatus()) {
                    if (chessBoard == null) {
                        throw new IllegalStateException("체스 게임이 시작되지 않았습니다.");
                    }

                    OutputView.printScores(chessBoard.getScore(Color.WHITE),
                        chessBoard.getScore(Color.BLACK));
                }

                if (chessBoard != null && chessBoard.isFinished()) {
                    OutputView.printWinner(chessBoard.getWinner());
                    return;
                }

                if (command.isEnd()) {
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            command = inputCommand();
        }
    }

    private static Command inputCommand() {
        try {
            return Command.from(InputView.inputCommand());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }
}
