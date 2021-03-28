package chess.controller;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
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
        Round round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()), command);
        do {
            playChessGame(round);
        } while (round.isPlaying());
    }

    private void playChessGame(final Round round) {
        try {
            play(round);
        } catch (RuntimeException runtimeException) {
            OutputView.showExceptionMessage(runtimeException);
        }
    }

    private void play(final Round round) {
        if (round.isPlaying()) {
            OutputView.showChessBoard(round.getBoard());
            round.execute(INPUT.inputCommand());
            OutputView.showScore(round.currentPlayerName(), round.calculateScore());
        }
        if (round.isStatus()) {
            OutputView.showResult(PlayerResultDto.from(round.getWhitePlayer()));
            OutputView.showResult(PlayerResultDto.from(round.getBlackPlayer()));
        }
    }
}
