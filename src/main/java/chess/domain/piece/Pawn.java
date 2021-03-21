package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import chess.domain.position.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pawn extends Piece {
    private static final String SYMBOL = "Pp";

    private boolean isFirst = true;

    private Pawn(final String piece, final Position position, final Color color) {
        super(piece, position, color);
    }

    public static Pawn from(final String piece, final Position position) {
        validate(piece);
        if (isBlack(piece)) {
            return new Pawn(piece, position, Color.BLACK);
        }
        return new Pawn(piece, position, Color.WHITE);
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

    @Override
    public void move2(Target target, final Pieces basePieces, final Pieces targetPieces) {

    }

    private void checkTarget(Target target, List<Position> positions) {
        if (!positions.contains(target.getPosition())) {
            throw new IllegalArgumentException(String.format("이동할 수 없는 위치입니다. 입력 값: %s", target.getPosition()));
        }
    }

    private List<Position> makeRoutes(ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        if (isFirst) {
            positions.addAll(makeFirstUpRoutes(chessBoard));
            positions.addAll(makeUpRightRoutes(chessBoard));
            positions.addAll(makeUpLeftRoutes(chessBoard));
            this.isFirst = false;
            return positions;
        }
        positions.addAll(makeAfterFirstUpRoutes(chessBoard));
        positions.addAll(makeUpRightRoutes(chessBoard));
        positions.addAll(makeUpLeftRoutes(chessBoard));
        return positions;
    }

    private List<Position> makeFirstUpRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue();
        for (int index = 0; index < 2; index++) {
            Position nextPosition = Position.valueOf(rank + index, file);
            if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
                positions.add(nextPosition);
                continue;
            }
            break;
        }
        return positions;
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

    private List<Position> makeAfterFirstUpRoutes(final ChessBoard chessBoard) {
        List<Position> positions = new ArrayList<>();
        Position position = getPosition();
        int rank = position.getRank().getValue() + 1;
        int file = position.getFile().getValue();
        for (int index = 0; index < 1; index++) {
            Position nextPosition = Position.valueOf(rank + index, file);
            if (Objects.isNull(chessBoard.findPiece(nextPosition))) {
                positions.add(nextPosition);
                continue;
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
