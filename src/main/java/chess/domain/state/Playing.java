package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public abstract class Playing implements GameState {
    private static final String WHITE_SPACE_DELIMITER = " ";
    private static final int SOURCE_ARG_INDEX = 1;
    private static final int TARGET_ARG_INDEX = 2;

    @Override
    public final GameState run(final Grid grid, final String command) {
        List<Piece> moveInput = parsedPieces(grid, command);
        Piece sourcePiece = moveInput.get(0);
        Piece targetPiece = moveInput.get(1);
        return move(grid, sourcePiece, targetPiece);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    final void validateSourcePieceIsEmpty(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("움직이려는 대상이 빈칸입니다");
        }
    }

    private List<Piece> parsedPieces(final Grid grid, final String command) {
        List<String> moveInput = Arrays.asList(command.split(WHITE_SPACE_DELIMITER));
        Position sourcePosition = new Position(moveInput.get(SOURCE_ARG_INDEX));
        Position targetPosition = new Position(moveInput.get(TARGET_ARG_INDEX));
        Piece sourcePiece = grid.piece(sourcePosition);
        Piece targetPiece = grid.piece(targetPosition);
        return Arrays.asList(sourcePiece, targetPiece);
    }

    abstract GameState move(final Grid grid, final Piece sourcePiece, final Piece targetPiece);
}
