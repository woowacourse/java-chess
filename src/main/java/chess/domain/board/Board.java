package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.Position;

import java.util.List;

public class Board {
    private static final int ROW = 8;
    private static final int COLUMN = 8;

    private final Pieces pieces;

    public Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Board(final List<Piece> pieces) {
        this(new Pieces(pieces));
    }

    public void movePiece(Color color, Position source, Position target) {
        Piece sourcePiece = pieces.findPieceByPosition(color, source);

        sourcePiece.move(target, this);
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
    }

//    @Override
//    public String toString() { //todo: 출력 테스트용
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < ROW; i++) {
//            for (int j = 0; j < COLUMN; j++) {
//                final int finalI = i;
//                final int finalJ = j;
//
//                Optional<Piece> p = pieces.stream()
//                        .filter(piece -> piece.isSamePosition(new Position(finalI, finalJ)))
//                        .findAny();
//
//                if (p.isPresent()) {
//                    stringBuilder.append(p.get().getNotation());
//                    continue;
//                }
//
//                stringBuilder.append(".");
//            }
//            stringBuilder.append(System.lineSeparator());
//        }
//
//        return stringBuilder.toString();
//    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }

    public void catchPiece(final Color color) {
        pieces.catchPiece(color);
    }
}
