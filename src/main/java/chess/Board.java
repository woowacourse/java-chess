package chess;

import chess.piece.*;
import chess.position.Direction;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.*;
import java.util.stream.Collectors;

import static chess.piece.Team.BLACK;
import static chess.piece.Team.WHITE;
import static chess.position.File.*;
import static chess.position.Rank.*;

public class Board {
    private static final int MINIMUM_COUNT_OF_LOWER_SCORE_PAWN = 2;
    private final Map<Position, Piece> pieces;
    private Team turn;
    private boolean isFinished;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
        this.turn = Team.WHITE;
        this.isFinished = false;
    }

    public static Board createInitialBoard() {
        return new Board(getInitialPieces());
    }

    private static Map<Position, Piece> getInitialPieces() {
        Map<Position, Piece> pieces = new HashMap<>();

        pieces.put(Position.of(A, EIGHT), new Rook(BLACK));
        pieces.put(Position.of(B, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(C, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(D, EIGHT), new Queen(BLACK));
        pieces.put(Position.of(E, EIGHT), new King(BLACK));
        pieces.put(Position.of(F, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(G, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(H, EIGHT), new Rook(BLACK));

        pieces.put(Position.of(A, ONE), new Rook(WHITE));
        pieces.put(Position.of(B, ONE), new Knight(WHITE));
        pieces.put(Position.of(C, ONE), new Bishop(WHITE));
        pieces.put(Position.of(D, ONE), new Queen(WHITE));
        pieces.put(Position.of(E, ONE), new King(WHITE));
        pieces.put(Position.of(F, ONE), new Bishop(WHITE));
        pieces.put(Position.of(G, ONE), new Knight(WHITE));
        pieces.put(Position.of(H, ONE), new Rook(WHITE));

        for (File file : File.valuesExceptNone()) {
            pieces.put(Position.of(file, SEVEN), new Pawn(BLACK));
            pieces.put(Position.of(file, TWO), new Pawn(WHITE));
            Rank.valuesRangeClosed(THREE, SIX)
                    .forEach(rank -> pieces.put(Position.of(file, rank), new EmptyPiece()));
        }
        return pieces;
    }

    public void moveIfPossible(Position source, Position target) {
        Piece pieceToBeMoved = pieces.get(source);
        if (pieceToBeMoved.isEmpty()) {
            throw new IllegalArgumentException("이동시키고자 하는 말이 존재하지 않습니다.");
        }
        pieceToBeMoved.throwExceptionIfNotMovable(this, source, target);
        finishIfKilledEnemyKing(target);
        move(source, target);
        pieceToBeMoved.updateHasMoved();
        this.turn = turn.getOppositeTeam();
    }

    private boolean isMovableWithoutConsideringKingCouldBeKilledNextTurn(Position source, Position target) {
        try {
            pieces.get(source).throwExceptionIfNotMovableWithoutConsideringKingCouldBeKilledNextTurn(this, source, target);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean isKilledIfMoves(Position source, Position target) {
        Board copiedBoard = copy();
        copiedBoard.move(source, target);
        return copiedBoard.allMovablePositionsOf(turn.getOppositeTeam()).contains(target);
    }

    public void move(Position source, Position target) {
        Piece piece = pieces.get(source);
        pieces.put(source, new EmptyPiece());
        pieces.put(target, piece);
    }

    private Set<Position> allMovablePositionsOf(Team team) {
        Set<Position> positions = new HashSet<>();
        for (Position position : positionsOf(team)) {
            positions.addAll(getMovablePositionsOf(position));
        }
        return positions;
    }

    private List<Position> positionsOf(Team team) {
        return pieces.keySet().stream()
                .filter(position -> isExistAt(position) && pieces.get(position).isSameTeam(turn))
                .collect(Collectors.toList());
    }

    private List<Position> getMovablePositionsOf(Position position) {
        return pieces.keySet().stream()
                .filter(target -> isMovableWithoutConsideringKingCouldBeKilledNextTurn(position, target))
                .collect(Collectors.toList());
    }

    private void finishIfKilledEnemyKing(Position target) {
        if (isExistAt(target) && isEnemyKing(target)) {
            this.isFinished = true;
        }
    }

    private boolean isEnemyKing(Position position) {
        return getTeamOf(position).isNotSame(this.turn) && pieces.get(position) instanceof King;
    }


    public boolean isExistAt(Position position) {
        return !pieces.get(position).isEmpty();
    }

    public boolean isExistAnyPieceAt(List<Position> traces) {
        return traces.stream()
                .anyMatch(this::isExistAt);
    }

    public boolean isKingKilledIfMoves(Position source, Position target) {
        Board copiedBoard = copy();
        copiedBoard.move(source, target);
        return copiedBoard.allMovablePositionsOf(turn.getOppositeTeam()).contains(findPositionOfKing(turn));
    }

    private Position findPositionOfKing(Team turn) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position) instanceof King && pieces.get(position).isSameTeam(turn))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public Board copy() {
        Map<Position, Piece> copiedPieces = new HashMap<>();
        for (Position position : pieces.keySet()) {
            copiedPieces.put(position, pieces.get(position));
        }
        return new Board(copiedPieces);
    }

    public int forwardMoveAmountOfRank(Position source, Position target) {
        int increaseAmountOfRank = source.increaseAmountOfRank(target);
        return isWhite(source) ? increaseAmountOfRank : -1 * increaseAmountOfRank;
    }

    private boolean isFrontLeft(Position source, Position target) {
        return target == frontLeftOf(source);
    }

    private boolean isFrontRight(Position source, Position target) {
        return target == frontRightOf(source);
    }

    private Position frontLeftOf(Position position) {
        if (turn.isWhite()) {
            return position.at(Direction.NORTH_WEST);
        }
        return position.at(Direction.SOUTH_EAST);
    }

    private Position frontRightOf(Position position) {
        if (turn.isWhite()) {
            return position.at(Direction.NORTH_EAST);
        }
        return position.at(Direction.SOUTH_WEST);
    }

    public Scores calculateScores() {
        return new Scores(this);
    }

    public double calculateScoreOf(Team team) {
        return Arrays.stream(File.valuesExceptNone())
                .mapToDouble(file -> scoreOfFile(file, team))
                .sum();
    }

    private double scoreOfFile(File file, Team team) {
        List<Piece> sameTeamPiecesInSameFile = Arrays.stream(Rank.valuesExceptNone())
                .map(rank -> getPiece(Position.of(file, rank)))
                .filter(piece -> isSameTeamBetween(team, piece))
                .collect(Collectors.toList());

        int countOfPawn = (int) sameTeamPiecesInSameFile.stream()
                .filter(Piece::isPawn)
                .count();

        double rawScoreOfFile = sameTeamPiecesInSameFile.stream()
                .mapToDouble(Piece::getScore)
                .sum();

        double scoreOfFile = rawScoreOfFile;
        if (countOfPawn >= MINIMUM_COUNT_OF_LOWER_SCORE_PAWN) {
            scoreOfFile = rawScoreOfFile - Pawn.getLowerScore() * countOfPawn;
        }
        return scoreOfFile;
    }

    public boolean hasMoved(Position position) {
        return pieces.get(position).getHasMoved();
    }

    public boolean isExistEnemyFrontLeft(Position source, Position target) {
        return isExistAt(target)
                && isFrontLeft(source, target)
                && isNotSameTeamBetween(source, target);
    }

    public boolean isExistEnemyFrontRight(Position source, Position target) {
        return isExistAt(target)
                && isFrontRight(source, target)
                && isNotSameTeamBetween(source, target);
    }

    public boolean isNotFinished() {
        return !this.isFinished;
    }

    public boolean isNotTurnOf(Position position) {
        return getTeamOf(position).isNotSame(this.turn);
    }

    private Team getTeamOf(Position position) {
        return pieces.get(position).getTeam();
    }

    public boolean isSameTeamBetween(Position position1, Position position2) {
        Piece piece1 = pieces.get(position1);
        Piece piece2 = pieces.get(position2);
        return piece1.isSameTeam(piece2);
    }

    public boolean isSameTeamBetween(Team team, Piece piece) {
        return piece.isSameTeam(team);
    }

    public boolean isNotSameTeamBetween(Position position1, Position position2) {
        return !isSameTeamBetween(position1, position2);
    }

    private boolean isWhite(Position position) {
        return pieces.get(position).isWhite();
    }

    public Map<Position, Piece> getPieces() {
        return this.pieces;
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }
}
