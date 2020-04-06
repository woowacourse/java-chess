package chess.service;

import chess.dao.MoveHistoryDao;
import chess.dao.PieceDao;
import chess.domains.board.Board;
import chess.domains.board.BoardFactory;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessWebService {
    private static final int BOARD_CELLS = 64;
    private static final int BOARD_CELLS_COUNT = BOARD_CELLS;
    private static final String SQL_EXCEPTION_ERR_MSG = "DB에서 정보를 가져오는 중 에러가 발생했습니다.";

    private final PieceDao pieceDao;
    private final MoveHistoryDao moveHistoryDao;

    public ChessWebService(PieceDao pieceDao, MoveHistoryDao moveHistoryDao) {
        this.pieceDao = pieceDao;
        this.moveHistoryDao = moveHistoryDao;
    }

    public boolean canResume(String user_id) throws SQLException {
        int savedCount = pieceDao.countSavedInfo(user_id);
        return savedCount == BOARD_CELLS_COUNT;
    }

    public void startNewGame(Board board, String user_id) {
        board.initialize();

        Position.stream()
                .forEach(position -> {
                    try {
                        pieceDao.addPiece(user_id, position, board.getPieceByPosition(position));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void resumeGame(Board board, String user_id) throws SQLException {
        Map<Position, Piece> savedBoard = Position.stream()
                .collect(Collectors.toMap(Function.identity(), position -> {
                    try {
                        String pieceName = pieceDao.findPieceNameByPosition(user_id, position);
                        return BoardFactory.findPieceByPieceName(pieceName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new RuntimeException(SQL_EXCEPTION_ERR_MSG);
                    }
                }));

        Optional<String> lastTurn = moveHistoryDao.figureLastTurn("guest");

        board.resume(savedBoard, lastTurn);
    }

    public String turnMsg(Board board) {
        String turn = board.getTeamColor().name();
        return turn + "의 순서입니다.";
    }

    public void move(Board board, String user_id, String sourceName, String targetName) throws SQLException {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        PieceColor currentTeam = board.getTeamColor();

        board.move(source, target);

        pieceDao.updatePiece(user_id, source, board.getPieceByPosition(source));
        pieceDao.updatePiece(user_id, target, board.getPieceByPosition(target));
        moveHistoryDao.addMoveHistory(user_id, currentTeam, source, target);
    }

    public boolean isGameOver(Board board) {
        return board.isGameOver();
    }

    public double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }

    public void deleteSaved() throws SQLException {
        pieceDao.deleteSaved("guest");
    }

    public List<String> convertPieces(Board board) {
        List<Piece> pieces = board.showBoard();
        return convertView(pieces);
    }

    public String winningMsg(Board board) {
        PieceColor winner = board.getTeamColor().changeTeam();
        return winner + "이/가 이겼습니다.";
    }

    private static List<String> convertView(List<Piece> pieces) {
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
}
