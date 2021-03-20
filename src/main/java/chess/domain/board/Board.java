package chess.domain.board;

import chess.domain.direction.Direction;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.*;
import chess.manager.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;
    private boolean isEnd = false;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece of(Position position) {
        return board.get(position);
    }

    public Piece of(Vertical vertical, Horizontal horizontal) {
        return of(new Position(vertical, horizontal));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void move(Position source, Position target, Owner owner) {
        if (!of(source).isOwner(owner)) {
            throw new IllegalArgumentException();
        }

        if (!ableToMove(source).contains(target)) {
            throw new IllegalArgumentException();
        }
        checkGameEnd(target);
        movePiece(source, target);
    }

    private void checkGameEnd(Position target){
        if(of(target).isKing()){
            isEnd = true;
        }
    }

    private void movePiece(Position source, Position target) {
        putPiece(source, target);
        putEmpty(source);
    }

    private void putPiece(Position source, Position target) {
        board.put(target, board.get(source));
    }

    private void putEmpty(Position position) {
        board.put(position, Empty.getInstance());
    }

    public Status getStatus(){
        return new Status(calculateScore(Owner.WHITE), calculateScore(Owner.BLACK));
    }

    private Score calculateScore(Owner owner){
        Score score = new Score(0);
        boolean existKing = false;

        for(Vertical v : Vertical.values()){
            for(Horizontal h : Horizontal.values()){
                Piece piece = of(v,h);

                if(!piece.isOwner(owner) ){
                    continue;
                }

                if(piece.isKing()){
                    existKing = true;
                }

                score = score.plus(piece.score());
            }
        }

        if(existKing == false){
            return new Score(0);
        }

        return score.calculatePawnPenaltyScore(getPawnCountInLine(owner));
    }

    private int getPawnCountInLine(Owner owner){
        int totalCount = 0;
        for(Vertical v : Vertical.values()){
            int verticalCount = 0;
            for(Horizontal h : Horizontal.values()){
                if(of(v,h).equals(Pawn.getInstanceOf(owner))){
                    verticalCount++;
                }
            }
            if(verticalCount > 1){
                totalCount += verticalCount;
            }
        }
        return totalCount;
    }

    public List<Position> ableToMove(Position source) {
        List<Position> ableToMove = new ArrayList<>();
        Piece sourcePiece = of(source);

        for (Direction direction : sourcePiece.getDirections()) {
            for (int i = 1; i <= sourcePiece.getMaxDistance(); i++) {
                Position nextPosition;
                try {
                    nextPosition = source.next(direction, i);
                } catch (IllegalArgumentException e) {
                    break;
                }

                Piece nextPiece = of(nextPosition);

                if (sourcePiece.isSameTeam(nextPiece)) {
                    break;
                }

                if (sourcePiece.validateMove(source, nextPosition, nextPiece)) {
                    ableToMove.add(nextPosition);
                }

                if (sourcePiece.isEnemy(nextPiece)) {
                    break;
                }
            }
        }

        return ableToMove;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<Position> getAbleToMove(Position source) {
        return ableToMove(source);
    }
}
