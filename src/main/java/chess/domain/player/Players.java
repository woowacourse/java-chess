package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;
import chess.manager.Status;

import java.util.Arrays;
import java.util.List;

public class Players {
    private final List<Player> players;
    private Turn turn;

    public Players() {
        players = Arrays.asList(
                PlayerInitializer.initPlayer(Owner.BLACK),
                PlayerInitializer.initPlayer(Owner.WHITE)
        );

        this.turn = Turn.BLACK;
    }

    public void updatePositions(final Position source, final Position target) {
        final Player turnPlayer = players.get(turn.index());
        final Player otherPlayer = players.get(turn.otherIndex());

        turnPlayer.move(source, target);
        otherPlayer.removeIfExist(target);
    }

    public void changeTurn() {
        this.turn = this.turn.change();
    }

    public Status getStatus(final Board board) {
        return new Status(players.get(1).calculateScore(board), players.get(0).calculateScore(board));
    }

    public boolean isKingDead(final Board board) {
        return players.stream()
                .filter(player -> player.isKingDead(board))
                .findFirst()
                .isPresent();
    }

    public void validateTurn(final Position source) {
        final Player turnPlayer = players.get(turn.index());

        if (!turnPlayer.contains(source)) {
            throw new IllegalArgumentException("현재 턴의 기물이 아닙니다.");
        }
    }
}
