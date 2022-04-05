package chess.console.service;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.File;
import chess.model.board.ChessInitializer;
import chess.model.board.EmptyBoardInitializer;
import chess.model.piece.PieceLetter;
import chess.model.Rank;
import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.status.Playing;
import chess.model.status.Ready;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessService {
    private ChessGame chessGame;

    public ChessService() {
        this.chessGame = new ChessGame(new EmptyBoardInitializer(), new Ready());
    }

    public List<List<String>> initGame() {
        chessGame = new ChessGame(new ChessInitializer(), new Playing());
        return getAllPieceLetter(chessGame.getBoard());
    }

    private List<List<String>> getAllPieceLetter(final Board board) {
        return Rank.getRanksInBoardOrder().stream()
                .map(rank -> getPieceLetterInRank(board, rank))
                .collect(Collectors.toList());
    }

    private List<String> getPieceLetterInRank(Board board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> board.findPieceBySquare(Square.of(file, rank)))
                .map(PieceLetter::getLetterByColor)
                .collect(Collectors.toList());
    }

    public List<List<String>> move(String from, String to) {
        chessGame.move(Square.of(from), Square.of(to));
        return getAllPieceLetter(chessGame.getBoard());
    }

    public Map<String, Double> getScores() {
        return chessGame.getPlayersScore().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
    }

    public boolean isWaitingOrRunning() {
        return chessGame.isRunning();
    }

    public void endGame() {
        chessGame.end();
    }

    public GameResult getResult() {
        Color winner = chessGame.findWinner();
        if (winner.equals(Color.NOTHING)) {
            return new GameResult(winner.name(), true);
        }
        return new GameResult(winner.name(), false);
    }
}
