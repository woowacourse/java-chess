package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.result.Pieces;
import chess.domain.result.ResultDto;
import chess.domain.result.Score;
import chess.domain.state.TeamColor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final int MAX_NUM_PIECE = 16;
    private static final int ROW_WHITE_START = 1;
    private static final int ROW_WHITE_END = 2;
    public static final char COLUMN_FIRST = 'a';
    public static final char COLUMN_LAST = 'h';
    public static final int ROW_FIRST = 1;
    public static final int ROW_LAST = 8;
    private static final int ROW_BLACK_START = 7;
    private static final int ROW_BLACK_END = 8;
    public static final int ROW_WHITE_PAWN_LINE = 2;
    public static final int ROW_BLACK_PAWN_LINE = 7;
    private static final String EXCEPTION_MOVE = "잘못된 이동입니다.";
    private static final String EXCEPTION_DUPLICATE_POSITION = "동일한 좌표는 불가능합니다.";
    private static final String EXCEPTION_POSITION = "잘못된 좌표입니다.";
    private static final int WIN_BOUNDARY = 0;

    private final Map<Position, Piece> chessBoard;
    private final Pieces whitePieces;
    private final Pieces blackPieces;
    private boolean gameStatus = true;

    //TODO : init 분리
    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
        initBoard();
        this.whitePieces = initWhitePieces();
        this.blackPieces = initBlackPieces();
    }

    private Pieces initWhitePieces() {
        List<Piece> whitePieces = new ArrayList<>(MAX_NUM_PIECE);
        for (int row = ROW_WHITE_START; row <= ROW_WHITE_END; row++) {
            for (char column = COLUMN_FIRST; column <= COLUMN_LAST; column++) {
                String piecePositionName = createPiecePositionName(row, column);
                whitePieces.add(piece(Position.valueOf(piecePositionName)));
            }
        }
        return new Pieces(whitePieces);
    }

    public static String createPiecePositionName(int row, char column) {
        return "" + column + row;
    }

    private Piece piece(Position position) {
        return chessBoard.get(position);
    }

    private Pieces initBlackPieces() {
        List<Piece> blackPieces = new ArrayList<>(16);
        for (int row = ROW_BLACK_START; row <= ROW_BLACK_END; row++) {
            for (char column = COLUMN_FIRST; column <= COLUMN_LAST; column++) {
                String piecePositionName = createPiecePositionName(row, column);
                blackPieces.add(piece(Position.valueOf(piecePositionName)));
            }
        }
        return new Pieces(blackPieces);
    }

    private void initBoard() {
        for (int row = ROW_FIRST; row <= ROW_LAST; row++) {
            for (char column = COLUMN_FIRST; column <= COLUMN_LAST; column++) {
                String boardPosition = createPiecePositionName(row, column);
                Piece piece = Blank.INSTANCE;
                pieceOnChessBoard(boardPosition, piece);
            }
        }
        initPawnLine();
        initUniquePieceLine();
    }

    private Piece pieceOnChessBoard(String boardPosition, Piece piece) {
        return chessBoard.put(Position.valueOf(boardPosition), piece);
    }

    private Piece pieceOnChessBoard(Position boardPosition, Piece piece) {
        return chessBoard.put(boardPosition, piece);
    }

    private void initPawnLine() {
        for (char column = COLUMN_FIRST; column <= COLUMN_LAST; column++) {
            String boardPosition = createPiecePositionName(ROW_WHITE_PAWN_LINE, column);
            Piece pawn = new Pawn(TeamColor.WHITE, Position.valueOf(boardPosition));
            inputPiece(boardPosition, pawn);
        }

        for (char column = COLUMN_FIRST; column <= COLUMN_LAST; column++) {
            String boardPosition = createPiecePositionName(ROW_BLACK_PAWN_LINE, column);
            Piece pawn = new Pawn(TeamColor.BLACK, Position.valueOf(boardPosition));
            inputPiece(boardPosition, pawn);
        }
    }

    private Piece inputPiece(String boardPosition, Piece piece) {
        return chessBoard.put(Position.valueOf(boardPosition), piece);
    }

    private void initUniquePieceLine() {
        TeamColor color = TeamColor.WHITE;
        chessBoard.put(Position.valueOf("a1"), new Rook(color, Position.valueOf("a1")));
        chessBoard.put(Position.valueOf("b1"), new Knight(color, Position.valueOf("b1")));
        chessBoard.put(Position.valueOf("c1"), new Bishop(color, Position.valueOf("c1")));
        chessBoard.put(Position.valueOf("d1"), new Queen(color, Position.valueOf("d1")));
        chessBoard.put(Position.valueOf("e1"), new King(color, Position.valueOf("e1")));
        chessBoard.put(Position.valueOf("f1"), new Bishop(color, Position.valueOf("f1")));
        chessBoard.put(Position.valueOf("g1"), new Knight(color, Position.valueOf("g1")));
        chessBoard.put(Position.valueOf("h1"), new Rook(color, Position.valueOf("h1")));

        color = TeamColor.BLACK;
        chessBoard.put(Position.valueOf("a8"), new Rook(color, Position.valueOf("a8")));
        chessBoard.put(Position.valueOf("b8"), new Knight(color, Position.valueOf("b8")));
        chessBoard.put(Position.valueOf("c8"), new Bishop(color, Position.valueOf("c8")));
        chessBoard.put(Position.valueOf("d8"), new Queen(color, Position.valueOf("d8")));
        chessBoard.put(Position.valueOf("e8"), new King(color, Position.valueOf("e8")));
        chessBoard.put(Position.valueOf("f8"), new Bishop(color, Position.valueOf("f8")));
        chessBoard.put(Position.valueOf("g8"), new Knight(color, Position.valueOf("g8")));
        chessBoard.put(Position.valueOf("h8"), new Rook(color, Position.valueOf("h8")));
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
            gameStatus = false;
        }
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
        return chessBoard.containsKey(Position.valueOf(source)) && chessBoard
            .containsKey(Position.valueOf(target));
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
        return gameStatus;
    }
}

