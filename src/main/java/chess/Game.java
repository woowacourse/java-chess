package chess;

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
    private static final String START_COMMAND = "start";

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
        while (command.equals(START_COMMAND)) {
            outputView.printBoard(createBoardStatus(board.getPieces()));
            moveToCommand(board, nowTurn);
            nowTurn = nowTurn.exchangeTurn();
        }
    }

    private void moveToCommand(final Board board, final Color nowTurn) {
        List<String> movement = inputView.readMovement();
        Square from = Square.from(movement.get(0));
        Square to = Square.from(movement.get(1));
        board.move(from, to, nowTurn);
    }

    private List<PieceResponse> createBoardStatus(final Map<Square, Piece> pieces) {
        return pieces.entrySet().stream()
                .map(entry -> new PieceResponse(entry.getKey(), entry.getValue()))
                .toList();
    }
}
