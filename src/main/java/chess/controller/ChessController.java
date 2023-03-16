package chess.controller;

import chess.model.board.Board;
import chess.model.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final Board board = retry(this::createChessBoard);
        retry(this::move, board);
    }

    private Board createChessBoard() {
        final GameCommand gameCommand = inputView.printGameStartMessage();

        Board board = null;
        if (GameCommand.START.equals(gameCommand)) {
            board = Board.create();
            final BoardResponse boardResponse = new BoardResponse(board.getSquares());

            outputView.printChessBoard(boardResponse);
        }
        return board;
    }

    private <T> T retry(final Supplier<T> supplier){
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void move(final Board board) {
        GameCommand gameCommand = GameCommand.MOVE;

        Turn turn = new Turn();

        while (!GameCommand.END.equals(gameCommand)) {
            gameCommand = processMove(board, turn);
        }
    }

    private <T> void retry(final Consumer<T> consumer, T input) {
        while (true) {
            try {
                consumer.accept(input);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private GameCommand processMove(final Board board, final Turn turn) {
        final MoveRequest moveRequest = inputView.readPlayCommand();

        if (GameCommand.MOVE.equals(moveRequest.getGameCommand())) {
            doMove(board, turn, moveRequest);
        }

        validatePlayCommand(moveRequest);

        return moveRequest.getGameCommand();
    }

    private void doMove(final Board board, final Turn turn, final MoveRequest moveRequest) {
        final Position source = moveRequest.getSource();
        final Position target = moveRequest.getTarget();

        board.move(source, target, turn.getTurn());

        final BoardResponse boardResponse = new BoardResponse(board.getSquares());
        outputView.printChessBoard(boardResponse);
    }

    private void validatePlayCommand(final MoveRequest moveRequest) {
        if (GameCommand.START.equals(moveRequest.getGameCommand())) {
            throw new IllegalArgumentException("게임 중에는 새 게임을 시작할 수 없습니다.");
        }
    }
}
