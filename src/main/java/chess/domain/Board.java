package chess.domain;

import chess.ChessInitializer;
import chess.domain.piece.Column;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.Row;
import java.util.List;
import java.util.Set;

public class Board {

    private final Pieces pieces;
    private final Turn turn;

    public Board() {
        this.pieces = new Pieces(ChessInitializer.initialize());
        this.turn = new Turn();
    }

    public void movePiece(final List<String> inputMovePiece, final List<String> inputMovePosition) {
        Position wantMovePiecePosition = new Position(Row.findRowByNumber(inputMovePiece.get(1)),
                Column.findColumnByAlphabet(inputMovePiece.get(0)));
        Position wantMovePosition = new Position(Row.findRowByNumber(inputMovePosition.get(1)),
                Column.findColumnByAlphabet(inputMovePosition.get(0)));
        Piece piece = pieces.findPieceByPositionAndColor(wantMovePiecePosition, turn.getTurn());

        pieces.validateMove(piece, wantMovePosition);
        pieces.move(piece, wantMovePosition);
    }

    public Set<Piece> getPieces() {
        return pieces.getPieces();
    }

    public Turn getTurn() {
        return turn;
    }

    public void changeTurn() {
        turn.changeTurn();
    }

}
