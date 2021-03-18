package chess.board;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    
    private static final int MAX_INDEX = 7;
    private static final int MIN_INDEX = 0;
    
    private Color turn = Color.WHITE;
    private final List<List<Piece>> board = new ArrayList<>();
    // 끝났는지 여부
    
    public Board() {
        for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
            board.add(new ArrayList<>());
        }
    }
    
    public void movePiece(String sourceValue, String targetValue) {
        Position sourcePosition = Position.of(sourceValue);
        Position targetPosition = Position.of(targetValue);
    
        final int sourceX = sourcePosition.getX().getPoint();
        final int sourceY = sourcePosition.getY().getPoint();
    
        final int targetX = targetPosition.getX().getPoint();
        final int targetY = targetPosition.getY().getPoint();
    
        final Piece sourcePiece = board.get(sourceX).get(sourceY);
        
        validateColorOfPiece(sourcePiece);
        
        final Piece targetPiece = board.get(targetX).get(targetY);
        if (targetPiece.isSameColor(turn)) {
            throw new IllegalArgumentException("이동하려는 위치에 자신의 말이 있습니다.");
        }
        // TODO - 피스에게 이동여부 보내기
        // 기물이 타겟 위치로 이동할 수 있는가? - 중간에 기물이 있다던가
        
        board.get(sourceX).set(sourceY, new Blank(Color.BLANK, sourcePosition));
        board.get(targetX).set(targetY, sourcePiece.move(targetPosition, board));
        
        turn = turn.next();
    }
    
    private void validateColorOfPiece(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException("움직이려 하는 말은 상대방의 말입니다.");
        }
    }
    
    public void init() {
        initGeneral(MIN_INDEX, Color.WHITE);
        initGeneral(MAX_INDEX, Color.BLACK);
        initPawn(1, Color.WHITE);
        initPawn(6, Color.BLACK);
        initBlank();
    }
    
    private void initGeneral(int row, Color color) {
        board.get(row)
             .add(new Rook(color, Position.of(row, 0)));
        board.get(row)
             .add(new Knight(color, Position.of(row, 1)));
        board.get(row)
             .add(new Bishop(color, Position.of(row, 2)));
        board.get(row)
             .add(new Queen(color, Position.of(row, 3)));
        board.get(row)
             .add(new King(color, Position.of(row, 4)));
        board.get(row)
             .add(new Bishop(color, Position.of(row, 5)));
        board.get(row)
             .add(new Knight(color, Position.of(row, 6)));
        board.get(row)
             .add(new Rook(color, Position.of(row, 7)));
    }
    
    private void initPawn(int row, Color color) {
        for (int y = 0; y <= MAX_INDEX; y++) {
            board.get(row)
                 .add(new Pawn(color, Position.of(row, y)));
        }
    }
    
    private void initBlank() {
        for (int x = 2; x < 6; x++) {
            for (int y = MIN_INDEX; y <= MAX_INDEX; y++) {
                board.get(x)
                     .add(new Blank(Color.BLANK, Position.of(x, y)));
            }
        }
    }
    
    public double score(Color color) {
        int score = 0;
        for (List<Piece> rank : board) {
            for (Piece piece : rank) {
                if (piece.isSameColor(color)) {
                    score += piece.getScore();
                }
            }
        }
        return score;
    }
    
    public Color getWinner() {
        // todo - 안끝났을때 요청하면 에러 출력
        return Color.BLACK;
    }
    
    public List<List<Piece>> getBoard() {
        return board;
    }
}
