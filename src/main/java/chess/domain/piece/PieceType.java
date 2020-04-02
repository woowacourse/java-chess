package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.strategy.*;

import java.util.List;

public enum PieceType {
    WHITE_PAWN(new WhitePawnMoveStrategy(), 'p', Team.WHITE, 1),
    WHITE_KNIGHT(new KnightMoveStrategy(), 'n', Team.WHITE, 2.5),
    WHITE_BISHOP(new BishopMoveStrategy(), 'b', Team.WHITE, 3),
    WHITE_ROOK(new RookMoveStrategy(), 'r', Team.WHITE, 5),
    WHITE_QUEEN(new QueenMoveStrategy(), 'q', Team.WHITE, 9),
    WHITE_KING(new KingMoveStrategy(), 'k', Team.WHITE, 0),

    BLACK_PAWN(new BlackPawnMoveStrategy(), 'P', Team.BLACK, 1),
    BLACK_KNIGHT(new KnightMoveStrategy(), 'N', Team.BLACK, 2.5),
    BLACK_BISHOP(new BishopMoveStrategy(), 'B', Team.BLACK, 3),
    BLACK_ROOK(new RookMoveStrategy(), 'R', Team.BLACK, 5),
    BLACK_QUEEN(new QueenMoveStrategy(), 'Q', Team.BLACK, 9),
    BLACK_KING(new KingMoveStrategy(), 'K', Team.BLACK, 0),

    BLANK(new BlankMoveStrategy(), '.', Team.BLANK, 0);

    private final MoveStrategy moveStrategy;
    private final char representation;
    private final Team team;
    private final double score;

    PieceType(MoveStrategy moveStrategy, char representation, Team team, double score) {
        this.moveStrategy = moveStrategy;
        this.representation = representation;
        this.team = team;
        this.score = score;
    }

    public List<Position> getPossiblePositions(Board board, Piece piece) {
        return moveStrategy.getPossiblePositions(board, piece);
    }

    public boolean isOtherTeam(final PieceType pieceType) {
        return team.isNotSame(pieceType.team);
    }

    public boolean isSameTeam(final PieceType pieceType) {
        return team == pieceType.team;
    }

    public boolean isSameTeam(final Team team) {
        return this.team == team;
    }

    public char representation() {
        return representation;
    }

    public double score() {
        return score;
    }
}
