package chess;

import chess.piece.King;
import chess.piece.Piece;
import chess.piece.PieceType;
import java.util.List;
import java.util.Optional;

public class ChessBoard {

    private final List<Piece> pieces;

    public ChessBoard(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void movePiece(Position startPosition, Position endPosition, Color turn){
        Piece startPiece = pieces.stream().filter(piece -> piece.isSamePosition(startPosition) && piece.isSameColor(turn))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 자리에는 말이 없거나 상대 말입니다."));
        boolean canMove = startPiece.canMove(startPosition, findAlivePiece());

        if(!canMove){
            throw new IllegalArgumentException("해당 자리에 이동할 수 없습니다.");
        }
        Optional<Piece> endPiece = pieces.stream()
                .filter(piece -> piece.isSamePosition(endPosition) && piece.isAnotherTeam(startPiece))
                .findAny();
        endPiece.ifPresent(Piece::changePieceToDead);
        startPiece.moveTo(endPosition,findAlivePiece());
    }

    public boolean isKingDead(){
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePieceType(PieceType.KING) && !piece.isAlive());
    }

    public Color findWinColor(){
        Optional<Piece> deadKing = pieces.stream()
                .filter(piece -> piece.isSamePieceType(PieceType.KING) && !piece.isAlive())
                .findAny();
        if(deadKing.isEmpty()){
            throw new IllegalArgumentException("게임이 아직 안끝났습니다.");
        }
        return deadKing.get().getColor().opposite();
    }

    private List<Piece> findAlivePiece(){
        return pieces.stream()
                .filter(Piece::isAlive)
                .toList();
    }
}
