package chess.service;

import chess.dao.PieceDao;
import chess.domains.board.Board;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChessWebService {
    private static final int BOARD_CELLS = 64;
    private static final int BOARD_CELLS_COUNT = BOARD_CELLS;

    private final PieceDao dao;

    public ChessWebService(PieceDao dao) {
        this.dao = dao;
    }

    public boolean canContinue(String user_id) throws SQLException {
        int savedCount = dao.countSavedInfo(user_id);
        return savedCount == BOARD_CELLS_COUNT;
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

    public void startNewGame(Board board) {
        board.initialize();

        Position.stream()
                .forEach(position -> {
                    try {
                        dao.addPiece("guest", position, board.getPieceByPosition(position));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
    }

    public String turnMsg(Board board) {
        String turn = board.getTeamColor().name();
        return turn + "의 순서입니다.";
    }

    public void move(Board board, String source, String target) throws SQLException {
        Position sourcePosition = Position.ofPositionName(source);
        Position targetPosition = Position.ofPositionName(target);

        board.move(sourcePosition, targetPosition);

        dao.updatePiece("guest", sourcePosition, board.getPieceByPosition(sourcePosition));
        dao.updatePiece("guest", targetPosition, board.getPieceByPosition(targetPosition));
    }

    public boolean isGameOver(Board board) {
        return board.isGameOver();
    }

    public double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }

    public void deleteSaved() throws SQLException {
        dao.deleteSaved("guest");
    }

    public List<String> convertPieces(Board board) {
        List<Piece> pieces = board.showBoard();
        return convertView(pieces);
    }

    public String winningMsg(Board board) {
        PieceColor winner = board.getTeamColor().changeTeam();
        return winner + "이/가 이겼습니다.";
    }
}
