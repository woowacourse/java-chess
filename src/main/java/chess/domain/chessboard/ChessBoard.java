package chess.domain.chessboard;

import chess.domain.Position;
import chess.domain.chessPiece.Piece;
import chess.domain.chessPiece.factory.PieceBundleFactory;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;

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

    public void movePiece(Position source, Position target) {
        Piece movingPiece = findPieceByPosition(source);
        Piece targetPiece = findPieceByPosition(target);
        if (targetPiece != null) {
            validSameTeam(movingPiece, targetPiece);
            removePiece(targetPiece);
        }
        movingPiece.isMovable(target);
        movingPiece.move(target);
    }

    private void removePiece(Piece targetPiece) {
        if (blackTeam.contains(targetPiece)) {
            blackTeam.remove(targetPiece);
            return;
        }
        whiteTeam.remove(targetPiece);
    }

    private void validSameTeam(Piece movingPiece, Piece targetPiece) {
        if (movingPiece.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("같은팀이 있는 칸으로 이동할수 없습니다.");
        }
    }
}
