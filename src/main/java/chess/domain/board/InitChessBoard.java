package chess.domain.board;

import static chess.domain.board.ChessBoard.createPiecePositionName;
import static chess.domain.board.ChessBoardInfo.COLUMN_FIRST;
import static chess.domain.board.ChessBoardInfo.COLUMN_LAST;
import static chess.domain.board.ChessBoardInfo.MAX_NUM_PIECE;
import static chess.domain.board.ChessBoardInfo.ROW_BLACK_END;
import static chess.domain.board.ChessBoardInfo.ROW_BLACK_PAWN_LINE;
import static chess.domain.board.ChessBoardInfo.ROW_BLACK_START;
import static chess.domain.board.ChessBoardInfo.ROW_FIRST;
import static chess.domain.board.ChessBoardInfo.ROW_LAST;
import static chess.domain.board.ChessBoardInfo.ROW_WHITE_END;
import static chess.domain.board.ChessBoardInfo.ROW_WHITE_PAWN_LINE;
import static chess.domain.board.ChessBoardInfo.ROW_WHITE_START;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.result.Pieces;
import chess.domain.state.TeamColor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InitChessBoard {

    private final Map<Position, Piece> chessBoard;
    private final Pieces whitePieces;
    private final Pieces blackPieces;

    public InitChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
        initBoard();
        this.whitePieces = initWhitePieces();
        this.blackPieces = initBlackPieces();
    }


    private Pieces initBlackPieces() {
        int maxNumPiece = MAX_NUM_PIECE.getBoardInfo();
        int rowStart = ROW_BLACK_START.getBoardInfo();
        int rowEnd = ROW_BLACK_END.getBoardInfo();
        char columnStart = (char) COLUMN_FIRST.getBoardInfo();
        char columnEnd = (char) COLUMN_LAST.getBoardInfo();

        List<Piece> blackPieces = new ArrayList<>(maxNumPiece);
        for (int row = rowStart; row <= rowEnd; row++) {
            for (char column = columnStart; column <= columnEnd; column++) {
                String piecePositionName = createPiecePositionName(row, column);
                blackPieces.add(piece(Position.valueOf(piecePositionName)));
            }
        }
        return new Pieces(blackPieces);
    }

    private Pieces initWhitePieces() {
        int rowStart = ROW_WHITE_START.getBoardInfo();
        int rowEnd = ROW_WHITE_END.getBoardInfo();
        int maxNumPiece = MAX_NUM_PIECE.getBoardInfo();
        char columnStart = (char) COLUMN_FIRST.getBoardInfo();
        char columnEnd = (char) COLUMN_LAST.getBoardInfo();

        List<Piece> whitePieces = new ArrayList<>(maxNumPiece);
        for (int row = rowStart; row <= rowEnd; row++) {
            for (char column = columnStart; column <= columnEnd; column++) {
                String piecePositionName = createPiecePositionName(row, column);
                whitePieces.add(piece(Position.valueOf(piecePositionName)));
            }
        }
        return new Pieces(whitePieces);
    }

    private Piece piece(Position position) {
        return chessBoard.get(position);
    }

    private void initBoard() {
        int rowStart = ROW_FIRST.getBoardInfo();
        int rowEnd = ROW_LAST.getBoardInfo();
        char columnStart = (char) COLUMN_FIRST.getBoardInfo();
        char columnEnd = (char) COLUMN_LAST.getBoardInfo();

        for (int row = rowStart; row <= rowEnd; row++) {
            for (char column = columnStart; column <= columnEnd; column++) {
                String boardPosition = createPiecePositionName(row, column);
                Piece piece = Blank.INSTANCE;
                pieceOnChessBoard(boardPosition, piece);
            }
        }
        initPawnLine();
        initUniquePieceLine();
    }

    private void pieceOnChessBoard(String boardPosition, Piece piece) {
        chessBoard.put(Position.valueOf(boardPosition), piece);
    }


    private void initPawnLine() {
        char columnStart = (char) COLUMN_FIRST.getBoardInfo();
        char columnEnd = (char) COLUMN_LAST.getBoardInfo();

        for (char column = columnStart; column <= columnEnd; column++) {
            String boardPosition = createPiecePositionName(ROW_WHITE_PAWN_LINE.getBoardInfo(),
                column);
            Piece pawn = new Pawn(TeamColor.WHITE, Position.valueOf(boardPosition));
            inputPiece(boardPosition, pawn);
        }

        for (char column = columnStart; column <= columnEnd; column++) {
            String boardPosition = createPiecePositionName(ROW_BLACK_PAWN_LINE.getBoardInfo(),
                column);
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

    public Pieces getWhitePieces() {
        return whitePieces;
    }

    public Pieces getBlackPieces() {
        return blackPieces;
    }
}
