package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Direction;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Board {

    public static final int WHITE_GENERALS_RANK = 0;
    public static final int WHITE_PAWNS_RANK = 1;
    public static final int BLACK_PAWNS_RANK = 6;
    public static final int BLACK_GENERALS_RANK = 7;

    private final Map<Position, Piece> board;
    
    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }
    
    public static Board create() {
        Map<Position, Piece> board = new TreeMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Position position = Position.from(file, rank);
                board.put(position, Empty.create());
            }
        }
        return new Board(board);
    }
    
    public void initialize() {
        List<Piece> whiteGenerals = PieceFactory.createWhiteGenerals();
        List<Piece> whitePawns = PieceFactory.createWhitePawns();
        List<Piece> blackPawns = PieceFactory.createBlackPawns();
        List<Piece> blackGenerals = PieceFactory.createBlackGenerals();
        for (Position position : board.keySet()) {
            placePieceAtPosition(whiteGenerals, position, WHITE_GENERALS_RANK);
            placePieceAtPosition(whitePawns, position, WHITE_PAWNS_RANK);
            placePieceAtPosition(blackPawns, position, BLACK_PAWNS_RANK);
            placePieceAtPosition(blackGenerals, position, BLACK_GENERALS_RANK);
        }
    }
    
    private void placePieceAtPosition(final List<Piece> pieces, final Position position, int rank) {
        if (position.isRank(rank)) {
            board.put(position, pieces.get(position.getFile().getIndex()));
        }
    }
    
    public Piece getValidSourcePiece(final Position source, final Color color) {
        if (isEmpty(source)) {
            throw new IllegalArgumentException("피스가 존재하지 않습니다.");
        }
        if (!isSameColor(source, color)) {
            throw new IllegalArgumentException("상대편 피스입니다.");
        }
        return board.get(source);
    }
    
    public void checkBetweenRoute(final Position source, final Position destination) {
        Direction direction = source.calculateDirection(destination);
        Position move = source.addDirection(direction);
        while (!destination.equals(move)) {
            checkOtherPieceInRoute(move);
            move = move.addDirection(direction);
        }
    }
    
    private void checkOtherPieceInRoute(final Position move) {
        if (!isEmpty(move)) {
            throw new IllegalArgumentException("경로에 다른 피스가 존재합니다.");
        }
    }
    
    public void checkRestrictionForPawn(final Position source, final Position destination, final Color color) {
        Direction direction = source.calculateDirection(destination);
        if (direction == Direction.N || direction == Direction.S) {
            checkOtherPieceInRoute(destination);
        }
        if (Direction.isDiagonal(direction)) {
            checkDiagonalPiece(destination);
            checkSameColor(destination, color);
        }
    }
    
    private void checkDiagonalPiece(final Position destination) {
        if (isEmpty(destination)) {
            throw new IllegalArgumentException("비어있기 때문에 대각선으로 이동할 수 없습니다.");
        }
    }
    
    public void checkSameColor(final Position destination, Color color) {
        if (isSameColor(destination, color)) {
            throw new IllegalArgumentException("목적지에 같은 색깔의 피스가 있습니다.");
        }
    }
    
    public void replace(final Position source, final Position destination) {
        board.put(destination, board.get(source));
        board.put(source, Empty.create());
    }
    
    private boolean isEmpty(final Position position) {
        return board.get(position).isEmpty();
    }
    
    private boolean isSameColor(final Position position, final Color color) {
        return board.get(position).isSameColor(color);
    }
    
    public List<Piece> getPiecesAt(Rank rank) {
        return board.entrySet().stream()
                .filter(e -> e.getKey().isRank(rank.getIndex()))
                .map(Entry::getValue)
                .collect(Collectors.toList());
    }
}
