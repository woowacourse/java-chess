package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.player.Pieces;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    //TODO : King 이 dead 상태가 될 경우 게임 종료
    private final Map<Position, Piece> chessBoard;
    private final Pieces whitePieces;
    private final Pieces blackPieces;

    public ChessBoard() {
        this.chessBoard = new LinkedHashMap<>();
        initBoard();
        this.whitePieces = initWhitePieces();
        this.blackPieces = initBlackPieces();
    }

    private Pieces initWhitePieces() {
        List<Piece> whitePieces = new ArrayList<>(16);
        for (int j = 1; j <= 2; j++) {
            for (char i = 'a'; i <= 'h'; i++) {
                String boardPosition = "" + i + j;
                whitePieces.add(chessBoard.get(Position.valueOf(boardPosition)));
            }
        }
        return new Pieces(whitePieces);
    }

    private Pieces initBlackPieces() {

        List<Piece> blackPieces = new ArrayList<>(16);
        for (int j = 7; j <= 8; j++) {
            for (char i = 'a'; i <= 'h'; i++) {
                String boardPosition = "" + i + j;
                blackPieces.add(chessBoard.get(Position.valueOf(boardPosition)));
            }
        }
        return new Pieces(blackPieces);
    }

    private void initBoard() {
        for (int j = 1; j <= 8; j++) {
            for (char i = 'a'; i <= 'h'; i++) {
                String boardPosition = "" + i + j;
                chessBoard.put(Position.valueOf(boardPosition), Blank.INSTANCE);
            }
        }
        createPawnLine();
        createUniqueLine();
    }

    private void createPawnLine() {
        for (char i = 'a'; i <= 'h'; i++) {
            String boardPosition = "" + i + "2";
            chessBoard.put(Position.valueOf(boardPosition),
                new Pawn(TeamColor.WHITE, Position.valueOf(boardPosition)));
        }

        for (char i = 'a'; i <= 'h'; i++) {
            String boardPosition = "" + i + "7";
            chessBoard.put(Position.valueOf(boardPosition),
                new Pawn(TeamColor.BLACK, Position.valueOf(boardPosition)));
        }

    }

    private void createUniqueLine() {
        // g화이트
        TeamColor color = TeamColor.WHITE;

        chessBoard.put(Position.valueOf("a1"), new Rook(color, Position.valueOf("a1")));
        chessBoard.put(Position.valueOf("b1"), new Knight(color, Position.valueOf("b1")));
        chessBoard.put(Position.valueOf("c1"), new Bishop(color, Position.valueOf("c1")));
        chessBoard.put(Position.valueOf("d1"), new Queen(color, Position.valueOf("d1")));
        chessBoard.put(Position.valueOf("e1"), new King(color, Position.valueOf("e1")));
        chessBoard.put(Position.valueOf("f1"), new Bishop(color, Position.valueOf("f1")));
        chessBoard.put(Position.valueOf("g1"), new Knight(color, Position.valueOf("g1")));
        chessBoard.put(Position.valueOf("h1"), new Rook(color, Position.valueOf("h1")));

        // 블랙

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
        Piece startPiece = chessBoard.get(start);
        Piece goingToDie = chessBoard.get(end);

        if (chessBoard.get(start).isMoveAble(start, end, this)) {
            //blank 경우
            if (chessBoard.get(end) == Blank.INSTANCE) {
                chessBoard.put(start, Blank.INSTANCE);
                chessBoard.put(end, startPiece);
                startPiece.setPosition(end);
                return;
            }
            // 상대편이 있는 경우
            goingToDie.dead();
            chessBoard.put(end, startPiece);
            chessBoard.put(start, Blank.INSTANCE);
            startPiece.setPosition(end);
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
        boolean ret = piece == Blank.INSTANCE;
        return ret;
    }


    public Piece getPiece(Position position) {
        return chessBoard.get(position);
    }
}

