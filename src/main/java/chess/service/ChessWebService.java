package chess.service;

import chess.db.ChessPiece;
import chess.db.MoveHistoryDao;
import chess.db.PieceDao;
import chess.domains.board.Board;
import chess.domains.board.BoardFactory;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessWebService {
    public static final int BOARD_CELLS_NUMBER = 64;
    private static final String TURN_MSG_FORMAT = "%s의 순서입니다.";
    private static final String WINNING_MSG_FORMAT = "%s이/가 이겼습니다.";

    private final PieceDao pieceDao;
    private final MoveHistoryDao moveHistoryDao;
    private final Map<String, Board> playingBoards = new HashMap<>();

    public ChessWebService(PieceDao pieceDao, MoveHistoryDao moveHistoryDao) {
        this.pieceDao = pieceDao;
        this.moveHistoryDao = moveHistoryDao;
    }

    public boolean canResume(String gameId) throws SQLException {
        int savedPiecesNumber = pieceDao.countSavedPieces(gameId);
        return savedPiecesNumber == BOARD_CELLS_NUMBER;
    }

    public void startNewGame(String gameId) throws SQLException {
        deleteSavedGame(gameId);

        Board board = new Board();
        playingBoards.put(gameId, board);

        List<ChessPiece> chessPieces = Position.stream()
                .map(position -> {
                    Piece piece = board.getPieceByPosition(position);
                    return new ChessPiece(gameId, position.name(), piece.name());
                })
                .collect(Collectors.toList());
        pieceDao.addInitialPieces(chessPieces);
    }

    public void resumeGame(String gameId) throws SQLException {
        Map<Position, Piece> savedBoardStatus = Position.stream()
                .collect(Collectors.toMap(Function.identity(), position -> {
                    String pieceName = null;
                    try {
                        pieceName = pieceDao.findPieceNameByPosition(gameId, position);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return BoardFactory.findPieceByPieceName(pieceName);
                }));

        Optional<String> lastTurn = moveHistoryDao.figureLastTurn(gameId);

        Board board = new Board();
        board.recoverBoard(savedBoardStatus, lastTurn);

        playingBoards.put(gameId, board);
    }

    public void move(String gameId, String sourceName, String targetName) throws SQLException {
        Board board = playingBoards.get(gameId);
        PieceColor currentTeam = board.getTeamColor();

        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);

        board.move(source, target);

        pieceDao.updatePiece(gameId, source, board.getPieceByPosition(source));
        pieceDao.updatePiece(gameId, target, board.getPieceByPosition(target));
        moveHistoryDao.addMoveHistory(gameId, currentTeam, source, target);
    }

    public String provideWinner(String gameId) throws SQLException {
        Board board = playingBoards.get(gameId);

        if (board.isGameOver()) {
            deleteSavedGame(gameId);
            return winningMsg(board);
        }
        return null;
    }

    public Map<String, Object> provideGameInfo(String gameId) {
        Board board = playingBoards.get(gameId);

        Map<String, Object> gameInfo = new HashMap<>();
        gameInfo.put("pieces", convertPieces(board));
        gameInfo.put("turn", turnMsg(board));
        gameInfo.put("white_score", calculateScore(board, PieceColor.WHITE));
        gameInfo.put("black_score", calculateScore(board, PieceColor.BLACK));

        return gameInfo;
    }

    private void deleteSavedGame(String gameId) throws SQLException {
        pieceDao.deleteBoardStatus(gameId);
        moveHistoryDao.deleteMoveHistory(gameId);
        playingBoards.remove(gameId);
    }

    private List<String> convertPieces(Board board) {
        List<Piece> pieces = board.showBoard();
        List<String> pieceCodes = new ArrayList<>();

        for (Piece piece : pieces) {
            switch (piece.name()) {
                case "r":
                    pieceCodes.add("♖");
                    break;
                case "n":
                    pieceCodes.add("♘");
                    break;
                case "b":
                    pieceCodes.add("♗");
                    break;
                case "k":
                    pieceCodes.add("♕");
                    break;
                case "q":
                    pieceCodes.add("♔");
                    break;
                case "p":
                    pieceCodes.add("♙");
                    break;
                case "R":
                    pieceCodes.add("♜");
                    break;
                case "N":
                    pieceCodes.add("♞");
                    break;
                case "B":
                    pieceCodes.add("♝");
                    break;
                case "K":
                    pieceCodes.add("♛");
                    break;
                case "Q":
                    pieceCodes.add("♚");
                    break;
                case "P":
                    pieceCodes.add("♟");
                    break;
                case ".":
                    pieceCodes.add("");
                    break;
            }
        }
        return pieceCodes;
    }

    private String turnMsg(Board board) {
        PieceColor team = board.getTeamColor();
        return String.format(TURN_MSG_FORMAT, team.name());
    }

    private double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }

    private String winningMsg(Board board) {
        PieceColor winner = board.getTeamColor().changeTeam();
        return String.format(WINNING_MSG_FORMAT, winner);
    }
}
