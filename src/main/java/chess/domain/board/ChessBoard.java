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

    private static final String EXCEPTION_MOVE = "잘못된 이동입니다.";
    private static final String EXCEPTION_DUPLICATE_POSITION = "동일한 좌표는 불가능합니다.";
    private static final int WIN_BOUNDARY = 0;

    private final PieceOnBoardPosition pieceOnBoardPosition;
    private final Pieces whitePieces;
    private final Pieces blackPieces;
    private boolean running = true;

    public ChessBoard() {
        InitChessBoard initChessBoard = new InitChessBoard();
        this.pieceOnBoardPosition = new PieceOnBoardPosition(initChessBoard.getChessBoard());
        this.whitePieces = initChessBoard.getWhitePieces();
        this.blackPieces = initChessBoard.getBlackPieces();
    }

    public static String createPiecePositionName(int row, char column) {
        return String.valueOf(column) + row;
    }

    private Piece piece(Position position) {
        return pieceOnBoardPosition.valueOf(position);
    }

    public Map<Position, Piece> getChessBoard() {
        return pieceOnBoardPosition.getChessBoard();
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
        return piece(start).isMovable(start, end, this);
    }

    private void checkKilledPieceIsKing(Piece endPiece) {
        if (endPiece instanceof King) {
            running = false;
        }
    }

    private void pieceOnChessBoard(Position boardPosition, Piece piece) {
        pieceOnBoardPosition.pieceOnChessBoard(boardPosition, piece);
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
        throw new IllegalArgumentException(Position.ERROR_POSITION);
    }

    private boolean isMoveAblePosition(String source, String target) {
        return pieceOnBoardPosition.containsKey(Position.valueOf(source))
            && pieceOnBoardPosition.containsKey(Position.valueOf(target));
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

