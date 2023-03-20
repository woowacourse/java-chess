package chess.controller;

import chess.domain.Board;
import chess.domain.order.Order;
import chess.domain.team.Team;
import chess.domain.team.player.BlackPieceGenerator;
import chess.domain.team.player.Player;
import chess.domain.team.player.WhitePieceGenerator;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;


public class ChessController {

    public void run() {
        final Player whitePlayer = Player.of(Team.WHITE, new WhitePieceGenerator());
        final Player blackPlayer = Player.of(Team.BLACK, new BlackPieceGenerator());
        final Board board = Board.create(new HashMap<>());

        startGame(board);
        playGame(board, whitePlayer, blackPlayer);
    }

    private void startGame(final Board board) {
        try {
            Order.ofStart(InputView.askStart());
            OutputView.printBoard(board);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            startGame(board);
        }
    }

    private void playGame(final Board board, final Player currentPlayer, final Player opponentPlayer) {
        try {
            Order order = Order.ofMoveOrEnd(InputView.askNext());

            if (order.isEnd()) {
                return;
            }
            if (order.isMove()) {
                board.move(order.source(), order.target(), currentPlayer);
                OutputView.printBoard(board);
                playGame(board, opponentPlayer, currentPlayer);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            playGame(board, currentPlayer, opponentPlayer);
        }
    }
}
