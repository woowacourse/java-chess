package chess.domain.chessboard;

import chess.domain.Position;
import chess.domain.chessPiece.factory.PieceBundleFactory;
import chess.domain.chessPiece.piece.King;
import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.movefactory.MoveType;
import chess.domain.movefactory.MoveTypeFactory;

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
        validSameTeam(sourcePosition, targetPosition);
        validMovable(sourcePosition, targetPosition);

        Piece targetPiece = findPieceByPosition(targetPosition);
        Piece pieceToMove = findPieceByPosition(sourcePosition);
        MoveType moveType = MoveTypeFactory.of(sourcePosition, targetPosition);

        if (targetPiece != null) {
            removePiece(targetPiece);
        }

        pieceToMove.move(moveType, this);
    }

    private void validMovable(Position sourcePosition, Position targetPosition) {
        MoveType moveType = MoveTypeFactory.of(sourcePosition, targetPosition);
        Piece targetPiece = findPieceByPosition(targetPosition);
        Piece pieceToMove = findPieceByPosition(sourcePosition);

        if (pieceToMove instanceof Pawn) {
            Pawn pawn = (Pawn) pieceToMove;
            if (pawn.isMovable(moveType, targetPiece)) {
                return;
            }
            throw new IllegalArgumentException("잘못된 움직임 입니다");
        }

        if (pieceToMove.isMovable(moveType)) {
            return;
        }
        throw new IllegalArgumentException("해당 말이 갈 수 없는 칸입니다.");
    }

    private void validSameTeam(Position sourcePosition, Position targetPosition) {
        Piece pieceToMove = findPieceByPosition(sourcePosition);
        Piece targetPiece = findPieceByPosition(targetPosition);

        if (targetPiece == null) {
            return;
        }
        if (pieceToMove.isSameTeam(targetPiece)) {
            throw new IllegalArgumentException("해당 칸에 같은 팀의 말이 존재 합니다.");
        }
    }

    public void removePiece(Piece targetPiece) {
        if (blackTeam.contains(targetPiece)) {
            blackTeam.remove(targetPiece);
            return;
        }
        whiteTeam.remove(targetPiece);
    }

    public boolean isExistPiece(Position position) {
        return findPieceByPosition(position) != null;
    }

    public boolean isSurviveKings() {
        return blackTeam.stream().anyMatch(x -> x instanceof King)
                && whiteTeam.stream().anyMatch(x -> x instanceof King);
    }
}
