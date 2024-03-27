package domain.board;

import domain.board.position.Position;
import domain.board.position.Vector;
import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Board {
    private final Map<Position, Piece> squares;
    private Color currentTurnColor;

    public Board(final Map<Position, Piece> squares) {
        this.currentTurnColor = Color.WHITE;
        this.squares = squares;
    }

    public void move(final String source, final String target) {
        move(Position.from(source), Position.from(target));
    }

    public void move(final Position source, final Position target) {
        validateMovement(source, target);
        updateBoard(source, target);
        if (isKingDead()) {
            return;
        }
        switchTurn();
    }

    public double calculateScore(final Color color) {
        final List<Piece> list = squares.values().stream().filter(r -> r.hasColor(color)).toList();
        double sum = list.stream().map(Piece::getScore).mapToDouble(r -> r).sum();
        if (isPawnOnSameFile(color)) {
            sum -= list.stream().filter(Piece::isPawn).count() / 2.0;
        }
        return sum;
    }

    private boolean isPawnOnSameFile(final Color color) {
        final List<Integer> pawnsFile = squares.entrySet()
                .stream()
                .filter(r -> r.getValue().hasColor(color))
                .filter(r -> r.getValue().isPawn())
                .map(r -> r.getKey().toFileIndex())
                .toList();

        return Set.copyOf(pawnsFile).size() != pawnsFile.size();
    }

    private void validateMovement(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        final Piece targetPiece = squares.get(target);
        final Vector vector = new Vector(source, target);

        validateEmptiness(currentPiece);
        validateCurrentTurn(currentPiece);
        validateReachability(vector, currentPiece, targetPiece);
        validateNoPieceOnPath(source, vector);
    }

    private void validateEmptiness(final Piece currentPiece) {
        if (currentPiece.isEmpty()) {
            throw new IllegalArgumentException("이동할 말이 선택되지 않았습니다");
        }
    }

    private void validateNoPieceOnPath(final Position source, final Vector vector) {
        if (isPiecesPossiblyExistOnPath(vector)) {
            validateNoPieceExistInPath(vector, source);
        }
    }

    private boolean isPiecesPossiblyExistOnPath(final Vector vector) {
        return vector.hasAbsoluteValueMoreOrEqualThan(2) && vector.isStraightOrDiagonal();
    }

    private void validateReachability(final Vector vector, final Piece currentPiece, final Piece targetPiece) {
        if (!currentPiece.isReachable(vector, targetPiece)) {
            throw new IllegalArgumentException("해당 말로 해당 위치를 갈 수 없습니다");
        }
    }

    private void validateNoPieceExistInPath(final Vector vector, final Position source) {
        final List<Position> path = vector.generatePathExcludingEndpoints(source);
        final boolean isPieceExistInPath = !path.stream().map(squares::get).allMatch(Piece::isEmpty);

        if (isPieceExistInPath) {
            throw new IllegalArgumentException("이동 경로에 다른 말이 놓져 있습니다");
        }
    }

    private void validateCurrentTurn(final Piece currentPiece) {
        if (!currentPiece.hasColor(currentTurnColor)) {
            throw new IllegalArgumentException(
                    String.format("현재 차례: %s, 현재 차례의 말만 움직일 수 있습니다", currentTurnColor.name()));
        }
    }

    private void updateBoard(final Position source, final Position target) {
        squares.put(target, squares.get(source).move());
        squares.put(source, Empty.INSTANCE);
    }

    public boolean isKingDead() {
        return squares.values().stream().filter(Piece::isKing).count() != 2;
    }

    private void switchTurn() {
        currentTurnColor = currentTurnColor.reverse();
    }

    public Color getColor() {
        return this.currentTurnColor;
    }

    public Map<Position, Piece> getSquares() {
        return Collections.unmodifiableMap(squares);
    }

    public void move(final String s1, final String s2, final String t1, final String t2) {
        move(Position.of(s1, s2), Position.of(t1, t2));
    }

    public Piece getPiece(final String source) {
        return squares.get(Position.from(source));
    }
}
