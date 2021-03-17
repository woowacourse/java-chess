package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.exception.NoSuchPermittedChessPieceException;

import java.util.*;

public class Board {
    private static final int ROW = 8;
    private static final int COLUMN = 8;

    private final List<Piece> pieces;

    public Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void movePiece(Position source, Position target) {
        Piece sourcePiece = pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .findAny()
                .orElseThrow(NoSuchPermittedChessPieceException::new);

        sourcePiece.move(target, this);
    }

    @Override
    public String toString() { //todo: 출력 테스트용
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                final int finalI = i;
                final int finalJ = j;

                Optional<Piece> p = pieces.stream()
                        .filter(piece -> piece.isSamePosition(new Position(finalI, finalJ)))
                        .findAny();

                if (p.isPresent()) {
                    stringBuilder.append(p.get().getNotation());
                    continue;
                }

                stringBuilder.append(".");
            }
            stringBuilder.append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }
}
