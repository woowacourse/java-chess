package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;
import javafx.geometry.Pos;

import java.util.*;

public class King extends Piece {
    private static final String SYMBOL = "Kk";

    private King(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static King from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new King(piece, position, Color.BLACK);
        }
        return new King(piece, position, Color.WHITE);
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
        positions.addAll(makeUpRoutes(chessBoard));
        positions.addAll(makeDownRoutes(chessBoard));
        positions.addAll(makeLeftRoutes(chessBoard));
        positions.addAll(makeRightRoutes(chessBoard));
        positions.addAll(makeUpLeftRoutes(chessBoard));
        positions.addAll(makeUpRightRoutes(chessBoard));
        positions.addAll(makeDownLeftRoutes(chessBoard));
        positions.addAll(makeDownRightRoutes(chessBoard));
        return positions;
    }

    private List<Position> makeUpRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeDownRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeLeftRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank, file - 1);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeRightRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank, file + 1);
        if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
            return Collections.singletonList(nextPosition);
        }
        if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
            return Collections.singletonList(nextPosition);
        }
        return Collections.emptyList();
    }

    private List<Position> makeUpLeftRoutes(final ChessBoard chessBoard) {
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file - 1);
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
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank + 1, file + 1);
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
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file - 1);
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
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        Position nextPosition = Position.valueOf(rank - 1, file + 1);
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
