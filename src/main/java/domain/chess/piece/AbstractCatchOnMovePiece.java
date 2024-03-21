package domain.chess.piece;

import static domain.chess.piece.PieceMoveResult.CATCH;
import static domain.chess.piece.PieceMoveResult.FAILURE;
import static domain.chess.piece.PieceMoveResult.SUCCESS;

import domain.Position;
import domain.chess.board.ChessBoard;
import java.util.Optional;

abstract class AbstractCatchOnMovePiece extends AbstractPiece {
    AbstractCatchOnMovePiece(Position position, Team team) {
        super(position, team);
    }

    @Override
    public final PieceMoveResult tryMove(Position targetPosition, ChessBoard chessBoard) {
        Optional<PieceMoveResult> pieceMoveResult = tryMoveAssumeAloneAndCheckRoute(targetPosition, chessBoard);
        if (pieceMoveResult.isPresent()) {
            return pieceMoveResult.get();
        }
        if (isMyTeam(targetPosition, chessBoard)) {
            return FAILURE;
        }
        if (isOtherTeam(targetPosition, chessBoard)) {
            return CATCH;
        }
        return SUCCESS;
    }

    protected abstract Optional<PieceMoveResult> tryMoveAssumeAloneAndCheckRoute(Position targetPosition,
                                                                                 ChessBoard chessBoard);

    private boolean isMyTeam(Position targetPosition, ChessBoard chessBoard) {
        Optional<Team> targetTeam = chessBoard.whichTeam(targetPosition);
        return targetTeam.isPresent() && targetTeam.get().equals(getTeam());
    }

    private boolean isOtherTeam(Position targetPosition, ChessBoard chessBoard) {
        Optional<Team> targetTeam = chessBoard.whichTeam(targetPosition);
        return targetTeam.isPresent() && targetTeam.get().equals(getTeam().otherTeam());
    }
}
