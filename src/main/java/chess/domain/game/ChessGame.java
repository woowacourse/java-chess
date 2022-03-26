package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private Color turn = Color.WHITE;

    public ChessGame() {
        this.board = new Board(createBoard());
    }

    public void play(Position from, Position to) {
        if (!isThatTurn(from)) {
            throw new IllegalArgumentException("해당 진영의 차례가 아닙니다");
        }
        board.move(from, to);
        turn = turn.oppositeColor();
    }

    private boolean isThatTurn(Position position) {
        return board.isSameColor(position, turn);
    }

    public Status getStatus() {
        return new Status(board.getColorsTotalScore());
    }

    public boolean isGameNext(Position to) {
        return board.isKingAlive(to);
    }

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> squares = new HashMap<>();

        initEmptyPieces(squares);
        initNotPawnSquares(squares, Rank.ONE, Color.WHITE);
        initPawnPieces(squares, Rank.TWO, Color.WHITE);
        initPawnPieces(squares, Rank.SEVEN, Color.BLACK);
        initNotPawnSquares(squares, Rank.EIGHT, Color.BLACK);

        return squares;
    }

    private void initEmptyPieces(Map<Position, Piece> squares) {
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                squares.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    private void initPawnPieces(Map<Position, Piece> squares, Rank rank, Color color) {
        for (File file : File.values()) {
            squares.replace(new Position(file, rank), new Pawn(color));
        }
    }

    private void initNotPawnSquares(Map<Position, Piece> squares, Rank rank, Color color) {
        squares.replace(new Position(File.A, rank), new Rook(color));
        squares.replace(new Position(File.B, rank), new Knight(color));
        squares.replace(new Position(File.C, rank), new Bishop(color));
        squares.replace(new Position(File.D, rank), new Queen(color));
        squares.replace(new Position(File.E, rank), new King(color));
        squares.replace(new Position(File.F, rank), new Bishop(color));
        squares.replace(new Position(File.G, rank), new Knight(color));
        squares.replace(new Position(File.H, rank), new Rook(color));
    }

    public Board getBoard() {
        return board;
    }
}
