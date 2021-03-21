package chess.domain.game;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Board {
    
    private final Map<Position, Piece> board;
    
    public Board() {
        this.board = new HashMap<>();
    }
    
    public Board(Map<Position, Piece> board) {
        this.board = board;
    }
    
    public Map<Position, Piece> getBoard() {
        return board;
    }
    
    public Piece get(Position position) {
        return board.get(position);
    }
    
    public void move(Piece sourcePiece, Position sourcePosition, Position targetPosition) {
        board.put(sourcePosition, new Blank());
        board.put(targetPosition, sourcePiece.move(sourcePosition, targetPosition, this));
    }
    
    public double score(Color color) {
        return sumDefaultScoresOfBoard(color) - sumOptionScoresOfPawns(color);
    }
    
    private double sumDefaultScoresOfBoard(Color color) {
        return board.values()
                    .stream()
                    .filter(piece -> piece.isSameColorAs(color))
                    .mapToDouble(Piece::getScore)
                    .sum();
    }
    
    private double sumOptionScoresOfPawns(Color color) {
        return countPawnAtColumn(color).values()
                                       .stream()
                                       .filter(pawnCount -> pawnCount > 1L)
                                       .mapToDouble(pawn -> pawn * 0.5)
                                       .reduce(0, Double::sum);
    }
    
    private Map<Point, Long> countPawnAtColumn(Color color) {
        return board.entrySet()
                    .stream()
                    .filter(entry -> isPawnOfColor(color, entry.getValue()))
                    .collect(groupingBy(entry -> entry.getKey().getX(), counting()));
    }
    
    private boolean isPawnOfColor(Color Color, Piece piece) {
        return piece.isSameColorAs(Color) && piece instanceof Pawn;
    }
    
    public boolean isBlank(Position position) {
        return board.get(position)
                    .isSameColorAs(Color.BLANK);
    }
}
