package chessgame.domain;

import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Pieces;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.dto.ChessBoardDto;
import chessgame.dto.RouteDto;
import chessgame.factory.ChessBoardGenerator;
import java.util.Set;

public class ChessBoard {
    private Pieces pieces;

    public ChessBoard() {
        this.pieces = new Pieces(Set.of());
    }

    public ChessBoard(final Pieces pieces) {
        this.pieces = pieces;
    }

    public void reset() {
        this.pieces = ChessBoardGenerator.createDefaultPieces();
    }

    public Piece findPieceByPoint(final Point point) {
        return this.pieces.findPieceWithPoint(point)
                .orElseThrow(() -> new IllegalArgumentException("해당 포인트에는 기물이 없습니다"));
    }

    public void move(final RouteDto dto) {
        validateGameProceed();
        final var startPoint = dto.getStartPoint();
        final var endPoint = dto.getEndPoint();
        final var piece = findPieceByPoint(startPoint);
        validateCanMove(piece, endPoint, startPoint);
        pieces.replace(piece, endPoint);
    }

    private void validateGameProceed() {
        if (pieces.isEmpty()) {
            throw new IllegalStateException("게임을 시작하고 기물 이동을 요청해주새요.");
        }
    }

    private void validateCanMove(Piece piece, Point endPoint, Point startPoint) {
        if (!pieces.canReplace(piece, endPoint)) {
            throw new IllegalArgumentException(
                    String.format("%s 는 %s 에서 %s로 이동할 수 없습니다.", piece.status(), startPoint, endPoint));
        }
    }

    public static ChessBoard createDefaultBoard() {
        return ChessBoardGenerator.createDefaultBoard();
    }

    public ChessBoardDto toDto() {
        return new ChessBoardDto(pieces.toDto());
    }
}
