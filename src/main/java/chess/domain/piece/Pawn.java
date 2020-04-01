package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.exception.WrongPawnSquareScopeCalculationException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pawn extends Piece {
    private final static Map<Color, Pawn> CACHE = new HashMap<>();
    private final static String NAME_BLACK = "P";
    private final static String NAME_WHITE = "p";
    private final static double SCORE = 1;

    static {
        Stream.of(Color.values())
                .forEach(Pawn::putIntoCache);
    }

    private static void putIntoCache(Color color) {
        String name = putNameByColor(color);
        CACHE.putIfAbsent(color, new Pawn(color, name, SCORE));
    }

    private static String putNameByColor(Color color) {
        String name = NAME_BLACK;
        if (color == Color.WHITE) {
            name = NAME_WHITE;
        }
        return name;
    }

    public static Pawn of(Color color) {
        validateInput(color);
        return CACHE.get(color);
    }

    private static void validateInput(Color color) {
        if (Objects.isNull(color)) {
            throw new IllegalArgumentException("잘못된 입력입니다");
        }
    }

    private Pawn(Color color, String name, double score) {
        super(color, name, score);
    }

    @Override
    public Set<Square> calculateScope(Square square) {
        Objects.requireNonNull(square, "square은 필수입니다");
        if (color == Color.BLACK) {
            return calculateBlackScope(square);
        }
        return calculateWhiteScope(square);
    }

    private Set<Square> calculateWhiteScope(Square square) {
        Set<Square> scope = new HashSet<>();
        addToWhiteScopeIfInitialMove(square, scope);
        scope.addAll(Direction.whitePawnDirection()
                .stream()
                .map(direction -> square.movedSquareInBoundary(direction, 1))
                .filter(movedSquare -> isNotSameSquareItself(square, movedSquare))
                .collect(Collectors.toSet()));
        return scope;
    }

    private void addToWhiteScopeIfInitialMove(Square square, Set<Square> scope) {
        if (square.getRank() == Rank.of(2)) {
            scope.add(square.movedSquareInBoundary(Direction.N, 2));
            scope.remove(square);
        }
    }

    private Set<Square> calculateBlackScope(Square square) {
        Set<Square> scope = new HashSet<>();
        addToBlackScopeIfInitialMove(square, scope);
        scope.addAll(Direction.blackPawnDirection()
                .stream()
                .map(direction -> square.movedSquareInBoundary(direction, 1))
                .filter(movedSquare -> isNotSameSquareItself(square, movedSquare))
                .collect(Collectors.toSet()));
        return scope;
    }

    private void addToBlackScopeIfInitialMove(Square square, Set<Square> scope) {
        if (square.getRank() == Rank.of(7)) {
            scope.add(square.movedSquareInBoundary(Direction.S, 2));
            scope.remove(square);
        }
    }

    @Override
    public Set<Square> calculateMovableSquares(Square currentSquare, Map<Square, Piece> board) {
        Set<Square> currentSquareScope = calculateScope(currentSquare);

        Set<Square> movableSquares = currentSquareScope.stream()
                .filter(nextSquare -> hasPieceInDiagonalDirectionDistanceOne(currentSquare, board, nextSquare))
                .filter(nextSquare -> isDifferentColorWithPieceInDiagonalDirection(board, nextSquare))
                .collect(Collectors.toSet());

        List<Square> sameFileSquares = currentSquareScope.stream()
                .filter(currentSquare::isJustSameFile)
                .collect(Collectors.toList());

        calculatePawnFrontMove(board, movableSquares, sameFileSquares);
        return movableSquares;
    }

    private boolean hasPieceInDiagonalDirectionDistanceOne(Square currentSquare, Map<Square, Piece> board, Square nextSquare) {
        return currentSquare.isDiagonalDirectionDistanceOne(nextSquare) && board.containsKey(nextSquare);
    }

    private boolean isDifferentColorWithPieceInDiagonalDirection(Map<Square, Piece> board, Square nextSquare) {
        Piece pieceOfNextSquare = board.get(nextSquare);
        return pieceOfNextSquare.getColor() != this.color;
    }

    private void calculatePawnFrontMove(Map<Square, Piece> board, Set<Square> movableSquares, List<Square> sameFileSquares) {
        if (sameFileSquares.isEmpty()) {
            throw new WrongPawnSquareScopeCalculationException("Pawn이 갈 수 있는 범위를 잘못 계산하였습니다");
        }
        if (sameFileSquares.size() == 2) {
            Square firstSquare = sameFileSquares.get(0);
            Square secondSquare = sameFileSquares.get(1);
            if (!board.containsKey(firstSquare) && !board.containsKey(secondSquare)) {
                movableSquares.add(firstSquare);
                movableSquares.add(secondSquare);
            }
            if (!board.containsKey(firstSquare) && board.containsKey(secondSquare)) {
                movableSquares.add(firstSquare);
            }
            if (board.containsKey(firstSquare) && !board.containsKey(secondSquare)) {
                movableSquares.add(secondSquare);
            }
        }
        if (sameFileSquares.size() == 1) {
            Square firstSquare = sameFileSquares.get(0);
            if (!board.containsKey(firstSquare)) {
                movableSquares.add(firstSquare);
            }
        }
    }
}
