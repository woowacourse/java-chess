package chess.service;

import chess.dao.MoveHistoryDao;
import chess.dao.PieceDao;
import chess.domains.board.Board;
import chess.domains.board.BoardFactory;
import chess.domains.piece.Piece;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public boolean canResume(String gameId) {
        int savedPiecesNumber = pieceDao.countSavedPieces(gameId);
        return savedPiecesNumber == BOARD_CELLS_NUMBER;
    }

    public void startNewGame(Board board, String gameId) {
        deleteSaved(gameId);

        board.initialize();

        Position.stream()
                .forEach(position -> pieceDao.addPiece(gameId, position, board.getPieceByPosition(position)));
    }

    public void resumeGame(Board board, String gameId) {
        Map<Position, Piece> savedBoard = Position.stream()
                .collect(Collectors.toMap(Function.identity(), position -> {
                    String pieceName = pieceDao.findPieceNameByPosition(gameId, position);
                    return BoardFactory.findPieceByPieceName(pieceName);
                }));

        Optional<String> lastTurn = moveHistoryDao.figureLastTurn(gameId);

        board.resume(savedBoard, lastTurn);
    }

    public String turnMsg(Board board) {
        String turn = board.getTeamColor().name();
        return turn + "의 순서입니다.";
    }

    public void move(Board board, String gameId, String sourceName, String targetName) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        PieceColor currentTeam = board.getTeamColor();

        board.move(source, target);

        pieceDao.updatePiece(gameId, source, board.getPieceByPosition(source));
        pieceDao.updatePiece(gameId, target, board.getPieceByPosition(target));
        moveHistoryDao.addMoveHistory(gameId, currentTeam, source, target);
    }

    public boolean isGameOver(Board board) {
        return board.isGameOver();
    }

    public double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }

    public void deleteSaved(String gameId) {
        pieceDao.deleteBoardStatus(gameId);
        moveHistoryDao.deleteMoveHistory(gameId);
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
