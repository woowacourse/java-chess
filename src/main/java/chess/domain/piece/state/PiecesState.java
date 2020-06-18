package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.position.Direction;
import chess.domain.position.Distance;
import chess.domain.position.Position;

import java.util.*;

public class PiecesState {
    public static final int BLACK_PAWN_ROW = 7;
    public static final int WHITE_PAWN_ROW = 2;
    static final String CAN_NOT_MOVE_ERROR = "%s 위치의 말을 %s 위치로 옮길 수 없습니다.";
    static final String NOT_STRAIGHT_ERROR = "%s와 %s의 방향을 측정할 수 없어, 장애물이 있는 지 없는 지 확인할 수 없습니다.";
    private static final long INITIAL_KING_SIZE = 2L;

    private final Map<Position, Piece> pieces;

    PiecesState(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static PiecesState initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);
        initializeWhiteTeam(pieces);
        return new PiecesState(pieces);
    }

    public static PiecesState of(Map<Position, Piece> pieces) {
        return new PiecesState(pieces);
    }

    public PiecesState movePiece(Position from, Position to) {
        Piece piece = pieces.get(from);
        if (piece.canNotMove(from, to, this)) {
            throw new IllegalArgumentException(String.format(CAN_NOT_MOVE_ERROR, from, to));

        }
        return updatePiecesState(from, to, piece);
    }


    public boolean hasHindranceInStraightBetween(Position from, Position to) {
        if (from.isNotStraightDirection(to)) {
            throw new IllegalArgumentException(String.format(NOT_STRAIGHT_ERROR, from, to));

        }
        Distance amount = from.calculateStraightDistance(to);
        Direction direction = from.calculateDirection(to);
        return hasHindrance(amount, direction, from);
    }

    public boolean isSameTeam(Position from, Position to) {
        if (isBlank(from) || isBlank(to)) {
            return false;
        }
        Piece fromPiece = findPiece(from);
        Piece toPiece = findPiece(to);
        return fromPiece.isSameTeam(toPiece);
    }

    public Piece findPiece(Position position) {
        return getPiece(position)
                .orElseThrow(() -> new IllegalStateException(String.format("해당 위치 %s에서 체스 말을 찾을 수 없습니다.", position)));
    }

    public boolean isOppositeTeam(Position from, Position to) {
        if (isBlank(from) || isBlank(to)) {
            return false;
        }

        Piece fromPiece = findPiece(from);
        Piece toPiece = findPiece(to);

        return fromPiece.isOppositeTeam(toPiece);
    }

    public boolean isNotFinished() {
        long kingSize = pieces.values()
                .stream()
                .filter(Piece::isKing)
                .count();
        return INITIAL_KING_SIZE <= kingSize;
    }

    public boolean isBlank(Position position) {
        return !getPiece(position).isPresent();
    }

    public Map<String, String> serialize() {
        Map<String, String> serialized = new HashMap<>();
        for (Map.Entry<Position, Piece> element : pieces.entrySet()) {
            Position position = element.getKey();
            Piece piece = element.getValue();
            serialized.put(position.toString(), piece.getName());
        }
        return serialized;
    }

    Score calculateScoreOf(Team team) {
        Score sum = Score.zero();
        for (int column = Board.LINE_START; column <= Board.LINE_END; column++) {
            File file = getFile(column);
            Score fileScore = file.calculateScoreOf(team);
            sum = sum.add(fileScore);
        }
        return sum;
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    private boolean hasHindrance(Distance amount, Direction direction, Position position) {
        for (int i = Position.FORWARD_AMOUNT; i < amount.getValue(); i++) {
            position = position.go(direction);
            if (isNotBlank(position)) {
                return true;
            }
        }
        return false;
    }

    private PiecesState updatePiecesState(Position from, Position to, Piece piece) {
        pieces.put(to, piece);
        pieces.remove(from);
        return new PiecesState(pieces);
    }

    private File getFile(int column) {
        List<Piece> pieces = new ArrayList<>();
        for (int row = Board.LINE_START; row <= Board.LINE_END; row++) {
            Position position = Position.of(column, row);
            addPiece(pieces, position);
        }
        return new File(pieces);
    }

    private void addPiece(List<Piece> pieces, Position position) {
        if (isNotBlank(position)) {
            Piece piece = findPiece(position);
            pieces.add(piece);
        }
    }

    private Optional<Piece> getPiece(Position position) {
        Piece piece = pieces.get(position);
        if (Objects.isNull(piece)) {
            return Optional.empty();
        }

        return Optional.of(piece);
    }

    private boolean isNotBlank(Position position) {
        return getPiece(position).isPresent();
    }

    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.BLACK, Board.LINE_END);
        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);

    }

    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.WHITE, Board.LINE_START);
        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
    }

    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
        for (int x = Board.LINE_START; x <= Board.LINE_END; x++) {
            Position position = Position.of(x, edgeRow);
            Piece piece = PieceFactory.createPieceWithInitialColumn(x, team);
            pieces.put(position, piece);
        }
    }

    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
        for (int x = Board.LINE_START; x <= Board.LINE_END; x++) {
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
