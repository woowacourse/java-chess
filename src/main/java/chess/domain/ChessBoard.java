package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChessBoard {

    private static final int FIRST_KINGS_NUMBER = 2;
    private Map<Square, Piece> chessBoard = new HashMap<>();
    private Player player;
    private MoveState moveState;
    private Turn turn;

    public ChessBoard(String id, Color color) {
        player = new Player(id);
        moveState = new MoveState(player);
        for (File file : File.values()) {
            InitializingChessBoard.initPiecesLocation(file, chessBoard);
        }
        this.turn = new Turn(color);
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }

    boolean canMove(Square beforeSquare, Square afterSquare) {
        Piece beforePiece = chessBoard.get(beforeSquare);
        if (!chessBoard.containsKey(beforeSquare)) {
            throw new IllegalArgumentException("말이 없는 칸을 선택했습니다.");
        }
        if (!beforePiece.getColor().equals(turn.getTurn())) {
            throw new IllegalArgumentException("차례가 아닙니다.");
        }
        if (!beforePiece.findMovable(beforeSquare, chessBoard).contains(afterSquare)) {
            throw new IllegalArgumentException("이동할 수 없는 칸입니다.");
        }
        turn.changeTurn(turn.getTurn().getName());
        return true;
    }


    public boolean movePiece(Square sourceSquare, Square targetSquare) {
        if (!canMove(sourceSquare, targetSquare)) {
            return false;
        }
        Piece currentPiece = chessBoard.remove(sourceSquare);
        chessBoard.put(targetSquare, currentPiece);
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

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.equals(player, that.player) &&
                Objects.equals(turn, that.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, turn);
    }
}
