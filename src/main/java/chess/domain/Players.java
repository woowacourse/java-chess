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

    public void validateAlreadyExistPieceMovingRoute(
            final Position movablePiecePosition,
            final Position targetPosition
    ) {
        Position fromPosition = Position.from(movablePiecePosition.getRankValue(), movablePiecePosition.getFileValue());
        Position toPosition = Position.from(targetPosition.getRankValue(), targetPosition.getFileValue());

        int diffFile = toPosition.calculateFileDistance(fromPosition.getFileValue());
        int diffRank = toPosition.calculateRankDistance(fromPosition.getRankValue());

        BigInteger rankAndFileGcd = BigInteger.valueOf(Math.abs(diffFile)).gcd(BigInteger.valueOf(Math.abs(diffRank)));
        int fileDirection = diffFile / rankAndFileGcd.intValue();
        int rankDirection = diffRank / rankAndFileGcd.intValue();

        Position tempPosition = fromPosition.move(fileDirection, rankDirection);

        while (!tempPosition.equals(toPosition)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(fileDirection, rankDirection);
        }
    }

    private void validateIsEmpty(Position tempPosition) {
        if (isAlreadyExistPiece(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private boolean isAlreadyExistPiece(Position tempPosition) {
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

    public boolean isPieceExistsInputPosition(char file, int rank) {
        return getAllPosition().stream()
                .anyMatch(position -> position.equals(Position.from(rank, file)));
    }

    public Position findPositionByInputPoint(String point) {
        Position foundPosition = Position.from(point);
        return getAllPosition().stream()
                .filter(position -> position.equals(foundPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }

    public Player findPlayerByPosition(Position findPosition) {
        return players.stream()
                .filter(player -> player.hasPositionPiece(findPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }

    public Player getAnotherPlayer(Player findPlayer) {
        for (Player player : players) {
            if (!player.getColor().equals(findPlayer.getColor())) {
                return player;
            }
        }
        return null;
    }

    public void movePiece(String inputMovablePiece, String inputTargetPosition) {
        Position findPosition = findPositionByInputPoint(inputMovablePiece);
        Position targetPosition = Position.from(inputTargetPosition);
        validatePosition(inputMovablePiece);
        move(findPosition, targetPosition);
        changeTurn();
    }


    private void move(Position sourcePosition, Position targetPosition) {
        validateAlreadyExistPieceMovingRoute(sourcePosition, targetPosition);

        Player findPlayer = findPlayerByPosition(sourcePosition);
        Position changedPosition = findPlayer.movePieceByInput(getAllPosition(), sourcePosition, targetPosition);

        Player anotherPlayer = getAnotherPlayer(findPlayer);
        anotherPlayer.removePiece(changedPosition);
    }

    private void changeTurn() {
        if (current.isWhite()) {
            current = Color.BLACK;
            return;
        }
        current = Color.WHITE;
    }

    private void validatePosition(String inputMovablePiece) {
        Position findPosition = findPositionByInputPoint(inputMovablePiece);
        Player findPlayer = findPlayerByPosition(findPosition);
        if (!current.equals(findPlayer.getColor())) {
            throw new IllegalArgumentException("해당 플레이어의 차례가 아닙니다.");
        }
    }

    public Player getWhitePlayer() {
        return players.get(0);
    }

    public Player getBlackPlayer() {
        return players.get(1);
    }

    @Override
    public String toString() {
        return "Players{" +
                "players=" + players +
                '}';
    }
}
