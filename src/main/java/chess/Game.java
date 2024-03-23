package chess;

import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.dto.PieceResponse;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class Game {
    private final InputView inputView;
    private final OutputView outputView;

    public Game(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Board board = BoardFactory.createBoard();
        String command = inputView.readStartCommand();
        Color nowTurn = Color.WHITE;

        if (command.equals(Command.START_COMMAND)) {
            progressTurn(board, nowTurn);
        }
    }

    private void progressTurn(final Board board, Color nowTurn) {
        outputView.printBoard(createBoardStatus(board.getPieces()));
        Command command = new Command(inputView.readMovement());
        while (!command.isEnd()) {
            moveToCommand(board, nowTurn, command);
            outputView.printBoard(createBoardStatus(board.getPieces()));
            nowTurn = nowTurn.exchangeTurn();
            command = new Command(inputView.readMovement());
        }
    }

    private void moveToCommand(final Board board, final Color nowTurn, final Command command) {
        Square source = Square.from(command.sourceSquare());
        Square target = Square.from(command.targetSquare());
        board.move(source, target, nowTurn);
    }

    private List<PieceResponse> createBoardStatus(final Map<Square, Piece> pieces) {
        return pieces.entrySet().stream()
                .map(entry -> new PieceResponse(entry.getKey(), entry.getValue()))
                .toList();
    }
}
