package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class Playing implements GameState {
    private static final String WHITE_SPACE_DELIMITER = " ";
    private static final String STATUS_COMMAND = "status";
    private static final int SOURCE_ARG_INDEX = 1;
    private static final int TARGET_ARG_INDEX = 2;
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";

    @Override
    public final GameState run(final Grid grid, final String input) {
        try {
            return runPlaying(grid, input);
        } catch (IllegalArgumentException error) {
            return playingException(grid, input, error);
        }
    }

    private GameState runPlaying(Grid grid, String input) {
        if (input.equals(STATUS_COMMAND)) {
            return new Status();
        }
        if (input.startsWith(MOVE_COMMAND)) {
            List<String> moveInput = Arrays.asList(input.split(WHITE_SPACE_DELIMITER));

            Position sourcePosition = new Position(moveInput.get(SOURCE_ARG_INDEX));
            Position targetPosition = new Position(moveInput.get(TARGET_ARG_INDEX));
            Piece sourcePiece = grid.piece(sourcePosition);
            Piece targetPiece = grid.piece(targetPosition);

            validateSourcePieceIsEmpty(sourcePiece);
            validateMyPiece(sourcePiece, grid);
            if (isKingCaught(targetPiece)) {
                OutputView.printWinner(sourcePiece.color());
                return new End();
            }
            grid.move(sourcePiece, targetPiece);
            OutputView.printGridStatus(grid.lines());
            return new Playing();
        }
        if (input.equals(END_COMMAND)) {
            return new End();
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }

    private void validateMyPiece(final Piece sourcePiece, final Grid grid) {
        if (!grid.isMyTurn(sourcePiece.color())) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }

    private GameState playingException(Grid grid, String input, IllegalArgumentException error) {
        OutputView.printError(error);
        return run(grid, input);
    }

    private final void validateSourcePieceIsEmpty(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("움직이려는 대상이 빈칸입니다");
        }
    }

    private final boolean isKingCaught(final Piece targetPiece) {
        return targetPiece instanceof King;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }
}
