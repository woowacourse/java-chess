package chess;

import chess.piece.Color;
import chess.piece.Piece;
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

        String command = InputView.readStartCommand();

        if (!command.equals(START_COMMAND)) {
            return;
        }

        Color turn = Color.WHITE;
        while (true) {
            OutputView.printBoard(boardMap);
            List<String> moveCommand = InputView.readMoveCommand();

            String from = moveCommand.get(0);
            String to = moveCommand.get(1);

            int fromFile = from.charAt(0) - 'a' + 1;
            int fromRank = from.charAt(1) - '0';

            int toFile = to.charAt(0) - 'a' + 1;
            int toRank = to.charAt(1) - '0';

            Position fromPosition = new Position(fromFile, fromRank);
            Position toPosition = new Position(toFile, toRank);

            board.move(fromPosition, toPosition, turn);

            turn = turn.opposite();
        }
    }
}
