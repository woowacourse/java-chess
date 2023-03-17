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
    
    public static final String OTHER_COLOR_PIECE_ERROR_MESSAGE = "상대편 피스입니다.";
    public static final String NO_PIECE_ERROR_MESSAGE = "피스가 존재하지 않습니다.";
    public static final String OTHER_PIECE_IN_ROUTE = "경로에 다른 피스가 존재합니다.";
    public static final String PIECE_CANNOT_MOVE_SAME_COLOR = "목적지에 같은 색깔의 피스가 있습니다.";
    public static final String PAWN_CANNOT_MOVE_EMPTY_DIAGONAL = "비어있기 때문에 대각선으로 이동할 수 없습니다.";
    private final Map<Position, Piece> board;
    
    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }
    
    
    public static Board create() {
        Map<Position, Piece> board = new TreeMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Position position = Position.from(file.getLabel() + rank.getLabel());
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
        for (Position position : this.board.keySet()) {
            if (position.isRank(0)) {
                this.board.put(position, whiteGenerals.get(position.getFile().getIndex()));
            }
            if (position.isRank(1)) {
                this.board.put(position, whitePawns.get(position.getFile().getIndex()));
            }
            if (position.isRank(6)) {
                this.board.put(position, blackPawns.get(position.getFile().getIndex()));
            }
            if (position.isRank(7)) {
                this.board.put(position, blackGenerals.get(position.getFile().getIndex()));
            }
        }
        
    }
    
    public void printList() {
        List<Piece> piecesAt = this.getPiecesAt(Rank.EIGHT);
        piecesAt.forEach(System.out::println);
    }
    
    public List<Piece> getPiecesAt(Rank rank) {
        return this.board.entrySet().stream()
                .filter(e -> e.getKey().isRank(rank.getIndex()))
                .map(Entry::getValue)
                .collect(Collectors.toList());
    }
    
    public void checkBetweenRoute(final Position source, final Position destination) {
        Direction direction = source.calculateDirection(destination);
        Position move = source.addDirection(direction);
        while (!destination.equals(move)) {
            if (!this.board.get(move).isEmpty()) {
                throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE);
            }
            move = move.addDirection(direction);
        }
    }
    
    public Piece getPiece(final Position source, final Color color) {
        Piece piece = this.board.get(source);
        if (piece.isEmpty()) {
            throw new IllegalArgumentException(NO_PIECE_ERROR_MESSAGE);
        }
        if (!piece.isSameColor(color)) {
            throw new IllegalArgumentException(OTHER_COLOR_PIECE_ERROR_MESSAGE);
        }
        
        return piece;
    }
    
    public void checkRestrictionForPawn(final Position source, final Position destination, final Color color) {
        Direction direction = source.calculateDirection(destination);
        if (direction == Direction.N || direction == Direction.S) {
            if (!this.board.get(destination).isEmpty()) {
                throw new IllegalArgumentException(OTHER_PIECE_IN_ROUTE);
            }
        }
        if (List.of(Direction.NE, Direction.SE, Direction.NW, Direction.SW).contains(direction)) {
            if (this.board.get(destination).isEmpty()) {
                throw new IllegalArgumentException(PAWN_CANNOT_MOVE_EMPTY_DIAGONAL);
            }
            this.checkSameColor(destination, color);
        }
    }
    
    public void checkSameColor(final Position destination, Color color) {
        if (this.board.get(destination).isSameColor(color)) {
            throw new IllegalArgumentException(PIECE_CANNOT_MOVE_SAME_COLOR);
        }
    }
    
    public void replace(final Position source, final Position destination) {
        this.board.put(destination, this.board.get(source));
        this.board.put(source, Empty.create());
    }
}