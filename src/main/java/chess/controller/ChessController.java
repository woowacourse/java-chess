package chess.controller;

import chess.domain.Board;
import chess.domain.command.Command;
import chess.domain.command.strategy.MoveCommand;
import chess.domain.command.strategy.StrategyCommand;
import chess.domain.team.Team;
import chess.domain.team.player.BlackPieceGenerator;
import chess.domain.team.player.Player;
import chess.domain.team.player.WhitePieceGenerator;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;

import static chess.domain.team.Team.*;


public class ChessController {

    public void run() {
        final Player whitePlayer = Player.of(WHITE, new WhitePieceGenerator());
        final Player blackPlayer = Player.of(BLACK, new BlackPieceGenerator());
        final Board board = Board.create(new HashMap<>());

        startGame(board);
        playGame(board, whitePlayer, blackPlayer);
    }

    private void startGame(final Board board) {
        try {
            OutputView.printStart();
            Command.bind(InputView.read());
            OutputView.printBoard(board);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            startGame(board);
        }
    }

    private void playGame(final Board board, final Player currentPlayer, final Player opponentPlayer) {
        try {
            StrategyCommand command = Command.bind(InputView.read());
            command.execute(board, currentPlayer);
            playRepeatIfMove(command, board, currentPlayer, opponentPlayer);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            playGame(board, currentPlayer, opponentPlayer);
        }
    }

    private void playRepeatIfMove(final StrategyCommand command,
                                  final Board board,
                                  final Player currentPlayer, final Player opponentPlayer) {
        if (command instanceof MoveCommand) {
            OutputView.printBoard(board);
            playGame(board, opponentPlayer, currentPlayer);
        }
    }
}
