package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.result.Pieces;
import chess.domain.result.ResultDto;
import chess.domain.result.Score;
import chess.domain.state.TeamColor;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    public static final int MAX_NUM_PIECE = 16;
    public static final int ROW_WHITE_START = 1;
    public static final int ROW_WHITE_END = 2;
    public static final char COLUMN_FIRST = 'a';
    public static final char COLUMN_LAST = 'h';
    public static final int ROW_FIRST = 1;
    public static final int ROW_LAST = 8;
    public static final int ROW_BLACK_START = 7;
    public static final int ROW_BLACK_END = 8;
    public static final int ROW_WHITE_PAWN_LINE = 2;
    public static final int ROW_BLACK_PAWN_LINE = 7;
    private static final String EXCEPTION_MOVE = "잘못된 이동입니다.";
    private static final String EXCEPTION_DUPLICATE_POSITION = "동일한 좌표는 불가능합니다.";
    private static final String EXCEPTION_POSITION = "잘못된 좌표입니다.";
    private static final int WIN_BOUNDARY = 0;

    private final Map<Position, Piece> chessBoard;
    private final Pieces whitePieces;
    private final Pieces blackPieces;
    private boolean running = true;

    public ChessBoard() {
        InitChessBoard initChessBoard = new InitChessBoard();
        this.chessBoard = initChessBoard.getChessBoard();
        this.whitePieces = initChessBoard.getWhitePieces();
        this.blackPieces = initChessBoard.getBlackPieces();
    }

    public static String createPiecePositionName(int row, char column) {
        return "" + column + row;
    }

    private Piece piece(Position position) {
        return chessBoard.get(position);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public void move(String source, String target) {
        validatePosition(source, target);
        Position start = Position.valueOf(source);
        Position end = Position.valueOf(target);

        if (isMoveAblePosition(start, end)) {
            movePiece(start, end);
            return;
        }
        throw new IllegalArgumentException(EXCEPTION_MOVE);
    }

    private boolean isMoveAblePosition(Position start, Position end) {
        return piece(start).isMoveAble(start, end, this);
    }

    private void checkKilledPieceIsKing(Piece endPiece) {
        if (endPiece instanceof King) {
            running = false;
        }
    }

    private Piece pieceOnChessBoard(Position boardPosition, Piece piece) {
        return chessBoard.put(boardPosition, piece);
    }

    private void killPiece(Position start, Position end) {
        Piece startPiece = piece(start);
        Piece endPiece = piece(end);

        endPiece.dead();
        pieceOnChessBoard(end, startPiece);
        pieceOnChessBoard(start, Blank.INSTANCE);
        startPiece.setPosition(end);
    }

    private void movePiece(Position start, Position end) {
        Piece startPiece = piece(start);
        Piece endPiece = piece(end);
        if (isBlankPiece(piece(end))) {
            pieceOnChessBoard(start, Blank.INSTANCE);
            pieceOnChessBoard(end, startPiece);
            startPiece.setPosition(end);
            return;
        }
        killPiece(start, end);
        checkKilledPieceIsKing(endPiece);
    }

    private boolean isBlankPiece(Piece piece) {
        return piece == Blank.INSTANCE;
    }


    private void validatePosition(String source, String target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException(EXCEPTION_DUPLICATE_POSITION);
        }
        if (isMoveAblePosition(source, target)) {
            return;
        }
        throw new IllegalArgumentException(EXCEPTION_POSITION);
    }

    private boolean isMoveAblePosition(String source, String target) {
        return chessBoard.containsKey(Position.valueOf(source))
            && chessBoard.containsKey(Position.valueOf(target));
    }

    public boolean isBlank(Position position) {
        Piece piece = piece(position);
        return isBlankPiece(piece);
    }


    public Piece getPiece(Position position) {
        return piece(position);
    }

    public ResultDto result() {
        Map<TeamColor, Score> result = new HashMap<>();
        result.put(TeamColor.BLACK, blackPieces.calculateScore());
        result.put(TeamColor.WHITE, whitePieces.calculateScore());

        if (isBlankWin(result)) {
            return new ResultDto(result, TeamColor.BLACK);
        }
        if (isWhiteWin(result)) {
            return new ResultDto(result, TeamColor.WHITE);
        }
        return new ResultDto(result, TeamColor.NONE);
    }

    private boolean isWhiteWin(Map<TeamColor, Score> result) {
        return result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) < WIN_BOUNDARY;
    }

    private boolean isBlankWin(Map<TeamColor, Score> result) {
        return result.get(TeamColor.BLACK).compareTo(result.get(TeamColor.WHITE)) > WIN_BOUNDARY;
    }


    public boolean isPlaying() {
        return running;
    }
}

