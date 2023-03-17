package chess.controller;

import chess.domain.board.position.Position;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChessController {

    private static final String START_COMMAND = "start";

    public void run() {
        final BoardFactory boardFactory = new BoardFactory();

        final Board board = new Board(boardFactory);
        final Map<Position, Piece> boardMap = board.board();

        final String command = InputView.readStartCommand();

        if (!command.equals(START_COMMAND)) {
            return;
        }

        Color turn = Color.WHITE;

        while (true) {
            OutputView.printBoard(boardMap);
            List<String> moveCommand = InputView.readMoveCommand();

            final String from = moveCommand.get(0);
            final String to = moveCommand.get(1);

            final int fromFile = from.charAt(0) - 'a' + 1;
            final int fromRank = from.charAt(1) - '0';

            final int toFile = to.charAt(0) - 'a' + 1;
            final int toRank = to.charAt(1) - '0';

            final Position fromPosition = new Position(fromFile, fromRank);
            final Position toPosition = new Position(toFile, toRank);

            board.move(fromPosition, toPosition, turn);

            turn = turn.opposite();
        }
    }
}
