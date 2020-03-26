package chess.domain.piece;

import chess.domain.board.Square;
import util.NullChecker;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Piece {

    private final Color color;
    private final Type type;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public String getLetter() {
        return color.getApplyTypeName(type);
    }

    public abstract Set<Square> getAllCheatSheet(Square square);

    public Set<Square> calculateMoveBoundary(Square square, Map<Square, Piece> board) {
        NullChecker.validateNotNull(square, board);

        if (type == Type.KNIGHT || type == Type.KING) {
            return getAllCheatSheet(square).stream()
                    .filter(s -> !(board.containsKey(s) && board.get(s).color == color))
                    .collect(Collectors.toSet());
        }

        if (type == Type.PAWN) {
            Set<Square> squares = getAllCheatSheet(square);
            for (Square s : squares) {
                if (Math.abs(square.getRank() - s.getRank()) == 1) {
                    Square squareRight = s.addIfInBoundary(-1, 0);
                    Square squareLeft = s.addIfInBoundary(1, 0);
                    if (board.containsKey(s) && color == board.get(s).color) {
                        squares.removeAll(getAllCheatSheet(square));
                    }
                    if (board.containsKey(squareRight) && color != board.get(squareRight).color) {
                        squares.add(squareRight);
                    }
                    if (board.containsKey(squareLeft) && color != board.get(squareLeft).color) {
                        squares.add(squareLeft);
                    }
                    continue;
                }
                if (board.containsKey(s) && color == board.get(s).color) {
                    squares.remove(s);
                }
            }
            return squares;
        }

        if (type == Type.QUEEN) {
            Set<Square> squares = getAllCheatSheet(square);
            Set<Square> squaresIter = getAllCheatSheet(square);
            for (Square s : squaresIter) {
                if (board.containsKey(s)) {
                    int fileDifference = s.getFile() - square.getFile();
                    int rankDifference = s.getRank() - square.getRank();
                    if (fileDifference == 0 && rankDifference > 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 0, 1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference > 0 && rankDifference == 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 1, 0);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference < 0 && rankDifference == 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, -1, 0);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference == 0 && rankDifference < 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 0, -1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference > 0 && rankDifference > 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 1, 1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference > 0 && rankDifference < 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 1, -1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference < 0 && rankDifference > 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, -1, 1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference < 0 && rankDifference < 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, -1, -1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (board.get(s).color == color) {
                        squares.remove(s);
                    }
                }
            }
            return squares;
        }

        if (type == Type.BISHOP) {
            Set<Square> squares = getAllCheatSheet(square);
            Set<Square> squaresIter = getAllCheatSheet(square);
            for (Square s : squaresIter) {
                if (board.containsKey(s)) {
                    int fileDifference = s.getFile() - square.getFile();
                    int rankDifference = s.getRank() - square.getRank();

                    if (fileDifference > 0 && rankDifference > 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 1, 1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference > 0 && rankDifference < 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 1, -1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference < 0 && rankDifference > 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, -1, 1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference < 0 && rankDifference < 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, -1, -1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (board.get(s).color == color) {
                        squares.remove(s);
                    }
                }
            }
            return squares;
        }

        if (type == Type.ROOK) {
            Set<Square> squares = getAllCheatSheet(square);
            Set<Square> squaresIter = getAllCheatSheet(square);
            for (Square s : squaresIter) {
                if (board.containsKey(s)) {
                    int fileDifference = s.getFile() - square.getFile();
                    int rankDifference = s.getRank() - square.getRank();
                    if (fileDifference == 0 && rankDifference > 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 0, 1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference > 0 && rankDifference == 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 1, 0);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference < 0 && rankDifference == 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, -1, 0);
                        squares.removeAll(squaresToRemove);
                    }

                    if (fileDifference == 0 && rankDifference < 0) {
                        Set<Square> squaresToRemove = findSquaresToRemove(s, 0, -1);
                        squares.removeAll(squaresToRemove);
                    }

                    if (board.get(s).color == color) {
                        squares.remove(s);
                    }
                }
            }
            return squares;
        }

        throw new IllegalArgumentException("no type");
    }

    private Set<Square> findSquaresToRemove(Square s, int fileAddAmount, int rankAddAmount) {
        Set<Square> squaresToRemove = new HashSet<>();
        int file = 0;
        int rank = 0;
        for (int i = 0; i < 8; i++, file += fileAddAmount, rank += rankAddAmount) {
            squaresToRemove.add(s.addIfInBoundary(file, rank));
        }
        squaresToRemove.remove(s);
        return squaresToRemove;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public double getScore() {
        return type.getScore();
    }
}