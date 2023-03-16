package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPoints;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessBoard {

    private final List<Piece> pieces;

    private ChessBoard(final List<Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard create() {
        return new ChessBoard(ChessBoardFactory.create());
    }

    public void movePiece(final Turn turn, final PiecePosition source, final PiecePosition destination) {
        final Piece piece = get(source);
        if (turn.incorrect(piece.color())) {
            throw new IllegalArgumentException("상대 말 선택");
        }

        final WayPoints wayPoints = piece.wayPointsWithCondition(destination);

        if (wayPoints.wayPoints().stream().anyMatch(it -> optGet(it).isPresent())) {
            throw new IllegalArgumentException("경로 상에 말이 있어서 이동할 수 없습니다.");
        }
        final Optional<Piece> to = optGet(destination);
        if (to.isPresent()) {
            final Piece piece2 = to.get();
            piece.moveAndKill(piece2);
            pieces.remove(piece2);
        } else {
            piece.move(destination);
        }

        turn.change();
    }

    private Optional<Piece> optGet(final PiecePosition piecePosition) {
        return pieces.stream()
                .filter(piece -> piece.existIn(piecePosition))
                .findAny();
    }

    public Piece get(final PiecePosition piecePosition) {
        return pieces.stream()
                .filter(piece -> piece.existIn(piecePosition))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 존재하는 피스가 없습니다."));
    }

    public List<Piece> pieces() {
        return pieces.stream().map(Piece::clone)
                .collect(Collectors.toList());
    }
}
