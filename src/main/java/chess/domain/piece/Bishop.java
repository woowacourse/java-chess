package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {
    private static final String SYMBOL = "Bb";

    private Bishop(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Bishop from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Bishop(piece, position, Color.BLACK);
        }
        return new Bishop(piece, position, Color.WHITE);
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
    public void move(Target target, ChessBoard chessBoard) {

    }

    @Override
    public void move2(final Target target, final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = makeRoutes2(basePieces, targetPieces);
        checkTarget(target, positions);
        basePieces.changePiecePosition(this, target);
        changePosition(target.getPosition());
    }

    private void checkTarget(Target target, List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeRoutes2(final Pieces basePieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(makeUpLeftRoutes2(basePieces, targetPieces));
        positions.addAll(makeUpRightRoutes2(basePieces, targetPieces));
        positions.addAll(makeDownLeftRoutes2(basePieces, targetPieces));
        positions.addAll(makeDownRightRoutes2(basePieces, targetPieces));
        return positions;
    }

    private List<Position> makeDownRightRoutes2(final Pieces pieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file++ + 1);
            if (file > 7) {
                break;
            }
            Optional<Piece> basePiece = pieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = pieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeDownLeftRoutes2(final Pieces pieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex > 0; rankIndex--) {
            Position nextPosition = Position.valueOf(rankIndex - 1, file-- - 1);
            if (file < 1) {
                break;
            }
            Optional<Piece> basePiece = pieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = pieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpRightRoutes2(final Pieces pieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file++ + 1);
            if (file > 7) {
                break;
            }
            Optional<Piece> basePiece = pieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = pieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
    }

    private List<Position> makeUpLeftRoutes2(final Pieces pieces, final Pieces targetPieces) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue();
        int file = position.getFile().getValue();
        for (int rankIndex = rank; rankIndex < 8; rankIndex++) {
            Position nextPosition = Position.valueOf(rankIndex + 1, file-- - 1);
            if (file < 1) {
                break;
            }
            Optional<Piece> basePiece = pieces.findPiece(nextPosition);
            Optional<Piece> targetPiece = pieces.findPiece(nextPosition);
            if (targetPiece.isPresent()) {
                positions.add(nextPosition);
                break;
            }
            if (!basePiece.isPresent()) {
                positions.add(nextPosition);
                continue;
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
}
