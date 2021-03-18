package chess.board;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    
    private static final int MAX_INDEX = 7;
    private static final int MIN_INDEX = 0;
    
    // todo - 일급 컬렉션으로 리팩토링
    private List<List<Piece>> board;
    private State state;
    
    public Board() {
        state = new State();
        state.finish();
    }
    
    public void movePiece(String sourceValue, String targetValue) {
        if (state.isFinish()) {
            throw new IllegalArgumentException("게임진행중이 아닙니다.");
        }
        Position sourcePosition = Position.of(sourceValue);
        Position targetPosition = Position.of(targetValue);
    
        final int sourceX = sourcePosition.getX().getPoint();
        final int sourceY = sourcePosition.getY().getPoint();
    
        final int targetX = targetPosition.getX().getPoint();
        final int targetY = targetPosition.getY().getPoint();
    
        final Piece sourcePiece = board.get(sourceX).get(sourceY);
        
        validateColorOfPiece(sourcePiece);
        
        final Piece targetPiece = board.get(targetX).get(targetY);
        if (state.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("이동하려는 위치에 자신의 말이 있습니다.");
        }
        
        // todo - 킹이 죽으면 게임종료로 변경하기
        if (targetPiece instanceof King) {
            state.finish();
            state.setWinner();
        }
        board.get(sourceX).set(sourceY, new Blank(Color.BLANK, sourcePosition));
        board.get(targetX).set(targetY, sourcePiece.move(targetPosition, board));
        
        state.nextTurn();
    }
    
    private void validateColorOfPiece(Piece sourcePiece) {
        if (!state.isSameColor(sourcePiece)) {
            throw new IllegalArgumentException("움직이려 하는 말은 상대방의 말입니다.");
        }
    }
    
    public void init() {
        board = new ArrayList<>();
        for (int i = MIN_INDEX; i <= MAX_INDEX; i++) {
            board.add(new ArrayList<>());
        }
        state = new State();
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
        double score = 0;
        for (List<Piece> rank : board) {
            for (Piece piece : rank) {
                if (piece.isSameColor(color)) {
                    score += piece.getScore();
                }
            }
        }
        // todo - 겹치는 폰 체크해서 나오는 값을 스코어에서 감소시키기
        score -= checkPawn(color) * 0.5;
        return score;
    }
    
    private int checkPawn(Color color) {
        int count = 0;
        for (int column = MIN_INDEX; column <= MAX_INDEX; column++) {
            int pawnCount = 0;
            for (int row = MIN_INDEX; row <= MAX_INDEX; row++) {
                Piece piece = board.get(row).get(column);
                if (piece.isSameColor(color) && piece instanceof Pawn) {
                    pawnCount++;
                }
            }
            if (pawnCount >= 2) {
                count += pawnCount;
            }
        }
        return count;
    }
    
    public Color winner() {
//        if (!state.isFinish()) {
//            throw new IllegalArgumentException("게임이 진행중입니다.");
//        }
        return state.winner();
    }
    
    public List<List<Piece>> getBoard() {
        // todo - 의존성 끊
        return board;
    }
    
    public boolean isFinish() {
        return state.isFinish();
    }
}
