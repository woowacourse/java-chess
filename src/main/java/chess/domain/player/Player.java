package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.piece.Score;

import java.util.List;

public class Player {
    private final List<Position> positions;
    private final Owner owner;

    public Player(final List<Position> positions, final Owner owner){
        this.positions = positions;
        this.owner = owner;
    }

    public Score calculateScore(final Board board) {
        if (isKingDead(board)) {
            return Score.EMPTY;
        }

        final Score score = getScore(board);
        int pawnCountDuplicatedInLine = board.countDuplicatedPawnInLine(owner);

        return score.calculatePawnPenaltyScore(pawnCountDuplicatedInLine);
    }

    private Score getScore(final Board board){
        Score score = Score.EMPTY;

        for(Position position : positions){
            score = score.plus(board.getPieceOf(position).score());
        }

        return score;
    }

    public boolean isKingDead(final Board board) {
        return !positions.stream()
                .filter(position -> board.getPieceOf(position).isKing())
                .findFirst()
                .isPresent();
    }

    public void removeIfExist(Position target) {
        if(positions.contains(target)){
            positions.remove(target);
        }
    }

    public void move(Position source, Position target) {
        positions.remove(source);
        positions.add(target);
    }

    public boolean contains(final Position position){
        return positions.contains(position);
    }
}
