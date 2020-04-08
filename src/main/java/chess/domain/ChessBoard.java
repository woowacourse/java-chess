package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Type;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private static final int FIRST_KINGS_NUMBER = 2;
    private Map<Square, Piece> chessBoard = new HashMap<>();
    private MoveState moveState = new MoveState();
    private Turn turn;

    public ChessBoard() {
        for (File file : File.values()) {
            InitializingChessBoard.initPiecesLocation(file, chessBoard);
        }
        this.turn = new Turn();
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }

    boolean canMove(Square beforeSquare, Square afterSquare) {
        Piece beforePiece = chessBoard.get(beforeSquare);
        if (!chessBoard.containsKey(beforeSquare)) {
            throw new UnsupportedOperationException("말이 없는 칸을 선택했습니다.");
        }
        if (!beforePiece.getColor().equals(turn.getTurn())) {
            throw new UnsupportedOperationException("차례가 아닙니다.");
        }
        if (!beforePiece.findMovable(beforeSquare, chessBoard).contains(afterSquare)) {
            throw new UnsupportedOperationException("이동할 수 없는 칸입니다.");
        }
        turn.changeTurn();
        return true;
    }


    public boolean movePiece(Square sourceSquare, Square targetSquare) {
        if (!canMove(sourceSquare, targetSquare)) {
            return false;
        }
        Piece currentPiece = chessBoard.remove(sourceSquare);
        chessBoard.put(targetSquare, currentPiece);
        moveState.getMoveCount().setMoveCount();
        return true;
    }

    public boolean isKingCaptured() {
        return chessBoard.values().stream()
                .filter(piece -> piece.getLetter().equals(Type.KING.getName())
                        || piece.getLetter().equals(Type.KING.getName().toLowerCase()))
                .toArray().length != FIRST_KINGS_NUMBER;
    }

    public Turn getTurn() {
        return turn;
    }

    public MoveState getMoveState() {
        return moveState;
    }
}
