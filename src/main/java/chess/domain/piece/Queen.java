package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Queen extends Piece {
    private static final String SYMBOL = "Qq";

    private Queen(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Queen from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Queen(piece, position, Color.BLACK);
        }
        return new Queen(piece, position, Color.WHITE);
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
        positions.addAll(makeUpLeftRoutes(chessBoard));
        positions.addAll(makeUpRightRoutes(chessBoard));
        positions.addAll(makeDownLeftRoutes(chessBoard));
        positions.addAll(makeDownRightRoutes(chessBoard));
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

    private List<Position> makeDownRightRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file++ + 1);
            if (file > 7) {
                break;
            }
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

    private List<Position> makeDownLeftRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file-- - 1);
            if (file < 1) {
                break;
            }
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

    private List<Position> makeUpRightRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file++ + 1);
            if (file > 7) {
                break;
            }
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

    private List<Position> makeUpLeftRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file-- - 1);
            if (file < 1) {
                break;
            }
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
