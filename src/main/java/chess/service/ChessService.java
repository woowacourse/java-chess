package chess.service;

import chess.model.ChessGame;
import chess.model.File;
import chess.model.Rank;
import chess.model.board.Square;
import chess.model.board.Board;
import chess.util.PieceToLetterConvertor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessService {
    private ChessGame chessGame;

    public List<List<String>> initGame() {
        chessGame = new ChessGame();
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
                .map(PieceToLetterConvertor::convertToLetter)
                .collect(Collectors.toList());
    }

    public List<List<String>> move(String from, String to) {
        chessGame.move(Square.of(from), Square.of(to));
        return getAllPieceLetter(chessGame.getBoard());
    }

    public Map<String, Double> getScores() {
        return chessGame.status().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
    }

    public boolean isWaitingOrRunning() {
        return chessGame == null || chessGame.isRunning();
    }
}
