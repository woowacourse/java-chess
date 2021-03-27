package chess.controller;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.PieceFactory;
import chess.domain.player.ChessGame;
import chess.domain.state.StateFactory;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.PlayerResultDto;

import java.util.Scanner;

public class ChessController {
    private static final InputView INPUT = new InputView(new Scanner(System.in));

    public void run() {
        Command command = CommandFactory.initialCommand(INPUT.inputStart());
        if (command.isStart()) {
            startChessGame(command);
        }
    }

    private void startChessGame(final Command command) {
        ChessGame chessGame = new ChessGame(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()), command);

        while (true) try {
            inputCommand(chessGame);
            break;
        } catch (RuntimeException runtimeException) {
            System.out.println("[ERROR]: " + runtimeException.getMessage());
        }
    }

    private void inputCommand(final ChessGame chessGame) {
        while (!chessGame.isEnd()) {
            OutputView.showChessBoard(chessGame.getBoard());
            chessGame.move(INPUT.inputCommand());
            OutputView.showScore(chessGame.currentPlayerName(), chessGame.calculateScore());
        }
        if (chessGame.isStatus()) {
            OutputView.showResult(PlayerResultDto.toDto(chessGame.getWhitePlayer()));
            OutputView.showResult(PlayerResultDto.toDto(chessGame.getBlackPlayer()));
        }
    }
}
