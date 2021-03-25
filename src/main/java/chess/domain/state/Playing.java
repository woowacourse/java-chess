package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Playing implements GameState {
    @Override
    public GameState start() {
        throw new IllegalArgumentException("이미 게임이 시작했습니다.");
    }

    @Override
    public GameState end() {
        return new Finished();
    }

    @Override
    public GameState move(Grid grid, Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = grid.piece(sourcePosition);
        Piece targetPiece = grid.piece(targetPosition);
        validateSourcePieceIsEmpty(sourcePiece);
        validateMyPiece(sourcePiece, grid);
        if (isKingCaught(targetPiece)) {
            grid.catchKing(sourcePiece.color());
            return new Finished();
        }
        sourcePiece.validateRoute(targetPiece, grid.lines());
        grid.changeTurn();
        grid.update(sourcePiece, targetPiece);
        return new Playing();
    }

    private void validateMyPiece(final Piece sourcePiece, final Grid grid) {
        if (!grid.isMyTurn(sourcePiece.color())) {
            throw new IllegalArgumentException("자신의 말만 옮길 수 있습니다.");
        }
    }

    private void validateSourcePieceIsEmpty(final Piece sourcePiece) {
        if (sourcePiece.isEmpty()) {
            throw new IllegalArgumentException("움직이려는 대상이 빈칸입니다");
        }
    }

    private boolean isKingCaught(final Piece targetPiece) {
        return targetPiece instanceof King;
    }

    @Override
    public GameState status() {
        return new Finished();
    }

    @Override
    public final boolean isFinished() {
        return false;
    }
}
