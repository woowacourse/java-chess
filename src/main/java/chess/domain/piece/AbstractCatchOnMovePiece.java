package chess.domain.piece;

import static chess.domain.piece.PieceMoveResult.CATCH;
import static chess.domain.piece.PieceMoveResult.FAILURE;
import static chess.domain.piece.PieceMoveResult.SUCCESS;

import chess.domain.Position;
import chess.domain.board.ChessBoard;
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
        return chessBoard.whichTeam(targetPosition)
                .filter(team -> team.equals(getTeam()))
                .isPresent();
    }

    private boolean isOtherTeam(Position targetPosition, ChessBoard chessBoard) {
        return chessBoard.whichTeam(targetPosition)
                .filter(team -> {
                    Team otherTeam = getTeam().otherTeam();
                    return team.equals(otherTeam);
                })
                .isPresent();
    }
}
