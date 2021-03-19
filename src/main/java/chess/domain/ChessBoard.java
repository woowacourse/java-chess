package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
        initBoard();
    }

    private void initBoard() {

        Set<String> positionKeySet = Position.getPositionKeySet();

        Iterator<String> iter = positionKeySet.iterator();

        createUniqueLine(TeamColor.WHITE, iter);
        createPawnLine(TeamColor.WHITE, iter);
        createBlankLine(TeamColor.NONE, iter);
        createBlankLine(TeamColor.NONE, iter);
        createBlankLine(TeamColor.NONE, iter);
        createBlankLine(TeamColor.NONE, iter);
        createPawnLine(TeamColor.BLACK, iter);
        createUniqueLine(TeamColor.BLACK, iter);
    }

    private void createBlankLine(TeamColor none, Iterator<String> iter) {
        for (int i = 0; i < 8; i++) {
            chessBoard.put(Position.valueOf(iter.next()), Blank.INSTANCE);
        }
    }

    private void createPawnLine(TeamColor color, Iterator<String> iter) {
        for (int i = 0; i < 8; i++) {
            chessBoard.put(Position.valueOf(iter.next()), new Pawn(color));
        }
    }


    private void createUniqueLine(TeamColor color, Iterator<String> iter) {
        chessBoard.put(Position.valueOf(iter.next()), new Rook(color));
        chessBoard.put(Position.valueOf(iter.next()), new Knight(color));
        chessBoard.put(Position.valueOf(iter.next()), new Bishop(color));
        chessBoard.put(Position.valueOf(iter.next()), new Queen(color));
        chessBoard.put(Position.valueOf(iter.next()), new King(color));
        chessBoard.put(Position.valueOf(iter.next()), new Bishop(color));
        chessBoard.put(Position.valueOf(iter.next()), new Knight(color));
        chessBoard.put(Position.valueOf(iter.next()), new Rook(color));
    }


    public Map<Position, Piece> getChessBoard() {
//        return Collections.unmodifiableMap(chessBoard);
        return chessBoard;
    }

    public void move(String source, String target) {
        validatePosition(source, target);
        Position start = Position.valueOf(source);
        Position end = Position.valueOf(target);
        Piece startPiece = chessBoard.get(start);
        Piece goingToDie = chessBoard.get(end);

        if (chessBoard.get(start).isMoveAble(start, end, this)) {
            //blank 경우
            if (chessBoard.get(end) == Blank.INSTANCE) {
                chessBoard.put(start, Blank.INSTANCE);
                chessBoard.put(end, startPiece);
                return;
            }
            // 상대편이 있는 경우
            goingToDie.dead();
            chessBoard.put(end, startPiece);
            chessBoard.put(start, Blank.INSTANCE);
        }

    }

    private void validatePosition(String source, String target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("동일한 좌표는 불가능합니다.");
        }
        if (chessBoard.containsKey(Position.valueOf(source)) && chessBoard
            .containsKey(Position.valueOf(target))) {
            return;
        }
        throw new IllegalArgumentException("잘못된 좌표입니다.");
    }

    public boolean isBlank(Position position) {
        Piece piece = this.chessBoard.get(position);
        boolean ret = piece == Blank.INSTANCE;
        return ret;
    }


    public Piece getPiece(Position position) {
        return chessBoard.get(position);
    }
}

