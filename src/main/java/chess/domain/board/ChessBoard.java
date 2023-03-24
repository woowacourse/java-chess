package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.exception.PieceCannotMoveException;

import java.math.BigDecimal;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> piecePosition;

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = piecePosition;
    }

    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = PieceFactory.createPiece();
        return new ChessBoard(piecePosition);
    }

    public static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    public Piece movePiece(final Position from, final Position to) {
        Piece fromPiece = piecePosition.get(from);
        Piece toPiece = piecePosition.get(to);

        validateMovable(from, to);
        validateAlly(fromPiece, toPiece);
        validateRoute(from, to);

        move(from, to);
        return toPiece;
    }

    private void move(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        piecePosition.put(from, new EmptyPiece());
        piecePosition.put(to, fromPiece);
    }

    private void validateMovable(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.get(from);
        if (!fromPiece.isMovable(from, to)) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }
    }

    private void validateAlly(final Piece fromPiece, final Piece toPiece) {
        if (fromPiece.getTeam() == toPiece.getTeam()) {
            throw new IllegalArgumentException("도착지에 동일한 팀의 말이 존재합니다");
        }
    }

    private void validateRoute(final Position from, final Position to) {
        RouteFinder.findRoute(from, to).stream()
                .filter(position -> !piecePosition.get(position).isEmpty())
                .forEach(position -> {
                    throw new IllegalArgumentException("이동하려는 경로에 말이 존재합니다.");
                });
    }

    public BigDecimal calculateScore(final Team team) {

        // TODO : 폰일 경우 동일한 Rank에 같은 색의 폰이 있는 경우 0.5점으로 치환하는 메소드 생성.
        return piecePosition.entrySet().stream()
                .filter(entry -> entry.getValue().getTeam() == team)
                .map(entry -> entry.getValue().getType().getScore())
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new IllegalStateException(team + "은 점수를 계산할 수 없습니다."));
    }

    public Map<Position, Piece> getPiecePosition() {
        return Map.copyOf(piecePosition);
    }

    public Piece get(final Position from) {
        return piecePosition.get(from);
    }
}
