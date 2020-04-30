package chess.domain.piece;

import chess.config.BoardConfig;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Distance;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PiecesState {
    static final String CAN_NOT_MOVE_ERROR = "%s 위치의 말을 %s 위치로 옮길 수 없습니다.";
    private static final int BLACK_PAWN_ROW = 7;
    private static final int WHITE_PAWN_ROW = 2;

    private final Map<Position, Piece> pieces;

    //todo: check is good
    PiecesState(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static PiecesState initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);
        initializeWhiteTeam(pieces);
        return new PiecesState(pieces);
    }

    public PiecesState movePiece(Position from, Position to) {
        Piece piece = pieces.get(from);
        if (piece.canNotMove(from, to, this)) {
            throw new IllegalArgumentException(String.format(CAN_NOT_MOVE_ERROR, from, to));

        }

        pieces.put(to, piece);
        pieces.remove(from);
        return new PiecesState(pieces);
    }

    public boolean isNotFilled(Position position) {
        Piece piece = getPiece(position);
        return Objects.isNull(piece);
    }

    public boolean hasHindranceInStraightBetween(Position from, Position to) {
        Distance amount = from.calculateStraightDistance(to);
        Direction direction = from.calculateDirection(to);
        Position targetPosition = from;
        for (int i = Position.FORWARD_AMOUNT; i < amount.getValue(); i++) {
            targetPosition = targetPosition.go(direction);
            if (isFilled(targetPosition)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSameTeam(Position from, Position to) {
        Piece fromPiece = pieces.get(from);
        Piece toPiece = pieces.get(to);
        if (Objects.isNull(fromPiece) || Objects.isNull(toPiece)) {
            return false;
        }
        return fromPiece.isSameTeam(toPiece);
    }

    public boolean isOppositeTeam(Position from, Position to) {
        Piece fromPiece = pieces.get(from);
        Piece toPiece = pieces.get(to);
        if (Objects.isNull(fromPiece) || Objects.isNull(toPiece)) {
            return false;
        }
        return fromPiece.isOppositeTeam(toPiece);
    }

    //todo: refac
    public boolean isNotFinished() {
        long size = pieces.values()
                .stream()
                .filter(piece -> piece instanceof King)
                .count();
        return size == 2L;
    }

    public Result concludeResult() {
        return new Result(pieces);
    }

    public Map<String, String> serialize() {
        Map<String, String> serialized = new HashMap<>();
        for (Map.Entry<Position, Piece> element : pieces.entrySet()) {
            serialized.put(element.getKey().toString(), element.getValue().toString());
        }
        return serialized;
    }

    private Piece getPiece(Position position) {
        return pieces.get(position);
    }

    private boolean isFilled(Position position) {
        Piece piece = getPiece(position);
        return Objects.nonNull(piece);
    }

    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.BLACK, BoardConfig.LINE_END);
        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);

    }

    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.WHITE, BoardConfig.LINE_START);
        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
    }

    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
            Position position = Position.of(x, edgeRow);
            Piece piece = PieceFactory.createPieceWithInitialColumn(x, team);
            pieces.put(position, piece);
        }
    }

    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
        for (int x = BoardConfig.LINE_START; x <= BoardConfig.LINE_END; x++) {
            Position position = Position.of(x, row);
            Piece initializedPawn = PieceFactory.createInitializedPawn(team);
            pieces.put(position, initializedPawn);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiecesState that = (PiecesState) o;
        return Objects.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
