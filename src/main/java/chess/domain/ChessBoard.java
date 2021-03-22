package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.player.BlackSet;
import chess.domain.player.PieceSet;
import chess.domain.player.Score;
import chess.domain.player.WhiteSet;
import chess.domain.position.AlphaColumn;
import chess.domain.position.NumberRow;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    public static final int BLACK_START_LINE = 7;
    public static final int BLACK_END_LINE = 8;
    public static final int WHITE_START_LINE = 1;
    public static final int WHITE_END_LINE = 2;
    private final Map<Position, Piece> chessBoard;
    private final PieceSet whitePieces;
    private final PieceSet blackPieces;
    private boolean gameStatus = true;

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
        initBoard();
        this.whitePieces = new WhiteSet();
        this.blackPieces = new BlackSet();
        setPieces();
    }

    private void initBoard() {
        for (Position position : Position.values()) {
            chessBoard.put(position, Blank.INSTANCE);
        }
    }

    private void setPieces() {
        setWhitePieces();
        setBlackPieces();
    }

    private void setBlackPieces() {
        Iterator<Piece> blacks = blackPieces.values();
        for (int i = BLACK_START_LINE; i <= BLACK_END_LINE; i++) {
            for (AlphaColumn alpha : AlphaColumn.values()) {
                chessBoard.put(Position.valueOf(alpha, NumberRow.valueOf(i)), blacks.next());
            }
        }
    }

    private void setWhitePieces() {
        Iterator<Piece> whites = whitePieces.values();
        for (int i = WHITE_START_LINE; i <= WHITE_END_LINE; i++) {
            for (AlphaColumn alpha : AlphaColumn.values()) {
                chessBoard.put(Position.valueOf(alpha, NumberRow.valueOf(i)), whites.next());
            }
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public boolean move(String source, String target) {
        validatePosition(source, target);
        Position start = Position.valueOf(source);
        Position end = Position.valueOf(target);
        Piece startPiece = chessBoard.get(start);
        Piece goingToDie = chessBoard.get(end);

        if (chessBoard.get(start).isMoveAble(end, this)) {
            //blank 경우
            if (chessBoard.get(end) == Blank.INSTANCE) {
                chessBoard.put(start, Blank.INSTANCE);
                chessBoard.put(end, startPiece);
                startPiece.setCurrentPosition(end);
                return true;
            }
            // 상대편이 있는 경우
            goingToDie.dead();
            chessBoard.put(end, startPiece);
            chessBoard.put(start, Blank.INSTANCE);
            startPiece.setCurrentPosition(end);
            if (goingToDie instanceof King) {
                gameStatus = false;
            }
            return true;
        }

        throw new IllegalArgumentException("잘못된 이동입니다.");
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
        return piece == Blank.INSTANCE;
    }


    public Piece getPiece(Position position) {
        return chessBoard.get(position);
    }

    public Result result() {
        Map<TeamColor, Score> result = new HashMap<>();
        result.put(TeamColor.BLACK, blackPieces.calculateScore());
        result.put(TeamColor.WHITE, whitePieces.calculateScore());

        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > 0) {
            return new Result(result, TeamColor.BLACK);
        }
        if (result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < 0) {
            return new Result(result, TeamColor.WHITE);
        }

        return new Result(result, TeamColor.NONE);
    }


    public boolean isPlaying() {
        return gameStatus;
    }

}

