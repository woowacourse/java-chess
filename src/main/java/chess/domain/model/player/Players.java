package chess.domain.model.player;

import static java.util.stream.Collectors.toList;

import chess.domain.model.Score;
import chess.domain.model.piece.Piece;
import chess.domain.model.position.Position;
import java.util.List;
import java.util.Optional;

public class Players {

    private final List<Player> players;
    private Color current;

    private Players(final List<Player> players, final Color current) {
        this.players = players;
        this.current = current;
    }

    public static Players of(Player whitePlayer, Player blackPlayer, Color current) {
        return new Players(List.of(whitePlayer, blackPlayer), current);
    }

    public boolean isAlreadyExistPiece(final Position tempPosition) {
        return getAllPosition().stream()
                .anyMatch(position -> position.equals(tempPosition));
    }

    private List<Position> getAllPosition() {
        return players.stream()
                .flatMap(player -> player.getPieces().stream().map(Piece::getPosition))
                .collect(toList());
    }

    public Optional<Player> findPlayerByPosition(final Position findPosition) {
        return players.stream()
                .filter(player -> player.hasPositionPiece(findPosition))
                .findFirst();
    }

    public Color changeTurn() {
        current = Color.changeColor(current);
        return current;
    }

    public Piece movePiece(final Position sourcePosition, final Position targetPosition, final boolean isPresent) {
        Player findPlayer = findPlayerByPosition(sourcePosition).orElseThrow();
        Piece pieceOnSourcePosition = findPlayer.getPieceByPosition(sourcePosition);
        pieceOnSourcePosition.move(targetPosition, current, isPresent);
        return pieceOnSourcePosition;
    }

    private Player getAnotherPlayer(final Player findPlayer) {
        return players.stream()
                .filter(player -> !player.getColor().equals(findPlayer.getColor()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상대 플레이어를 찾을 수 없습니다."));
    }

    public void validateTurn(Player findPlayer) {
        if (!current.equals(findPlayer.getColor())) {
            throw new IllegalArgumentException("본인의 차례가 아닙니다.");
        }
    }

    public void validateTargetIsNotSameColor(Position targetPosition, Player findPlayer) {
        if (findPlayer.hasPositionPiece(targetPosition)) {
            throw new IllegalArgumentException("이동 위치에 이미 본인 기물이 있습니다.");
        }
    }

    public List<Piece> getPiecesByColor(final Color color) {
        return getPlayerByColor(color).getPieces();
    }

    private Player getPlayerByColor(final Color color) {
        return players.stream().filter(player -> player.getColor().equals(color))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 색의 플레이어가 없습니다."));
    }

    public boolean isEveryKingAlive() {
        return players.stream().noneMatch(Player::doesNotHaveKing);
    }

    public String getWinnerColorName() {
        Player loser = players.stream().filter(Player::doesNotHaveKing)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("아직 블랙, 화이트 킹이 모두 살아있습니다!"));
        return getAnotherPlayer(loser).getColorName();
    }

    public Score calculateScore() {
        return getPlayerByColor(current)
                .getTotalScore();
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }

}
