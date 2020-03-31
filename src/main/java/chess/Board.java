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
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                if (file.isNone() || rank.isNone()) {
                    continue;
                }
                pieces.put(Position.of(file, rank), null);
            }
        }

        pieces.put(Position.of(A, EIGHT), new Rook(BLACK));
        pieces.put(Position.of(B, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(C, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(D, EIGHT), new Queen(BLACK));
        pieces.put(Position.of(E, EIGHT), new King(BLACK));
        pieces.put(Position.of(F, EIGHT), new Bishop(BLACK));
        pieces.put(Position.of(G, EIGHT), new Knight(BLACK));
        pieces.put(Position.of(H, EIGHT), new Rook(BLACK));
        for (File value : File.values()) {
            if (value.isNone()) {
                continue;
            }
            pieces.put(Position.of(value, SEVEN), new Pawn(BLACK));
        }


        pieces.put(Position.of(A, ONE), new Rook(WHITE));
        pieces.put(Position.of(B, ONE), new Knight(WHITE));
        pieces.put(Position.of(C, ONE), new Bishop(WHITE));
        pieces.put(Position.of(D, ONE), new Queen(WHITE));
        pieces.put(Position.of(E, ONE), new King(WHITE));
        pieces.put(Position.of(F, ONE), new Bishop(WHITE));
        pieces.put(Position.of(G, ONE), new Knight(WHITE));
        pieces.put(Position.of(H, ONE), new Rook(WHITE));
        for (File value : File.values()) {
            if (value.isNone()) {
                continue;
            }
            pieces.put(Position.of(value, TWO), new Pawn(WHITE));
        }
        return pieces;
    }

    private boolean isMovable(Position source, Position target) {
        try {
            pieces.get(source).throwExceptionIfNotMovable(this, source, target);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

//    public boolean isNotCheckmate() {
//        return !isCheckmate();
//        Position positionOfKing = findPositionOfKing(turn);
//        Piece king = pieces.get(positionOfKing);
//        List<Position> movablePositionsRegardlessEnemyPieces
//                = king.getMovablePositionsRegardlessOtherPieces(positionOfKing);
//        movablePositionsRegardlessEnemyPieces.removeAll(positionsOf(turn));
//        for (Position destination : movablePositionsRegardlessEnemyPieces) {
//            if (isSurviveIfMoves(positionOfKing, destination)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private boolean isCheckmate() {
//        Position positionOfKing = findPositionOfKing(turn);
//        return allMovablePositionsOfOppositeTeamWhen(pieces).contains(positionOfKing)//상대팀이 킹에 도달조차 못하는 경우는 일단 걸러짐
//                && canNotKingAvoidHimselfWhenEnemiesCanReach(positionOfKing)//도달할수 있다면 킹이 직접 피할 수 없는기?
//                && canNotOurTeamProtectKingWhenKingCanNotAvoidWhereverKingMoves(positionOfKing);//킹이 어딜가든 못피하면 아군이 막을 수 없는가?
//    }
//
//    private boolean canNotKingAvoidHimselfWhenEnemiesCanReach(Position positionOfKing) {
//        List<Position> movablePositionsOfKing = getMovablePositions(pieces, pieces.get(positionOfKing));
//        return movablePositionsOfKing.stream()
//                .noneMatch(destination -> isMovable(pieces, positionOfKing, destination));
//    }
//
//    private boolean canNotOurTeamProtectKingWhenKingCanNotAvoidWhereverKingMoves(Position positionOfKing) {
//        return !canOurTeamProtectKingWhenKingCanNotAvoidWhereverKingMoves(positionOfKing);
//    }
//
//    private boolean canOurTeamProtectKingWhenKingCanNotAvoidWhereverKingMoves(Position positionOfKing) {
//        List<Position> positionsOfEnemies = positionsOf(turn.getOppositeTeam());
//        List<Position> positionsOfThreateningEnemies = positionsOfEnemies.stream()
//                .filter(positionOfEnemy -> isMovable(pieces, positionOfEnemy, positionOfKing))
//                .collect(Collectors.toList());
//        if (positionsOfThreateningEnemies.size() > 1) {
//            return false;
//        }
//        //TODO: AND 인지 OR 인지 확인
//        return canKillThreateningEnemy(positionsOfThreateningEnemies.get(0))
//                || canBlockPathOfThreateningEnemy(positionOfKing, positionsOfThreateningEnemies.get(0));
//    }
//
//    private boolean canKillThreateningEnemy(Position positionOfThreateningEnemy) {
//        List<Position> positionsOfOurTeam = positionsOf(turn);
//        return positionsOfOurTeam.stream()
//                .anyMatch(position -> isMovable(pieces, position, positionOfThreateningEnemy));
//    }
//
//    private boolean canBlockPathOfThreateningEnemy(Position positionOfKing, Position positionOfThreateningEnemy) {
//        List<Position> trace = Position.collectPositionsBetween(positionOfKing, positionOfThreateningEnemy);
//        return allMovablePositionsOfOurTeamWhen(pieces).stream()
//                .anyMatch(trace::contains);

    //    }

    private Position findPositionOfKing(Team turn) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position) instanceof King && pieces.get(position).isSameTeam(turn))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    private List<Position> positionsOf(Team team) {
//        List<Position> positions = new ArrayList<>();
//        for (Position position : pieces.keySet()) {
//            if (pieces.get(position).isSameTeam(team)) {
//                positions.add(position);
//            }
//        }
//        return positions;
        return pieces.keySet().stream()
                .filter(position -> isExistAt(position) && pieces.get(position).isSameTeam(turn))
                .collect(Collectors.toList());
    }

    private boolean isSurviveIfMoves(Position source, Position target) {
        return !isKilledIfMoves(source, target);
    }

    public boolean isKilledIfMoves(Position source, Position target) {
        Board copiedBoard = new Board(pieces);
        copiedBoard.move(source, target);
        return copiedBoard.allMovablePositionsOf(turn.getOppositeTeam()).contains(target);
    }

    public void move(Position source, Position target) {
        Piece piece = pieces.get(source);
        pieces.put(source, null);
        pieces.put(target, piece);
    }

    private Set<Position> allMovablePositionsOf(Team team) {
        return positionsOf(team).stream()
                .map(this::getMovablePositionsOf)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    private List<Position> getMovablePositionsOf(Position position) {
        return pieces.keySet().stream()
                .filter(target -> isMovable(position, target))
                .collect(Collectors.toList());
    }

    private Position findPositionByPiece(Piece piece) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position) == piece)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

//    public void move(Position from, Position to) {
//        Piece pieceToBeMoved = pieces.get(from);
//        List<Position> PositionsWherePiecesShouldNeverBeIncluded = pieceToBeMoved.movePathExceptSourceAndTarget(from, to);
//        if (isExistAnyPieceAt(PositionsWherePiecesShouldNeverBeIncluded)) {
//            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
//        }
//        if (isExistAt(to) && pieces.get(to).isSameTeam(pieces.get(from))) {
//            throw new IllegalArgumentException("본인의 말은 잡을 수 없습니다.");
//        }
//
//        Piece target = pieces.remove(from);
//        target.updateHasMoved();
//        Piece piece1 = pieces.get(to);
//        if (piece1 != null && pieceToBeMoved instanceof King) {
//            this.finished = true;
//        }
//        pieces.put(to, target);
//        this.turn = turn.getOppositeTeam();

//    }

    public void moveIfPossible(Position source, Position target) {
        Piece pieceToBeMoved = pieces.get(source);
        if (pieceToBeMoved == null) {
            throw new IllegalArgumentException("이동시키고자 하는 말이 존재하지 않습니다.");
        }
        pieceToBeMoved.throwExceptionIfNotMovable(this, source, target);
        finishIfKilledEnemyKing(target);
        move(source, target);
        pieceToBeMoved.updateHasMoved();
        this.turn = turn.getOppositeTeam();
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
        return pieces.get(position) != null;
    }

    public boolean isNotExistAt(Position position) {
        return !isExistAt(position);
    }

    public boolean isExistAnyPieceAt(List<Position> traces) {
        return traces.stream()
                .anyMatch(this::isExistAt);
    }

    public boolean isKingKilledIfMoves(Position source, Position target) {
        Board copiedBoard = new Board(pieces);
        copiedBoard.move(source, target);
        return copiedBoard.allMovablePositionsOf(turn.getOppositeTeam()).contains(findPositionOfKing(turn));
    }

    public Scores calculateScores() {
        //TODO: 점수계산
        return null;
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return this.pieces;
    }

    public Team getTurn() {
        return this.turn;
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

    public boolean isNotSameTeamBetween(Position position1, Position position2) {
        return !isSameTeamBetween(position1, position2);
    }

    private boolean isWhite(Position position) {
        return pieces.get(position).isWhite();
    }

    private boolean isBlack(Position position) {
        return pieces.get(position).isBlack();
    }

    public int forwardMoveAmountOfRank(Position source, Position target) {
        int increaseAmountOfRank = source.increaseAmountOfRank(target);
        return isWhite(source) ? increaseAmountOfRank : -1 * increaseAmountOfRank;
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

    public boolean hasMoved(Position position) {
        return pieces.get(position).getHasMoved();
    }
}
