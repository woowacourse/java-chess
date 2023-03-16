package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
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
        for (Position position : board.keySet()) {
            if (position.isRank(0)) {
                board.put(position, whiteGenerals.get(position.getFile().getIndex()));
            }
            if (position.isRank(1)) {
                board.put(position, whitePawns.get(position.getFile().getIndex()));
            }
            if (position.isRank(6)) {
                board.put(position, blackPawns.get(position.getFile().getIndex()));
            }
            if (position.isRank(7)) {
                board.put(position, blackGenerals.get(position.getFile().getIndex()));
            }
        }
        
    }
    
    public Map<Position, Piece> getBoard() {
        //TODO:얘를 그대로 남겨도 될까..
        return this.board;
    }
    
    public void printList() {
        List<Piece> piecesAt = getPiecesAt(Rank.EIGHT);
        piecesAt.forEach(System.out::println);
    }
    
    public List<Piece> getPiecesAt(Rank rank) {
        return this.board.entrySet().stream()
                .filter(e -> e.getKey().isRank(rank.getIndex()))
                .map(Entry::getValue)
                .collect(Collectors.toList());
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
}