package chess.domain;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Players {

    private final List<Player> players;
    private Color current;

    private Players(final List<Player> players) {
        this.players = players;
        this.current = Color.WHITE;
    }

    public static Players from(final Player whitePlayer, final Player blackPlayer) {
        return new Players(List.of(whitePlayer, blackPlayer));
    }

    public void validateMovingRoute(final Position fromPosition, final Position toPosition) {
        List<Integer> directionVector = calculateDirectionVector(fromPosition, toPosition);
        validateEachPositions(fromPosition, toPosition, directionVector);
    }

    private List<Integer> calculateDirectionVector(final Position fromPosition, final Position toPosition) {
        int diffFile = toPosition.calculateFileDistance(fromPosition.getFileValue());
        int diffRank = toPosition.calculateRankDistance(fromPosition.getRankValue());

        BigInteger rankAndFileGcd = BigInteger.valueOf(Math.abs(diffFile)).gcd(BigInteger.valueOf(Math.abs(diffRank)));
        int fileDirection = diffFile / rankAndFileGcd.intValue();
        int rankDirection = diffRank / rankAndFileGcd.intValue();
        return List.of(fileDirection, rankDirection);
    }

    private void validateEachPositions(Position fromPosition, Position toPosition, List<Integer> directionVector) {
        Position tempPosition = fromPosition.move(directionVector.get(0), directionVector.get(1));

        while (!tempPosition.equals(toPosition)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(directionVector.get(0), directionVector.get(1));
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
        if (current.isWhite()) {
            current = Color.BLACK;
            return;
        }
        current = Color.WHITE;
    }


    private void move(final Position sourcePosition, final Position targetPosition) {
        validateMovingRoute(sourcePosition, targetPosition);

        Player findPlayer = findPlayerByPosition(sourcePosition);
        Position changedPosition = findPlayer.movePieceByInput(getAllPosition(), sourcePosition, targetPosition);

        Player anotherPlayer = getAnotherPlayer(findPlayer);
        anotherPlayer.removePiece(changedPosition);
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

    private Player getPlayerByColor(Color color) {
        return players.stream().filter(player -> player.getColor().equals(color))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("해당하는 색의 플레이어가 없습니다."));
    }
    public List<Piece> getPiecesByColor(Color color) {
        return getPlayerByColor(color).getPieces();
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
