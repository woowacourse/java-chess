package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.side.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGame {

    private final Long id;
    private GameState gameState;
    private final Board board;

    public ChessGame(Long id, GameState gameState, Board board) {
        this.id = id;
        this.gameState = gameState;
        this.board = board;
    }

    public static ChessGame createInit() {
        return new ChessGame(null, GameState.INIT, null); // 이 부분이 확신이 없음
    }

    public void pause() {
        validateRunning();
        gameState = GameState.PAUSE;
    }

    public void move(final String sourceSquareInput, final String targetSquareInput) {
        validateRunning();
        Square sourceSquare = Square.from(sourceSquareInput);
        Square targetSquare = Square.from(targetSquareInput);
        board.makeMove(sourceSquare, targetSquare);
        if (board.findColorKingDied() != Color.NOTHING) {
            gameState = GameState.FINISHED;
        }
    }

    public Color findWinner() {
        Color colorKingDied = board.findColorKingDied();
        if (colorKingDied != Color.NOTHING) {
            return colorKingDied.findOpponent();
        }
        return calculateWinnerByScore();
    }

    private Color calculateWinnerByScore() {
        Map<Color, Double> sideScore = status();
        Double whiteScore = sideScore.get(Color.WHITE);
        Double blackScore = sideScore.get(Color.BLACK);

        if (whiteScore.compareTo(blackScore) > 0) {
            return Color.BLACK;
        }
        if (whiteScore.compareTo(blackScore) < 0) {
            return Color.WHITE;
        }
        return Color.NOTHING;
    }

    public Map<Color, Double> status() {
        return board.calculateScore();
    }

    public List<List<Piece>> findChessBoard() {
        List<List<Piece>> pieces = new ArrayList<>();
        List<File> files = List.of(File.values());
        List<Rank> ranks = List.of(Rank.values());
        for (Rank rank : ranks) {
            List<Piece> pieceByRank = files.stream()
                    .map(file -> board.findPiece(file, rank))
                    .collect(Collectors.toList());
            pieces.add(pieceByRank);
        }
        return pieces;
    }

    public boolean isContinue() {
        return gameState == GameState.RUNNING || gameState == GameState.INIT;
    }

    public Long getId() {
        return this.id;
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean isKindDied() {
        return gameState == GameState.FINISHED;
    }

    private void validateInit() {
        if (gameState != GameState.INIT) {
            throw new IllegalStateException("이미 시작한 게임 입니다.");
        }
    }

    private void validateRunning() {
        if (gameState != GameState.RUNNING) {
            throw new IllegalStateException("진행 중인 게임이 아닙니다.");
        }
    }

    private void validateStarted() {
        if (gameState == GameState.INIT) {
            throw new IllegalStateException("게임을 먼저 시작해 주세요.");
        }
    }
}
