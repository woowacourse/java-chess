package chess.domain;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final Player whitePlayer, final Player blackPlayer) {
        return new Players(List.of(whitePlayer, blackPlayer));
    }

    public void validateAlreadyExistPieceMovingRoute(
            final Position movablePiecePosition,
            final Position targetPosition
    ) {
        Position fromPosition = Position.from(movablePiecePosition.getRank(), movablePiecePosition.getFile());
        Position toPosition = Position.from(targetPosition.getRank(), targetPosition.getFile());

        int diffFile = toPosition.calculateFileDistance(fromPosition.getFile());
        int diffRank = toPosition.calculateRankDistance(fromPosition.getRank());

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
                .anyMatch(position -> position.isSame(file, rank));
    }

    public Position findPositionByInputPoint(String point) {
        Position foundPosition = Position.from(point);
        return getAllPosition().stream()
                .filter(position -> position.isSame(file, rank))
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


}
