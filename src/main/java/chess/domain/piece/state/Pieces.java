package chess.domain.piece.state;

import chess.config.BoardConfig;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.score.Score;
import chess.domain.position.Direction;
import chess.domain.position.Distance;
import chess.domain.position.Position;
import chess.domain.piece.team.Team;

import java.util.*;

public class Pieces {
    static final String CAN_NOT_MOVE_ERROR = "%s 위치의 말을 %s 위치로 옮길 수 없습니다.";
    static final String NOT_STRAIGHT_ERROR = "%s와 %s의 방향을 측정할 수 없어, 장애물이 있는 지 없는 지 확인할 수 없습니다.";
    private static final int BLACK_PAWN_ROW = 7;
    private static final int WHITE_PAWN_ROW = 2;
    private static final long INITIAL_KING_SIZE = 2L;

    private final Map<Position, Piece> pieces;

    //todo: check is good
    Pieces(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static Pieces initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);
        initializeWhiteTeam(pieces);
        return new Pieces(pieces);
    }

    public Pieces movePiece(Position from, Position to) {
        Piece piece = pieces.get(from);
        if (piece.canNotMove(from, to, this)) {
            throw new IllegalArgumentException(String.format(CAN_NOT_MOVE_ERROR, from, to));

        }

        pieces.put(to, piece);
        pieces.remove(from);
        return new Pieces(pieces);
    }

    Score calculateScoreOf(Team team) {
        Score sum = Score.zero();
        for (int column = BoardConfig.LINE_START; column <= BoardConfig.LINE_END; column++) {
            File file = getFile(column);
            Score fileScore = file.calculateScoreOf(team);
            sum = sum.add(fileScore);
        }
        return sum;
    }

    private File getFile(int column) {
        List<Piece> pieces = new ArrayList<>();
        for (int row = BoardConfig.LINE_START; row <= BoardConfig.LINE_END; row++) {
            Position position = Position.of(column, row);
            Piece piece = getPiece(position);
            //todo check null, refac
            if (piece == null) {
                continue;
            }
            pieces.add(piece);
        }
        return new File(pieces);
    }


//    private boolean hasPeerOnSameCollumn(PiecesState piecesState) {
//        int collumn = position.getX();
//        return IntStream.rangeClosed(BoardConfig.LINE_START, BoardConfig.LINE_END)
//                .mapToObj(row -> piecesState.getPiece(Position.of(collumn, row)))
//                .anyMatch(hasPeerOnSameCollumn());
//    }
//
//    private Predicate<Piece> hasPeerOnSameCollumn() {
//        return piece -> (piece instanceof InitializedPawn || piece instanceof MovedPawn)
//                && !this.equals(piece)
//                && this.isSameTeam(piece);
//    }

    public boolean isBlank(Position position) {
        Piece piece = getPiece(position);
        return Objects.isNull(piece);
    }

    public boolean hasHindranceInStraightBetween(Position from, Position to) {
        if (from.isNotStraightDirection(to)) {
            throw new IllegalArgumentException(String.format(NOT_STRAIGHT_ERROR, from, to));

        }
        Distance amount = from.calculateStraightDistance(to);
        Direction direction = from.calculateDirection(to);
        Position targetPosition = from;
        for (int i = Position.FORWARD_AMOUNT; i < amount.getValue(); i++) {
            targetPosition = targetPosition.go(direction);
            if (isNotBlank(targetPosition)) {
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
        long kingSize = pieces.values()
                .stream()
                .filter(piece -> piece instanceof King)
                .count();
        return kingSize == INITIAL_KING_SIZE;
    }

    public Result concludeResult() {
        return Result.conclude(this);
    }

    public Map<String, String> serialize() {
        Map<String, String> serialized = new HashMap<>();
        for (Map.Entry<Position, Piece> element : pieces.entrySet()) {
            serialized.put(element.getKey().toString(), element.getValue().toString());
        }
        return serialized;
    }

    Map<Position, Piece> getPieces() {
        return pieces;
    }

    private Piece getPiece(Position position) {
        //todo: check if throw is needed
        return pieces.get(position);
    }

    private boolean isNotBlank(Position position) {
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
        Pieces that = (Pieces) o;
        return Objects.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
