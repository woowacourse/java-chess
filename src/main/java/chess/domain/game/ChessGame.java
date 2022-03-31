package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.Article;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;
    private Color turn = Color.WHITE;

    public ChessGame() {
        this.chessBoard = new ChessBoard(createBoard());
        this.gameStatus = GameStatus.READY;
    }

    public void play(Position from, Position to) {
        if (!isThatTurn(from)) {
            throw new IllegalArgumentException("현재 진영에 속해있지 않는 위치입니다.");
        }

        chessBoard.movePiece(from, to);
        if (chessBoard.isKingAlive()) {
            gameStatus = GameStatus.END;
        }
        turn = turn.oppositeColor();
    }

    private boolean isThatTurn(Position position) {
        return chessBoard.isSameColor(position, turn);
    }

    public Map<Color, Double> getStatus() {
        return chessBoard.getColorsTotalScore();
    }

    public Color getWinner() {
        return chessBoard.getWinner();
    }

    private Map<Position, Article> createBoard() {
        Map<Position, Article> squares = new HashMap<>();

        initNotPawnSquares(squares, Rank.ONE, Color.WHITE);
        initPawnPieces(squares, Rank.TWO, Color.WHITE);
        initPawnPieces(squares, Rank.SEVEN, Color.BLACK);
        initNotPawnSquares(squares, Rank.EIGHT, Color.BLACK);

        return squares;
    }

    private void initPawnPieces(Map<Position, Article> squares, Rank rank, Color color) {
        for (File file : File.values()) {
            squares.put(new Position(file, rank), new Pawn(color));
        }
    }

    private void initNotPawnSquares(Map<Position, Article> squares, Rank rank, Color color) {
        squares.put(new Position(File.A, rank), new Rook(color));
        squares.put(new Position(File.B, rank), new Knight(color));
        squares.put(new Position(File.C, rank), new Bishop(color));
        squares.put(new Position(File.D, rank), new Queen(color));
        squares.put(new Position(File.E, rank), new King(color));
        squares.put(new Position(File.F, rank), new Bishop(color));
        squares.put(new Position(File.G, rank), new Knight(color));
        squares.put(new Position(File.H, rank), new Rook(color));
    }

    public void start() {
        gameStatus = GameStatus.PLAYING;
    }

    public boolean isReady() {
        return gameStatus == GameStatus.READY;
    }

    public boolean isPlaying() {
        return gameStatus == GameStatus.PLAYING;
    }

    public boolean isEnd() {
        return gameStatus == GameStatus.END;
    }

    public Map<Position, Article> getBoard() {
        return chessBoard.getPieces();
    }
}
