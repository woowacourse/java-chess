package chess.domain;

import chess.domain.chessPiece.*;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    private Map<Position, ChessPiece> chessBoard;
    private Color currentTurn = Color.WHITE;

    public ChessBoard() {
        chessBoard = new HashMap<>();
        init();
    }

    private void init() {
        for (Color value : Color.values()) {
            List<ChessPiece> pieces = List.of(
                    new King(value),
                    new Queen(value),
                    new Pawn(value),
                    new Rook(value),
                    new Bishop(value),
                    new Knight(value));

            for (ChessPiece chessPiece : pieces) {
                initByPiece(chessPiece);
            }
        }
    }

    private void move(Position from, Position to) {
        ChessPiece me = findPiece(from)
                .orElseThrow(() -> new IllegalArgumentException("기물이 존재하지 않습니다."));

        if (me instanceof Pawn) {
            movePawn(from, to, (Pawn) me);
            return;
        }

        me.canMove(from, to);

        // todo gullimdol exist?

        if (findPiece(to).isEmpty() || enemyExist(me, to)) {
            chessBoard.put(to, me);
            chessBoard.remove(from);
            currentTurn = currentTurn.toOpposite();
            return;
        }
    }

    private void movePawn(Position from, Position to, Pawn me) {
        if (from.isSameFile(to)) {
            me.canMove(from, to);
            if (findPiece(to).isEmpty()) {
                chessBoard.put(to, me);
                chessBoard.remove(from);
                currentTurn = currentTurn.toOpposite();
                return;
            }
        }

        if (!from.isSameFile(to)) {
            me.checkCrossMove(from, to);
            if (enemyExist(me, to)) {
                chessBoard.put(to, me);
                chessBoard.remove(from);
                currentTurn = currentTurn.toOpposite();
                return;
            }
        }
    }

    private void initByPiece(ChessPiece chessPiece) {
        if (chessPiece.isBlack()) {
            for (Position position : chessPiece.getInitBlackPosition()) {
                chessBoard.put(position, chessPiece);
            }
            return;
        }
        for (Position position : chessPiece.getInitWhitePosition()) {
            chessBoard.put(position, chessPiece);
        }
    }

    // move명령 받고 -> findPiece -> canMove -> (for findPiece) -> enemyExist -> move 한다음에 -> 턴 변환
    public int countPiece() {
        return chessBoard.size();
    }

    public Optional<ChessPiece> findPiece(Position position) {
        ChessPiece piece = chessBoard.get(position);
        if (piece == null) {
            return Optional.empty();
        }

        return Optional.of(piece);
    }

    public boolean enemyExist(ChessPiece me, Position to) {
        Optional<ChessPiece> possiblePiece = findPiece(to);
        if (possiblePiece.isEmpty()) {
            return false;
        }

        ChessPiece piece = possiblePiece.get();
        return !piece.isSameColor(me);
    }
}
