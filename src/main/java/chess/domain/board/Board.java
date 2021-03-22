package chess.domain.board;

import chess.domain.Color;
import chess.domain.Status;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.MovePosition;
import chess.domain.position.Point;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Board {
    
    private static final int DEFAULT_SUM_OF_PAWN_OPTION_SCORE = 0;
    private static final double OPTION_SCORE_OF_PAWN = 0.5;
    
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
    
    public Status move(MovePosition movePosition) {
        final Position sourcePosition = movePosition.getSourcePosition();
        final Position targetPosition = movePosition.getTargetPosition();
        final Piece sourcePiece = board.get(sourcePosition);
        final Piece targetPiece = board.get(targetPosition);
        
        sourcePiece.checkToMoveToTargetPosition(movePosition, this);
        board.put(sourcePosition, Blank.INSTANCE);
        board.put(targetPosition, sourcePiece);
        
        if (kingWillDie(targetPiece)) {
            return Status.KING_DEAD;
        }
        
        return Status.RUNNING;
    }
    
    private boolean kingWillDie(Piece targetPiece) {
        return targetPiece.isKing();
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
                                       .filter(this::isSeveralPawnExist)
                                       .mapToDouble(this::changePawnScoreToHalf)
                                       .reduce(DEFAULT_SUM_OF_PAWN_OPTION_SCORE, Double::sum);
    }
    
    private boolean isSeveralPawnExist(Long pawnCont) {
        return pawnCont > 1L;
    }
    
    private double changePawnScoreToHalf(Long pawnCont) {
        return (double) pawnCont * OPTION_SCORE_OF_PAWN;
    }
    
    private Map<Point, Long> countPawnAtColumn(Color color) {
        return board.entrySet()
                    .stream()
                    .filter(entry -> isPawnOfColor(color, entry.getValue()))
                    .collect(groupingBy(entry -> entry.getKey()
                                                      .getX(), counting()));
    }
    
    private boolean isPawnOfColor(Color Color, Piece piece) {
        return piece.isSameColorAs(Color) && piece instanceof Pawn;
    }
    
    public boolean isBlank(Position position) {
        final Piece piece = board.get(position);
        return piece.isBlank();
    }
}
