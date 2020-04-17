package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.strategy.*;
import chess.domain.util.Direction;
import chess.domain.util.Directions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PieceType {
    FIRST_WHITE_PAWN(new PawnMoveStrategy(), Directions.FIRST_WHITE_PAWN_DIRECTION, 'p', Team.WHITE, 1),
    FIRST_BLACK_PAWN(new PawnMoveStrategy(), Directions.FIRST_BLACK_PAWN_DIRECTION, 'P', Team.BLACK, 1),
    WHITE_PAWN(new PawnMoveStrategy(), Directions.WHITE_PAWN_DIRECTION, 'p', Team.WHITE, 1),
    BLACK_PAWN(new PawnMoveStrategy(), Directions.BLACK_PAWN_DIRECTION, 'P', Team.BLACK, 1),

    WHITE_KNIGHT(new SingleMoveStrategy(), Directions.KNIGHT_DIRECTION, 'n', Team.WHITE,  2.5),
    BLACK_KNIGHT(new SingleMoveStrategy(), Directions.KNIGHT_DIRECTION, 'N', Team.BLACK, 2.5),

    WHITE_KING(new SingleMoveStrategy(), Directions.KING_DIRECTION, 'k', Team.WHITE, 0),
    BLACK_KING(new SingleMoveStrategy(), Directions.KING_DIRECTION, 'K', Team.BLACK, 0),

    WHITE_BISHOP(new MultipleMoveStrategy(), Directions.BISHOP_DIRECTION, 'b', Team.WHITE, 3),
    BLACK_BISHOP(new MultipleMoveStrategy(), Directions.BISHOP_DIRECTION, 'B', Team.BLACK, 3),

    WHITE_ROOK(new MultipleMoveStrategy(), Directions.ROOK_DIRECTION, 'r', Team.WHITE, 5),
    BLACK_ROOK(new MultipleMoveStrategy(), Directions.ROOK_DIRECTION, 'R', Team.BLACK, 5),

    WHITE_QUEEN(new MultipleMoveStrategy(), Directions.QUEEN_DIRECTION, 'q', Team.WHITE, 9),
    BLACK_QUEEN(new MultipleMoveStrategy(), Directions.QUEEN_DIRECTION, 'Q', Team.BLACK, 9),

    BLANK(new BlankStrategy(), Directions.BLANK_DIRECTION, '.', Team.BLANK, 0);

    private static final Map<String, PieceType> ENUM_MAP = new HashMap<>();

    static {
        for (PieceType pieceType : values()) {
            ENUM_MAP.put(pieceType.name(), pieceType);
        }
    }

    private final MoveStrategy moveStrategy;
    private final Directions directions;
    private final char representation;
    private final Team team;
    private final double score;

    PieceType(final MoveStrategy moveStrategy, final Directions directions, final char representation, final Team team, final double score) {
        this.moveStrategy = moveStrategy;
        this.directions = directions;
        this.representation = representation;
        this.team = team;
        this.score = score;
    }

    public List<Position> possiblePositions(final Board board, final Piece piece, final Position position) {
        return moveStrategy.possiblePositions(board, piece, position);
    }

    public boolean isSameTeam(final Team currentTurn) {
        return team == currentTurn;
    }

    public boolean isSameTeam(final PieceType pieceType) {
        return team == pieceType.team;
    }

    public boolean isOtherTeam(final PieceType pieceType) {
        return team.isNotSame(pieceType.team);
    }

    public double getScore() {
        return score;
    }

    public List<Direction> getDirections() {
        return directions.getDirections();
    }

    public String getRepresentation() {
        return String.valueOf(representation);
    }
}