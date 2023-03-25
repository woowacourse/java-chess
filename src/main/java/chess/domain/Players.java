package chess.domain;

import chess.domain.dao.PieceDao;
import chess.domain.dao.PieceDaoImpl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Players {

    private final List<Player> players;
    private Color current;

    private Players(final List<Player> players) {
        this.players = players;
        this.current = Color.WHITE;
    }

    public static Players of(final Player whitePlayer, final Player blackPlayer) {
        return new Players(List.of(whitePlayer, blackPlayer));
    }

    private void validateMovingRoute(final Position fromPosition, final Position toPosition) {
        List<Integer> directionVector = calculateDirectionVector(fromPosition, toPosition);
        validateEachPositions(fromPosition, toPosition, directionVector);
    }

    private List<Integer> calculateDirectionVector(final Position fromPosition, final Position toPosition) {
        int diffFile = toPosition.calculateFileDistance(fromPosition);
        int diffRank = toPosition.calculateRankDistance(fromPosition);

        BigInteger rankAndFileGcd = BigInteger.valueOf(Math.abs(diffFile)).gcd(BigInteger.valueOf(Math.abs(diffRank)));
        int fileDirection = diffFile / rankAndFileGcd.intValue();
        int rankDirection = diffRank / rankAndFileGcd.intValue();
        return List.of(fileDirection, rankDirection);
    }

    private void validateEachPositions(final Position fromPosition, final Position toPosition, final List<Integer> directionVector) {
        Integer fileDirection = directionVector.get(0);
        Integer rankDirection = directionVector.get(1);
        Position tempPosition = fromPosition.move(fileDirection, rankDirection);

        while (!tempPosition.equals(toPosition)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(fileDirection, rankDirection);
        }
    }

    private void validateIsEmpty(final Position tempPosition) {
        if (isAlreadyExistPiece(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private boolean isAlreadyExistPiece(final Position tempPosition) {
        return getAllPosition().stream()
                .anyMatch(position -> position.equals(tempPosition));
    }

    private List<Position> getAllPosition() {
        return players.stream()
                .flatMap(player -> player.getPieces().stream().map(Piece::getPosition))
                .collect(toList());
    }

    private Position findPositionByInputPoint(final String point) {
        Position foundPosition = Position.from(point);
        return getAllPosition().stream()
                .filter(position -> position.equals(foundPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }

    private Player findPlayerByPosition(final Position findPosition) {
        return players.stream()
                .filter(player -> player.hasPositionPiece(findPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }

    public void movePiece(final String inputMovablePiece, final String inputTargetPosition) {
        Position findPosition = findPositionByInputPoint(inputMovablePiece);
        Position targetPosition = Position.from(inputTargetPosition);
        validatePosition(inputMovablePiece);
        move(findPosition, targetPosition);
        changeTurn();
    }

    private void changeTurn() {
        if (current == Color.WHITE) {
            current = Color.BLACK;
            return;
        }
        current = Color.WHITE;
    }

    private void move(final Position sourcePosition, final Position targetPosition) {
        validateMovingRoute(sourcePosition, targetPosition);

        Player findPlayer = findPlayerByPosition(sourcePosition);
        Piece changedPiece = findPlayer.movePiece(getAllPosition(), sourcePosition, targetPosition);

        Player anotherPlayer = getAnotherPlayer(findPlayer);

        PieceDao dao = new PieceDaoImpl();

        dao.updatePosition(changedPiece, sourcePosition);
        anotherPlayer.removePiece(targetPosition)
                .ifPresent(piece -> dao.deletePieceByColor(piece, anotherPlayer.getColor()));
    }

    private Player getAnotherPlayer(final Player findPlayer) {
        return players.stream()
                .filter(player -> !player.getColor().equals(findPlayer.getColor()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상대 플레이어를 찾을 수 없습니다."));
    }

    private void validatePosition(final String inputMovablePiece) {
        Position findPosition = findPositionByInputPoint(inputMovablePiece);
        Player findPlayer = findPlayerByPosition(findPosition);
        if (!current.equals(findPlayer.getColor())) {
            throw new IllegalArgumentException("해당 플레이어의 차례가 아닙니다.");
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

    public boolean notEveryKingAlive() {
        return players.stream().anyMatch(Player::isKingDead);
    }

    public String getWinnerColorName() {
        Player loser = players.stream().filter(Player::isKingDead)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("아직 블랙, 화이트 킹이 모두 살아있습니다!"));
        return getAnotherPlayer(loser).getColorName();
    }

    public double calculateScore() {
        return getPlayerByColor(current)
                .getTotalScore()
                .getValue();
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }

}
