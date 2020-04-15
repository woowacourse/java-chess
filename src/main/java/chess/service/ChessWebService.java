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

    private final PieceDao pieceDao;
    private final MoveHistoryDao moveHistoryDao;

    public ChessWebService(PieceDao pieceDao, MoveHistoryDao moveHistoryDao) {
        this.pieceDao = pieceDao;
        this.moveHistoryDao = moveHistoryDao;
    }

    public boolean canResume(String gameId) throws SQLException {
        int savedPiecesNumber = pieceDao.countSavedPieces(gameId);
        return savedPiecesNumber == BOARD_CELLS_NUMBER;
    }

    public void startNewGame(Board board, String gameId) throws SQLException {
        deleteSavedBoardStatus(gameId);
        board.initialize();

        List<ChessPiece> chessPieces = Position.stream()
                .map(position -> {
                    Piece piece = board.getPieceByPosition(position);
                    return new ChessPiece(gameId, position.name(), piece.name());
                })
                .collect(Collectors.toList());

        pieceDao.addInitialPieces(chessPieces);
    }

    public void resumeGame(Board board, String gameId) throws SQLException {
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

        board.resume(savedBoardStatus, lastTurn);
    }

    public void move(Board board, String gameId, String sourceName, String targetName) throws SQLException {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        PieceColor currentTeam = board.getTeamColor();

        board.move(source, target);

        pieceDao.updatePiece(gameId, source, board.getPieceByPosition(source));
        pieceDao.updatePiece(gameId, target, board.getPieceByPosition(target));
        moveHistoryDao.addMoveHistory(gameId, currentTeam, source, target);
    }

    public String provideWinner(Board board, String gameId) throws SQLException {
        if (board.isGameOver()) {
            deleteSavedBoardStatus(gameId);
            return winningMsg(board);
        }
        return null;
    }

    public Map<String, Object> provideGameInfo(Board board) {
        Map<String, Object> gameInfo = new HashMap<>();

        gameInfo.put("pieces", convertPieces(board));
        gameInfo.put("turn", turnMsg(board));
        gameInfo.put("white_score", calculateScore(board, PieceColor.WHITE));
        gameInfo.put("black_score", calculateScore(board, PieceColor.BLACK));

        return gameInfo;
    }

    private void deleteSavedBoardStatus(String gameId) throws SQLException {
        pieceDao.deleteBoardStatus(gameId);
        moveHistoryDao.deleteMoveHistory(gameId);
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
        return team.name() + "의 순서입니다.";
    }

    private double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }

    private String winningMsg(Board board) {
        PieceColor winner = board.getTeamColor().changeTeam();
        return winner + "이/가 이겼습니다.";
    }
}
