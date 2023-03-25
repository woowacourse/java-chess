package chess.domain.board;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piecesfactory.PiecesFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Pieces pieces;

    private Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public static Board from(final PiecesFactory piecesFactory) {
        return new Board(new Pieces(piecesFactory.generate()));
    }

    public void move(final Color turnColor, final Position currentPosition, final Position targetPosition) {
        final Piece piece = findPiece(currentPosition);
        validateColor(turnColor, piece);
        validatePath(piece, targetPosition);
        processMoving(piece, targetPosition);
    }

    private Piece findPiece(final Position currentPosition) {
        return pieces.findPieceOrThrow(currentPosition);
    }

    private void validateColor(final Color turnColor, final Piece piece) {
        if (!piece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("해당 색의 말을 이동시킬 순서가 아닙니다.");
        }
    }

    private void validatePath(final Piece piece, final Position targetPosition) {
        final List<Position> pathPositions = piece.getPassingPositions(targetPosition);
        if (pieces.hasPiece(pathPositions)) {
            throw new IllegalArgumentException("이동 경로에 다른 말이 있습니다.");
        }
    }

    private void processMoving(final Piece piece, final Position targetPosition) {
        final Piece targetPiece = pieces.findPieceOrBlank(targetPosition);
        final Piece movedPiece = piece.move(targetPiece);

        pieces.remove(piece);
        pieces.remove(targetPiece);
        pieces.add(movedPiece);
    }

    public boolean hasPieces() {
        return !pieces.isEmpty();
    }

    public boolean hasTwoKings() {
        return pieces.hasTwoKings();
    }


    public Map<Color, Double> calculateScoreByColor() {
        final HashMap<Color, Double> scoreByColor = new HashMap<>();
        scoreByColor.put(Color.BLACK, pieces.calculateScore(Color.BLACK));
        scoreByColor.put(Color.WHITE, pieces.calculateScore(Color.WHITE));
        return scoreByColor;
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }
}
