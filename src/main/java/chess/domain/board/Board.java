package chess.domain.board;

import chess.domain.Color;
import chess.domain.Status;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Board {
    
    private static final int DEFAULT_SUM_OF_PAWN_OPTION_SCORE = 0;
    private static final double OPTION_SCORE_OF_PAWN = 0.5;
    
    private static final String ERROR_SOURCE_PIECE_IS_BLANK = "움직이려 하는 위치에 기물이 없습니다.";
    private static final String ERROR_SOURCE_PIECE_IS_NOT_MINE = "움직이려 하는 기물은 상대방의 기물입니다.";
    private static final String ERROR_TARGET_PIECE_IS_MINE = "이동하려는 위치에 자신의 말이 있습니다.";
    
    private final Map<Position, Piece> board;
    
    public Board(Map<Position, Piece> board) {
        this.board = board;
    }
    
    public Map<Position, Piece> getBoard() {
        return board;
    }
    
    public Status move(MovePosition movePosition, Color turn) {
        final Position sourcePosition = movePosition.getSourcePosition();
        final Position targetPosition = movePosition.getTargetPosition();
        final Piece sourcePiece = board.get(sourcePosition);
        final Piece targetPiece = board.get(targetPosition);
        
        checkColorOfPiece(movePosition, turn);
        sourcePiece.checkToMoveToTargetPosition(movePosition, this);
        board.put(sourcePosition, Blank.INSTANCE);
        board.put(targetPosition, sourcePiece);
        
        if (kingWillDie(targetPiece)) {
            return Status.KING_DEAD;
        }
        
        return Status.RUNNING;
    }
    
    private void checkColorOfPiece(MovePosition movePosition, Color turn) {
        final Piece sourcePiece = board.get(movePosition.getSourcePosition());
        final Piece targetPiece = board.get(movePosition.getTargetPosition());
        
        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException(ERROR_SOURCE_PIECE_IS_BLANK);
        }
        
        if (!sourcePiece.isSameColorAs(turn)) {
            throw new IllegalArgumentException(ERROR_SOURCE_PIECE_IS_NOT_MINE);
        }
        
        if (targetPiece.isSameColorAs(turn)) {
            throw new IllegalArgumentException(ERROR_TARGET_PIECE_IS_MINE);
        }
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
    
    private Map<Integer, Long> countPawnAtColumn(Color color) {
        return board.entrySet()
                    .stream()
                    .filter(entry -> isPawnOfColor(color, entry.getValue()))
                    .collect(groupingBy(entry -> entry.getKey().getX(), counting()));
    }
    
    private boolean isPawnOfColor(Color Color, Piece piece) {
        return piece.isSameColorAs(Color) && piece.isPawn();
    }
    
    public boolean isBlank(Position position) {
        final Piece piece = board.get(position);
        return piece.isBlank();
    }
}
