package chess.domain;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Players {

    private final List<Player> players;
    private boolean colorFlag = true;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final Player whitePlayer, final Player blackPlayer) {
        return new Players(List.of(whitePlayer, blackPlayer));
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }

    public void move(boolean position) {
        if (colorFlag) {
            players.get(0).move(position);
        }
        players.get(1).move(position);
    }

    public List<Position> getAllPosition() {
        List<Piece> whitePieces = players.get(0).getPieces();
        List<Piece> blackPieces = players.get(1).getPieces();

        List<Position> whitePositions = whitePieces.stream()
                .map(Piece::getPosition)
                .collect(toList());

        List<Position> blackPositions = blackPieces.stream()
                .map(Piece::getPosition)
                .collect(toList());

        return Stream.concat(whitePositions.stream(), blackPositions.stream())
                .collect(toList());
    }

    public boolean isPieceExistsInputPosition(char file, int rank) {
        return getAllPosition().stream()
                .anyMatch(position -> position.isSame(file, rank));
    }
}
