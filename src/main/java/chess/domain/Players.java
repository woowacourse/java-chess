package chess.domain;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Players {

    private static final Integer MINIMUM_ALIVE_PLAYER_COUNT = 1;

    private final List<Player> players;
    private Color current;

    private Players(final List<Player> players, final Color color) {
        this.players = players;
        this.current = color;
    }

    public static Players of(Player whitePlayer, Player blackPlayer, Color currentTurn) {
        return new Players(List.of(whitePlayer, blackPlayer), currentTurn);
    }

    public Position findPositionByInputPoint(final String point) {
        Position foundPosition = Position.from(point);
        return getAllPosition().stream()
                .filter(position -> position.equals(foundPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }

    public Player findPlayerByPosition(final Position findPosition) {
        return players.stream()
                .filter(player -> player.hasPositionPiece(findPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }

    public void updateTurn() {
        this.current = Color.changeColor(current);
    }

    public Player getAnotherPlayer(final Player findPlayer) {
        return players.stream()
                .filter(player -> !player.getColor().equals(findPlayer.getColor()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상대 플레이어를 찾을 수 없습니다."));
    }

    public void validatePosition(final String inputMovablePiece) {
        Position findPosition = findPositionByInputPoint(inputMovablePiece);
        Player findPlayer = findPlayerByPosition(findPosition);
        if (!current.equals(findPlayer.getColor())) {
            throw new IllegalArgumentException("해당 플레이어의 차례가 아닙니다.");
        }
    }

    public List<Piece> getPiecesByColor(final Color color) {
        return getPlayerByColor(color).getPieces().getPieces();
    }

    private Player getPlayerByColor(final Color color) {
        return players.stream().filter(player -> player.getColor().equals(color))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 색의 플레이어가 없습니다."));
    }

    public boolean everyKingAlive() {
        return players.stream().noneMatch(player -> player.getPieces().isKingDead());
    }

    public String getWinnerColorName() {
        if (isEndGame()) {
            return players.stream()
                    .filter(player -> !player.getPieces().isKingDead())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("블랙, 화이트 킹이 둘 다 죽었습니다!"))
                    .getColorName();
        }

        throw new IllegalArgumentException("아직 블랙, 화이트 킹이 모두 살아있습니다!");
    }

    public List<Position> getAllPosition() {
        return players.stream()
                .flatMap(player -> player.getPieces().getPieces().stream().map(Piece::getPosition))
                .collect(toList());
    }

    private boolean isEndGame() {
        return players.stream()
                .filter(player -> !player.getPieces().isKingDead()).count() == MINIMUM_ALIVE_PLAYER_COUNT;
    }

    public double calculateScore() {
        return getPlayerByColor(current)
                .getTotalScore()
                .getValue();
    }

    public boolean isAlreadyExistPiece(final Position tempPosition) {
        return getAllPosition().stream()
                .anyMatch(position -> position.equals(tempPosition));
    }

    public Color getCurrent() {
        return this.current;
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }

}
