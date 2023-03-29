package chess.domain.board;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.exception.PieceCannotMoveException;

import java.util.Map;

import static chess.domain.piece.PieceType.EMPTY;
import static chess.domain.piece.PieceType.PAWN;

public class ChessBoard {
    
    private final PiecePosition piecePosition;

    private ChessBoard(final Map<Position, Piece> piecePosition) {
        this.piecePosition = new PiecePosition(piecePosition);
    }

    public static ChessBoard createBoard() {
        final Map<Position, Piece> piecePosition = PieceFactory.createPiece();
        return new ChessBoard(piecePosition);
    }

    public static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    public Piece movePiece(final Position from, final Position to) {
        Piece fromPiece = piecePosition.findPieceByPosition(from);
        Piece toPiece = piecePosition.findPieceByPosition(to);

        validateMovable(from, to);
        validateAlly(fromPiece, toPiece);
        validateRoute(from, to);

        move(from, to);
        return toPiece;
    }

    private void validateMovable(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.findPieceByPosition(from);
        if (!fromPiece.isMovable(from, to)) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }
        if (fromPiece.getType() == PAWN) {
            validatePawnMovable(from, to);
        }
    }

    private void validatePawnMovable(final Position from, final Position to) {
        final Piece fromPiece = piecePosition.findPieceByPosition(from);
        final Piece toPiece = piecePosition.findPieceByPosition(to);

        Team fromPieceTeam = fromPiece.getTeam();

        if (isDiagonal(from, to) && !fromPieceTeam.isEnemy(toPiece.getTeam())) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }

        if (!isDiagonal(from, to) && !toPiece.isSameType(EMPTY)) {
            throw new PieceCannotMoveException(fromPiece.getType());
        }
    }

    private boolean isDiagonal(final Position from, final Position to) {

        final int fileInterval = File.calculateInterval(from.getFile(), to.getFile());
        final int rankInterval = Rank.calculateInterval(from.getRank(), to.getRank());

        return fileInterval == rankInterval;
    }

    private void validateAlly(final Piece fromPiece, final Piece toPiece) {
        if (fromPiece.getTeam() == toPiece.getTeam()) {
            throw new IllegalArgumentException("도착지에 동일한 팀의 말이 존재합니다");
        }
    }

    private void validateRoute(final Position from, final Position to) {
        PiecePosition.findRoute(from, to).stream()
                .filter(position -> !piecePosition.findPieceByPosition(position).isSameType(EMPTY))
                .forEach(position -> {
                    throw new IllegalArgumentException("이동하려는 경로에 말이 존재합니다.");
                });
    }

    private void move(final Position from, final Position to) {
        piecePosition.replace(from, to);
    }

    public boolean isEnd() {
        return piecePosition.isKingAvailable();
    }

    public PiecePosition getPiecePosition() {
        return piecePosition;
    }

    public Piece get(final Position from) {
        return piecePosition.findPieceByPosition(from);
    }
}
