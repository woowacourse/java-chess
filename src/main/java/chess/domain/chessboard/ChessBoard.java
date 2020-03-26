package chess.domain.chessboard;

import chess.domain.Position;
import chess.domain.chessPiece.factory.PieceBundleFactory;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.movefactory.MoveFactory;
import chess.domain.movefactory.MoveType;

import java.util.Collections;
import java.util.List;

public class ChessBoard {
    private final List<Position> chessBoard;
    private final List<Piece> blackTeam;
    private final List<Piece> whiteTeam;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
        this.blackTeam = PieceBundleFactory.createPieceSet(new BlackTeam());
        this.whiteTeam = PieceBundleFactory.createPieceSet(new WhiteTeam());
    }

    public List<Position> getChessBoard() {
        return Collections.unmodifiableList(chessBoard);
    }


    public Piece findPieceByPosition(Position position) {
        Piece piece = blackTeam.stream()
                .filter(x -> x.isEqualPosition(position))
                .findAny()
                .orElse(null);
        if (piece == null) {
            return whiteTeam.stream()
                    .filter(x -> x.isEqualPosition(position))
                    .findAny()
                    .orElse(null);
        }
        return piece;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        MoveType moveType = MoveFactory.of(sourcePosition, targetPosition);
        Piece targetPiece = findPieceByPosition(targetPosition);
        Piece pieceToMove = findPieceByPosition(sourcePosition);

        validSameTeam(pieceToMove, targetPiece);
        validMovable(pieceToMove, moveType);

        if (targetPiece != null) {
            removePiece(targetPiece);
        }

        pieceToMove.move(moveType, this);
    }

    private void validMovable(Piece pieceToMove, MoveType moveType) {
        if (pieceToMove.isMovable(moveType)) {
            return;
        }
        throw new IllegalArgumentException("해당 말이 갈 수 없는 칸입니다.");
    }

    private void validSameTeam(Piece pieceToMove, Piece targetPiece) {
        if (pieceToMove.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("해당 칸에 같은 팀의 말이 존재 합니다.");
        }
    }

    private void removePiece(Piece targetPiece) {
        if (blackTeam.contains(targetPiece)) {
            blackTeam.remove(targetPiece);
            return;
        }
        whiteTeam.remove(targetPiece);
    }

    public boolean isExistPiece(Position position) {
        return findPieceByPosition(position) != null;
    }
}
