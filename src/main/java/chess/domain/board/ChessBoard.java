package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class ChessBoard implements Board {
    
    public static final String OTHER_COLOR_IN_SOURCE = "[BOARD ERROR] 상대편 피스입니다.";
    public static final String NO_PIECE_IN_SOURCE = "[BOARD ERROR] 해당 위치에 피스가 없습니다.";
    public static final String OTHER_PIECE_IN_ROUTE = "[BOARD ERROR] 경로에 다른 피스가 있습니다.";
    public static final String SAME_COLOR_IN_DESTINATION = "[BOARD ERROR] 같은 색의 말이 있습니다.";
    public static final String NO_OTHER_COLOR_IN_DIAGONAL_DESTINATION = "[BOARD ERROR] 대각선으로 이동할 수 없습니다.";
    
    private static final Map<PieceType, RouteCheck> routeCheckMap = new EnumMap<>(PieceType.class);
    
    private final Map<Position, Piece> board;
    
    private Piece lastPiece = Empty.create();
    
    private ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
        routeCheckMap.put(PieceType.PAWN, this::checkPawnRoute);
        routeCheckMap.put(PieceType.ROOK, this::checkSlidingRoute);
        routeCheckMap.put(PieceType.BISHOP, this::checkSlidingRoute);
        routeCheckMap.put(PieceType.QUEEN, this::checkSlidingRoute);
        routeCheckMap.put(PieceType.KING, this::checkNonSlidingRoute);
        routeCheckMap.put(PieceType.KNIGHT, this::checkNonSlidingRoute);
    }
    
    public static ChessBoard create() {
        return new ChessBoard(BoardFactory.create());
    }
    
    private void checkSourcePiece(final Position from, final Color color) {
        if (this.isEmptyAt(from)) {
            throw new IllegalArgumentException(NO_PIECE_IN_SOURCE);
        }
        if (!this.isSameColorAt(from, color)) {
            throw new IllegalArgumentException(OTHER_COLOR_IN_SOURCE);
        }
    }
    
    private void checkDestinationPiece(final Position to, final Color color) {
        if (this.isSameColorAt(to, color)) {
            throw new IllegalArgumentException(SAME_COLOR_IN_DESTINATION);
        }
    }
    
    private void checkNonSlidingRoute(final Position from, final Position to) {
        this.checkPieceMove(from, to);
    }
    
    private void checkSlidingRoute(final Position from, final Position to) {
        this.checkPieceMove(from, to);
        this.checkBetweenRoute(from, to);
    }
    
    private void checkPawnRoute(final Position from, final Position to) {
        this.checkPieceMove(from, to);
        this.checkRestrictionForPawn(from, to);
    }
    
    private void checkPieceMove(final Position from, final Position to) {
        Piece sourcePiece = this.getPiece(from);
        sourcePiece.canMove(from, to);
    }
    
    private void checkBetweenRoute(final Position from, final Position to) {
        Direction direction = from.calculateDirection(to);
        Position move = from.addDirection(direction);
        while (to.isNotEqualTo(move)) {
            this.checkOtherPieceInRoute(move);
            move = move.addDirection(direction);
        }
    }
    
    private void checkOtherPieceInRoute(final Position position) {
        if (this.isNotEmptyAt(position)) {
            throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE);
        }
    }
    
    private void checkRestrictionForPawn(final Position from, final Position to) {
        Direction direction = from.calculateDirection(to);
        if (direction == Direction.N || direction == Direction.S) {
            this.checkOtherPieceInRoute(to);
        }
        if (List.of(Direction.NE, Direction.SE, Direction.NW, Direction.SW).contains(direction)) {
            this.checkEmptyAtDiagonal(to);
        }
    }
    
    private void checkEmptyAtDiagonal(final Position to) {
        if (this.isEmptyAt(to)) {
            throw new IllegalArgumentException(NO_OTHER_COLOR_IN_DIAGONAL_DESTINATION);
        }
    }
    
    private Piece getPiece(final Position position) {
        return this.board.get(position);
    }
    
    private boolean isEmptyAt(final Position position) {
        return this.getPiece(position).isEmpty();
    }
    
    private boolean isNotEmptyAt(final Position position) {
        return !this.isEmptyAt(position);
    }
    
    private boolean isSameColorAt(final Position position, final Color color) {
        return this.getPiece(position).isSameColor(color);
    }
    
    @Override
    public void checkColor(final Position from, final Position to, final Color color) {
        this.checkSourcePiece(from, color);
        this.checkDestinationPiece(to, color);
    }
    
    @Override
    public void checkRoute(final Position from, final Position to) {
        Piece sourcePiece = this.getPiece(from);
        routeCheckMap.get(sourcePiece.getType()).checkRoute(from, to);
    }
    
    @Override
    public void move(final Position from, final Position to) {
        this.lastPiece = this.board.get(to);
        this.board.put(to, this.getPiece(from));
        this.board.put(from, Empty.create());
    }
    
    @Override
    public List<Piece> getRankPieces(Rank rank) {
        return this.board.entrySet().stream()
                .filter(e -> e.getKey().isRank(rank.getIndex()))
                .map(Entry::getValue)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Piece> getFilePieces(final int file) {
        return null;
    }
    
    @Override
    public boolean isKingDead() {
        return this.lastPiece.getType() == PieceType.KING;
    }
    
}
