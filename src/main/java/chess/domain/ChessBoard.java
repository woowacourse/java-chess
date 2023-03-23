package chess.domain;

import chess.domain.piece.PieceType;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private static final int NUMBER_OF_PLAYER = 2;

    private final List<Square> squares;
    private Turn turn;

    ChessBoard(List<Square> squares) {
        this.squares = squares;
        this.turn = new Turn();
    }

    public void move(final Position startPosition, final Position endPosition) {
        validateAllyPiece(startPosition);
        validateNotExistAllyAt(endPosition);
        validateNotBlocked(startPosition, endPosition);
        if (canAttack(startPosition, endPosition) || canMove(startPosition, endPosition)) {
            executeMove(startPosition, endPosition);
        }
    }

    private void validateAllyPiece(final Position startPosition) {
        if (findSquareByPosition(startPosition).isSameTeam(turn.findCurrentEnemyTeam())) {
            throw new IllegalArgumentException("상대방의 기물은 이동시킬 수 없습니다.");
        }
    }

    private Square findSquareByPosition(final Position position) {
        return squares.stream()
            .filter(square -> square.isSamePosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("칸이 초기화되지 않았습니다."));
    }

    private void validateNotExistAllyAt(final Position endPosition) {
        final Team team = turn.findCurrentTeam();
        if (isMyPiece(team, endPosition)) {
            throw new IllegalArgumentException("도착지에 아군 기물이 있습니다.");
        }
    }

    private boolean isMyPiece(final Team team, final Position endPosition) {
        return findSquareByPosition(endPosition).isSameTeam(team);
    }

    private void validateNotBlocked(final Position startPosition, final Position endPosition) {
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);
        BigInteger gcd = BigInteger.valueOf(diffRank).gcd(BigInteger.valueOf(diffFile));
        int fileDirection = diffFile / gcd.intValue();
        int rankDirection = diffRank / gcd.intValue();
        Position tempPosition = startPosition.move(fileDirection, rankDirection);
        while (!tempPosition.equals(endPosition)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(fileDirection, rankDirection);
        }
    }

    private void validateIsEmpty(final Position tempPosition) {
        if (!isEmptyAt(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private boolean isEmptyAt(final Position position) {
        return findSquareByPosition(position)
            .isEmpty();
    }

    private boolean canAttack(final Position startPosition, final Position endPosition) {
        final Square userSquare = findSquareByPosition(startPosition);
        final Square targetSquare = findSquareByPosition(endPosition);
        return targetSquare.isSameTeam(turn.findCurrentEnemyTeam())
            && userSquare.canAttack(endPosition);
    }

    private boolean canMove(final Position startPosition, final Position endPosition) {
        return isEmptyAt(endPosition) &&
            findSquareByPosition(startPosition).canMove(startPosition, endPosition);
    }

    private void executeMove(final Position startPosition, final Position endPosition) {
        findSquareByPosition(startPosition).moveTo(turn, findSquareByPosition(endPosition));
        turn = turn.next();
    }

    public boolean isKingDead() {
        return squares.stream()
            .filter(Square::isKing)
            .count() < NUMBER_OF_PLAYER;
    }

    public Score calculateScoreByTeam(Team team) {
        return Arrays.stream(File.values())
            .map((file) -> calculateScoreByFileAndTeam(file, team))
            .reduce(Score.ZERO, Score::add);
    }

    private Score calculateScoreByFileAndTeam(File file, Team team) {
        Map<PieceType, Long> pieceCountBoard = squares.stream()
            .filter((square) -> square.isSameFileAndTeam(file, team))
            .collect(Collectors.groupingBy(Square::findPieceType, Collectors.counting()));

        return squares.stream()
            .filter((square) -> square.isSameFileAndTeam(file, team))
            .map((square) -> square.findPieceScore(pieceCountBoard))
            .reduce(Score.ZERO, Score::add);
    }

    public List<Square> getSquares() {
        return squares;
    }
}
