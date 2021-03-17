package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.player.Player;
import chess.exception.NoSuchPermittedChessPieceException;

import java.util.*;

public class Board {
    private static final int ROW = 8;
    private static final int COLUMN = 8;

    private final List<Piece> pieces;

    public Board(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void movePiece(Player player, Position source, Position target) {
        validateControllablePiece(player, source);

        Piece sourcePiece = pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .findAny()
                .orElseThrow(NoSuchPermittedChessPieceException::new);

        sourcePiece.move(target, this);
    }

    private void validateControllablePiece(final Player player, final Position source) {
        Optional<Piece> sourcePiece = pieces.stream()
                .filter(piece -> piece.isSamePosition(source))
                .findAny();

        if (!(sourcePiece.isPresent() && sourcePiece.get().getColor() == player.getColor())) {
            throw new NoSuchPermittedChessPieceException();
        }
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
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
