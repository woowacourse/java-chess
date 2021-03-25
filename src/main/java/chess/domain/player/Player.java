package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.piece.Score;

import java.util.List;

public class Player {
    private final Owner owner;
    private final List<Position> positions;
    private State state;

    public Player(final List<Position> positions, final Owner owner) {
        this.positions = positions;
        this.owner = owner;
        this.state = State.LiVE;
    }

    public Score calculateScore(final Board board) {
        if (state.isDead()) {
            return Score.EMPTY;
        }

        final Score score = getScore(board);
        int pawnCountDuplicatedInLine = board.countDuplicatedPawnInLine(owner);

        return score.calculatePawnPenaltyScore(pawnCountDuplicatedInLine);
    }

    private Score getScore(final Board board) {
        Score score = Score.EMPTY;

        for (Position position : positions) {
            score = score.plus(board.of(position).score());
        }

        return score;
    }

    public void removeIfExist(final Position target) {
        if (positions.contains(target)) {
            positions.remove(target);
        }
    }

    public void move(final Position source, final Position target) {
        positions.remove(source);
        positions.add(target);
    }

    public boolean contains(final Position position) {
        return positions.contains(position);
    }

    public void makeKingDead() {
        this.state = State.DEAD;
    }

    public boolean isEnd() {
        return state.isDead();
    }
}
