package chess;

import chess.piece.*;
import chess.position.File;
import chess.position.Position;

import java.util.*;
import java.util.stream.Collectors;

import static chess.piece.Team.BLACK;
import static chess.piece.Team.WHITE;
import static chess.position.File.*;
import static chess.position.Rank.*;

public class Board {
    private final Map<Position, Piece> pieces;
    private Team turn;
    private boolean finished;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
        this.turn = Team.WHITE;
        this.finished = false;
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
        for (File value : File.values()) {
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
            pieces.put(Position.of(value, TWO), new Pawn(WHITE));
        }
        return pieces;
    }

    public boolean isNotCheckmate() {
        //TODO: 맨처음에 시작 안되는 버그 해결하기.
        Position positionOfKing = findPositionOfKing(turn);
        List<Position> movablePositionsRegardlessEnemyPieces
                = pieces.get(positionOfKing).getMovablePositionsRegardlessOtherPieces(positionOfKing);
        movablePositionsRegardlessEnemyPieces.removeAll(positionsOf(turn));
        for (Position destination : movablePositionsRegardlessEnemyPieces) {
            if (isSurviveIfMoves(positionOfKing, destination)) {
                return true;
            }
        }
        return false;
    }

    private Position findPositionOfKing(Team turn) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position) instanceof King && pieces.get(position).isSameTeam(turn))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    private List<Position> positionsOf(Team turn) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position).isSameTeam(turn))
                .collect(Collectors.toList());
    }

    private boolean isSurviveIfMoves(Position source, Position target) {
        Map<Position, Piece> copiedPieces = new HashMap<>(pieces);
        move(copiedPieces, source, target);
        if (allMovablePositionsOfOppositeTeamWhen(copiedPieces).contains(target)) {
            return false;
        }
        return true;
    }

    public void move(Map<Position, Piece> pieces, Position source, Position target) {
        Piece piece = pieces.remove(source);
        pieces.put(target, piece);
    }

    private Set<Position> allMovablePositionsOfOppositeTeamWhen(Map<Position, Piece> copiedPieces) {
        Set<Position> positions = new HashSet<>();
        Team oppositeTeamColor = turn.getOppositeTeam();
        //TODO:values()로 하는게 맞는지 keySet()으로 하는게 맞는지 확인하기
        for (Piece piece : copiedPieces.values()) {
            if (piece.isSameTeam(oppositeTeamColor)) {
                positions.addAll(getMovablePositions(copiedPieces, piece));
            }
        }
        return positions;
    }

    private List<Position> getMovablePositions(Map<Position, Piece> pieces, Piece piece) {
        List<Position> movablePositions = new ArrayList<>();
        Position source = findPositionByPiece(pieces, piece);
        List<Position> targets = piece.getMovablePositionsRegardlessOtherPieces(source);
        for (Position target : targets) {
            try {
                if (isMovable(pieces, source, target)) {
                    movablePositions.add(target);
                }
            } catch (IllegalArgumentException ignored) {
            }
        }
        return movablePositions;
    }

    private Position findPositionByPiece(Map<Position, Piece> pieces, Piece piece) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position) == piece)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    private boolean isMovable(Map<Position, Piece> pieces, Position source, Position target) {
        //TODO: Map에서 한번만꺼내기
        if (pieces.get(source) == null) {
            throw new IllegalArgumentException("이동시키고자 하는 말이 존재하지 않습니다.");
        }
        List<Position> PositionsWherePiecesShouldNeverBeIncluded = pieces.get(source).findTraceBetween(source, target);
        if (isExistAnyPieceAt(PositionsWherePiecesShouldNeverBeIncluded)) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
        if (isExistAt(target) && pieces.get(target).isSameTeam(pieces.get(source))) {
            throw new IllegalArgumentException("본인의 말은 잡을 수 없습니다.");
        }
        if (pieces.get(source) instanceof King) {
            if (isKilledIfMoves(source, target)) {
                throw new IllegalArgumentException("KING을 방어하세요.");
            }
        } else {
            if (allMovablePositionsOfOppositeTeamWhen(pieces).contains(findPositionOfKing(turn))) {
                throw new IllegalArgumentException("KING을 방어하세요.");
            }
        }
        return true;
    }

    public boolean isExistAnyPieceAt(List<Position> traces) {
        return traces.stream()
                .anyMatch(pieces::containsKey);
    }

    private boolean isExistAt(Position position) {
        return pieces.containsKey(position);
    }

    private boolean isKilledIfMoves(Position source, Position target) {
        return !isSurviveIfMoves(source, target);
    }


//    public void move(Position from, Position to) {
//        Piece pieceToBeMoved = pieces.get(from);
//        List<Position> PositionsWherePiecesShouldNeverBeIncluded = pieceToBeMoved.findTraceBetween(from, to);
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
        if (isMovable(pieces, source, target)) {
            Piece pieceToBeMoved = pieces.get(source);
            Piece pieceToBeKilled = pieces.get(target);
            move(pieces, source, target);
            pieceToBeMoved.updateHasMoved();
            if (pieceToBeKilled instanceof King) {
                this.finished = true;
            }
            this.turn = turn.getOppositeTeam();
        }
    }

    public Scores calculateScores() {
        //TODO:점수계산
        return null;
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return this.pieces;
    }
}
