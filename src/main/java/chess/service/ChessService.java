package chess.service;

import static java.util.stream.Collectors.toMap;

import chess.model.ChessGame;
import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.model.board.Square;
import chess.model.piece.Piece;
import chess.model.piece.PieceLetter;
import chess.model.status.End;
import chess.model.status.Playing;
import chess.dao.RuntimeChessGameDao;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChessService {
    private final RuntimeChessGameDao dao;

    public ChessService(RuntimeChessGameDao dao) {
        this.dao = dao;
    }

    public void initGame() {
        ChessGame chessGame = new ChessGame(new ChessInitializer(), new Playing());
        Map<Square, Piece> board = chessGame.getBoard().getBoard();
        dao.saveAll(board, chessGame.getTurn(), new Playing());
    }

    private ChessGame getGameFromDao() {
        return new ChessGame(new Board(dao.getAllPieces()), dao.getTurn(), dao.getStatus());
    }

    public BoardDto getBoard() {
        Map<Square, Piece> allPieces = dao.getAllPieces();
        return new BoardDto(allPieces.keySet().stream()
                .collect(toMap(Square::getName, key -> toDto(allPieces.get(key)))));
    }

    private PieceDto toDto(Piece piece) {
        String pieceName = PieceLetter.getName(piece);
        return new PieceDto(pieceName, piece.getColor().name());
    }

    public List<List<String>> getAllPieceLetter() {
        return Rank.getRanksInBoardOrder().stream()
                .map(rank -> getPieceLetterInRank(getGameFromDao().getBoard(), rank))
                .collect(Collectors.toList());
    }

    private List<String> getPieceLetterInRank(Board board, Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> board.findPieceBySquare(Square.of(file, rank)))
                .map(PieceLetter::getLetterByColor)
                .collect(Collectors.toList());
    }

    public void move(String from, String to) {
        ChessGame chessGame = getGameFromDao();
        chessGame.move(Square.of(from), Square.of(to));
        dao.saveAll(chessGame.getBoard().getBoard(), chessGame.getTurn(), chessGame.getStatus());
    }

    public boolean isWaitingOrRunning() {
        return getGameFromDao().isRunning();
    }

    public void endGame() {
        dao.updateStatus(new End());
    }

    public GameResultDto getResult() {
        Color winner = getGameFromDao().findWinner();
        if (winner.equals(Color.NOTHING)) {
            return new GameResultDto(getScores(), winner.name(), true);
        }
        return new GameResultDto(getScores(), winner.name(), false);
    }

    private Map<String, Double> getScores() {
        return getGameFromDao().getPlayersScore().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().name(), Entry::getValue));
    }
}
