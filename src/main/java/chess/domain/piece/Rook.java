package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rook extends Piece {
    private static final String SYMBOL = "Rr";

    private Rook(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Rook from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Rook(piece, position, Color.BLACK);
        }
        return new Rook(piece, position, Color.WHITE);
    }

    private static void validate(final String piece) {
        if (!SYMBOL.contains(piece)) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
        if (piece.length() > 1) {
            throw new IllegalArgumentException(String.format("옳지 않은 기물입니다! 입력 값: %s", piece));
        }
    }

    @Override
    public void move(final Target target, final ChessBoard chessBoard) {
        List<Position> positions = makeRoutes(chessBoard);
        checkTarget(target, positions);
        chessBoard.changePiecePosition(this, target.getPosition());
        changePosition(target.getPosition());
    }

    private void checkTarget(Target target, List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(makeUpRoutes(chessBoard));
        positions.addAll(makeDownRoutes(chessBoard));
        positions.addAll(makeLeftRoutes(chessBoard));
        positions.addAll(makeRightRoutes(chessBoard));
        return positions;
    }

    private List<Position> makeRightRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = file; index < 8; index++) {
            Position nextPosition = Position.valueOf(rank, index + 1);
            if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
                positions.add(nextPosition);
                continue;
            }
            if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeLeftRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = file; index > 1; index--) {
            Position nextPosition = Position.valueOf(rank, index - 1);
            if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
                positions.add(nextPosition);
                continue;
            }
            if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeDownRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = rank; index > 1; index--) {
            Position nextPosition = Position.valueOf(index - 1, file);
            if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
                positions.add(nextPosition);
                continue;
            }
            if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int index = rank; index < 8; index++) {
            Position nextPosition = Position.valueOf(index + 1, file);
            if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
                positions.add(nextPosition);
                continue;
            }
            if (!chessBoard.findPiece(nextPosition).isSameColor(this)) {
                positions.add(nextPosition);
                break;
            }
            break;
        }
        return positions;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
