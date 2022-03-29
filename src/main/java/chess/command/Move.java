package chess.command;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;
import chess.view.OutputView;
import java.util.Optional;

public class Move extends CommandChain {

    private static final String INVALID_MOVING_COMMAND = "올바르지 않은 이동 명령입니다.";
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final int STARTING_POINT = 1;
    private static final int DESTINATION = 2;

    Move() {
        super(Optional.of(new Status()));
    }

    @Override
    protected boolean canDoAction(Command command, Board board) {
        return command == Command.MOVE && board.isRunning();
    }

    @Override
    protected void doAction(String[] rawCommand, Board board) {
        if (rawCommand.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException(INVALID_MOVING_COMMAND);
        }
        final Position start = Position.from(rawCommand[STARTING_POINT]);
        final Position target = Position.from(rawCommand[DESTINATION]);
        final Color currentColor = board.getCurrentColor();
        if (board.move(start, target, currentColor).isSamePiece(PieceType.KING)) {
            board.terminateGame();
        }
        board.changeTurn();
        OutputView.printBoard(board.getPieces());
    }
}
