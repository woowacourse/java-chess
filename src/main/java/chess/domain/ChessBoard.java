package chess.domain;

import chess.domain.piece.Role;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.math.BigInteger;
import java.util.List;

public class ChessBoard {

    private static final int NUMBER_OF_PLAYER = 2;
    private static final int NUMBER_OF_SQUARES = 64;

    private final List<Square> squares;
    private Turn turn;

    public ChessBoard(List<Square> squares, Turn turn) {
        validateHaveAllSquares(squares);
        this.squares = squares;
        this.turn = turn;
    }

    private void validateHaveAllSquares(List<Square> squares) {
        if (squares.size() != NUMBER_OF_SQUARES) {
            throw new IllegalStateException("체스판이 올바르게 만들어지지 않았습니다.");
        }
    }

    public void move(final Position source, final Position destination) {
        validateIsAllyPiece(source);
        validateNotExistAllyAt(destination);
        validateNotBlocked(source, destination);
        if (canAttack(source, destination) || canMove(source, destination)) {
            executeMove(source, destination);
            turn = turn.next();
            return;
        }
        throw new IllegalArgumentException("기물의 이동 범위 밖입니다.");
    }

    private void validateIsAllyPiece(final Position source) {
        if (findSquareByPosition(source).isSameTeam(turn.findCurrentEnemyTeam())) {
            throw new IllegalArgumentException("상대방의 기물은 이동시킬 수 없습니다.");
        }
    }

    private Square findSquareByPosition(final Position position) {
        return squares.stream().filter(square -> square.isSamePosition(position)).findFirst()
                .orElseThrow(() -> new IllegalStateException("칸이 초기화되지 않았습니다."));
    }

    private void validateNotExistAllyAt(final Position destination) {
        final Team team = turn.findCurrentTeam();
        if (isMyPiece(team, destination)) {
            throw new IllegalArgumentException("도착지에 아군 기물이 있습니다.");
        }
    }

    private boolean isMyPiece(final Team team, final Position destination) {
        return findSquareByPosition(destination).isSameTeam(team);
    }

    private void validateNotBlocked(final Position source, final Position destination) {
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);
        BigInteger gcd = BigInteger.valueOf(diffFile).gcd(BigInteger.valueOf(diffRank));
        int fileDirection = diffFile / gcd.intValue();
        int rankDirection = diffRank / gcd.intValue();
        Position tempPosition = source.move(rankDirection, fileDirection);
        while (!tempPosition.equals(destination)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(rankDirection, fileDirection);
        }
    }

    private void validateIsEmpty(final Position tempPosition) {
        if (!isEmptyAt(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private boolean isEmptyAt(final Position position) {
        return findSquareByPosition(position).isEmpty();
    }

    private boolean canAttack(final Position source, final Position destination) {
        final Square userSquare = findSquareByPosition(source);
        final Square targetSquare = findSquareByPosition(destination);
        return targetSquare.isSameTeam(turn.findCurrentEnemyTeam()) && userSquare.canAttack(destination);
    }

    private boolean canMove(final Position source, final Position destination) {
        return isEmptyAt(destination) && findSquareByPosition(source).canMove(source, destination);
    }

    private void executeMove(final Position source, final Position destination) {
        findSquareByPosition(source).moveTo(findSquareByPosition(destination));
    }

    public boolean isKingDead() {
        return squares.stream().filter(Square::isKing).count() < NUMBER_OF_PLAYER;
    }

    public double calculateScore(final Team team) {
        double heavyPiecesScore = calculateHeavyPiecesScore(team);
        double pawnScore = calculatePawnScore(team);
        return heavyPiecesScore + pawnScore;
    }

    private double calculateHeavyPiecesScore(final Team team) {
        return squares.stream().filter(square -> square.isSameTeam(team))
                .filter(square -> !square.hasSameRole(Role.PAWN)).mapToDouble(Square::getScore).sum();
    }

    private double calculatePawnScore(final Team team) {
        double pawnScore = 0;
        for (File file : File.values()) {
            pawnScore += calculatePawnScoreOfFile(file, team);
        }
        return pawnScore;
    }

    private double calculatePawnScoreOfFile(final File file, final Team team) {
        double pawnScore = squares.stream().filter(square -> square.isSameTeam(team))
                .filter(square -> square.isSameFile(file)).filter(square -> square.hasSameRole(Role.PAWN))
                .mapToDouble(Square::getScore).sum();
        if (pawnScore > 1) {
            return pawnScore / 2;
        }
        return pawnScore;
    }

    public Team previousTeam() {
        return turn.findCurrentEnemyTeam();
    }

    public Turn getTurn() {
        return turn;
    }

    public List<Square> getSquares() {
        return squares;
    }
}
