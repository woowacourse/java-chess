package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Knight extends Piece {
    private static final String SYMBOL = "Nn";

    private Knight(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Knight from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Knight(piece, position, Color.BLACK);
        }
        return new Knight(piece, position, Color.WHITE);
    }

    @Override
    public void move(final Target target, final ChessBoard chessBoard) {
        List<Position> positions = makeRoutes(chessBoard);
        checkTarget(target, positions);
        chessBoard.changePiecePosition(this, target.getPosition());
        changePosition(target.getPosition());
    }

    @Override
    public void move2(Target target, Pieces pieces) {

    }

    private void checkTarget(Target target, List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeRoutes(ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(makeUpLeftRoutes(chessBoard));
        positions.addAll(makeUpRightRoutes(chessBoard));
        positions.addAll(makeDownRightRoutes(chessBoard));
        positions.addAll(makeDownLeftRoutes(chessBoard));
        positions.addAll(makeLeftUpRoutes(chessBoard));
        positions.addAll(makeLeftDownRoutes(chessBoard));
        positions.addAll(makeRightUpRoutes(chessBoard));
        positions.addAll(makeRightDownRoutes(chessBoard));
        return positions;
    }

    private List<Position> makeUpLeftRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 2;
        int file = position.getFile().getValue() - 1;
        if (rank > 8 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeUpRightRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 2;
        int file = position.getFile().getValue() + 1;
        if (rank > 8 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownRightRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 2;
        int file = position.getFile().getValue() + 1;
        if (rank < 1 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownLeftRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 2;
        int file = position.getFile().getValue() - 1;
        if (rank < 1 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeLeftUpRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue() - 2;
        if (rank > 8 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeLeftDownRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 1;
        int file = position.getFile().getValue() - 2;
        if (rank < 1 || file < 1) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeRightUpRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue() + 2;
        if (rank > 8 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeRightDownRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue() - 1;
        int file = position.getFile().getValue() + 2;
        if (rank < 1 || file > 8) {
            return Collections.emptyList();
        }
        Position nextPosition = Position.valueOf(rank, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private static void validate(final String piece) {
        if (!SYMBOL.contains(piece)) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
        if (piece.length() > 1) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
    }
}
