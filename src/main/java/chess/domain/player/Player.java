package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.domain.piece.Score;

import java.util.List;

public class Player {
    private final Owner owner;
    private final List<Position> positions;

    public Player(final List<Position> positions, final Owner owner) {
        this.positions = positions;
        this.owner = owner;
    }

    public Score score(final Board board) {
        if (isDead(board)) {
            return Score.EMPTY;
        }

        final Score score = addAllPiecesScores(board);
        return score.applyPawnPenalty(board.countDuplicatedPawnInLine(owner));
    }

    private Score addAllPiecesScores(final Board board) {
        Score score = Score.EMPTY;
        for (final Position position : positions) {
            score = score.plus(board.of(position).score());
        }
        return score;
    }

    public void move(final Position source, final Position target) {
        positions.remove(source);
        positions.add(target);
    }

    public void captured(final Position target) {
        positions.remove(target);
    }

    public boolean isDead(final Board board) {
        return positions.stream()
                .map(position -> board.of(position))
                .noneMatch(piece -> piece.isKing());
    }

    public boolean has(final Position position) {
        return positions.contains(position);
    }

    public Owner owner() {
        return owner;
    }

    public boolean isOwner(final Owner owner) {
        return this.owner.equals(owner);
    }
}
