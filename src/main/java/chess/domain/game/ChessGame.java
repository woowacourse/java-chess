package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Team;
import java.util.HashMap;
import java.util.Map;

public final class ChessGame {
    private static final String NO_TURN_MESSAGE = "현재 진영에 속해있지 않는 위치입니다.";

    private final Board board;
    private GameState gameState;
    private Team turn = Team.WHITE;

    public ChessGame(Board board) {
        this.board = board;
        this.gameState = GameState.READY;
    }

    public ChessGame() {
        this.board = new Board(createBoard());
        this.gameState = GameState.READY;
    }

    public void play(Position from, Position to) {
        if (!isTurn(from)) {
            throw new IllegalArgumentException(NO_TURN_MESSAGE);
        }
        boolean isCheckmate = isCheckmate(to);
        board.move(from, to);
        if (isCheckmate) {
            gameState = GameState.END;
            return;
        }
        turn = turn.changeTeam();
    }

    private boolean isTurn(Position position) {
        return board.isSameColor(position, turn);
    }

    public Status getStatus() {
        return new Status(board.getTotalStatus(), turn);
    }

    public boolean isCheckmate(Position to) {
        return board.isCheckmate(to);
    }

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> squares = new HashMap<>();
        initEmptyPieces(squares);
        initNotPawnSquares(squares, Rank.ONE, Team.WHITE);
        initPawnPieces(squares, Rank.TWO, Team.WHITE);
        initPawnPieces(squares, Rank.SEVEN, Team.BLACK);
        initNotPawnSquares(squares, Rank.EIGHT, Team.BLACK);
        return squares;
    }

    private void initEmptyPieces(Map<Position, Piece> squares) {
        for (Column column : Column.values()) {
            initRankEmpty(squares, column);
        }
    }

    private void initRankEmpty(Map<Position, Piece> squares, Column column) {
        for (Rank rank : Rank.values()) {
            squares.put(new Position(column, rank), new EmptyPiece());
        }
    }

    private void initPawnPieces(Map<Position, Piece> squares, Rank rank, Team team) {
        for (Column column : Column.values()) {
            squares.replace(new Position(column, rank), new Pawn(team));
        }
    }

    private void initNotPawnSquares(Map<Position, Piece> squares, Rank rank, Team team) {
        squares.replace(new Position(Column.A, rank), new Rook(team));
        squares.replace(new Position(Column.B, rank), new Knight(team));
        squares.replace(new Position(Column.C, rank), new Bishop(team));
        squares.replace(new Position(Column.D, rank), new Queen(team));
        squares.replace(new Position(Column.E, rank), new King(team));
        squares.replace(new Position(Column.F, rank), new Bishop(team));
        squares.replace(new Position(Column.G, rank), new Knight(team));
        squares.replace(new Position(Column.H, rank), new Rook(team));
    }

    public void start() {
        gameState = GameState.PLAYING;
    }

    public boolean isPlaying() {
        return gameState == GameState.PLAYING;
    }

    public Board getBoard() {
        return board;
    }
}
