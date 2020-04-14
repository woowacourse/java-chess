package chess.service;

import chess.db.ChessPiece;
import chess.db.MoveHistoryDao;
import chess.db.PieceDao;
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

    public boolean canResume(Map<String, Object> model, String gameId) {
        int savedPiecesNumber = pieceDao.countSavedPieces(gameId);
        boolean canResume = savedPiecesNumber == BOARD_CELLS_NUMBER;
        model.put("game_id", gameId);
        model.put("canResume", canResume);
        return canResume;
    }

    public void startNewGame(Map<String, Object> model, Board board, String gameId) {
        deleteSavedBoardStatus(gameId);
        board.initialize();

        List<ChessPiece> chessPieces = Position.stream()
                .map(position -> {
                    Piece piece = board.getPieceByPosition(position);
                    return new ChessPiece(gameId, position.name(), piece.name());
                })
                .collect(Collectors.toList());

        pieceDao.addInitialPieces(chessPieces);

        model.put("game_id", gameId);
        model.put("pieces", convertPieces(board));
        model.put("turn", turnMsg(board));
        model.put("white_score", calculateScore(board, PieceColor.WHITE));
        model.put("black_score", calculateScore(board, PieceColor.BLACK));
    }

    public void deleteSavedBoardStatus(String gameId) {
        pieceDao.deleteBoardStatus(gameId);
        moveHistoryDao.deleteMoveHistory(gameId);
    }

    public void resumeGame(Map<String, Object> model, Board board, String gameId) {
        Map<Position, Piece> savedBoardStatus = Position.stream()
                .collect(Collectors.toMap(Function.identity(), position -> {
                    String pieceName = pieceDao.findPieceNameByPosition(gameId, position);
                    return BoardFactory.findPieceByPieceName(pieceName);
                }));

        Optional<String> lastTurn = moveHistoryDao.figureLastTurn(gameId);

        board.resume(savedBoardStatus, lastTurn);

        model.put("game_id", gameId);
        model.put("pieces", convertPieces(board));
        model.put("turn", turnMsg(board));
        model.put("white_score", calculateScore(board, PieceColor.WHITE));
        model.put("black_score", calculateScore(board, PieceColor.BLACK));
    }

    public String turnMsg(Board board) {
        PieceColor team = board.getTeamColor();
        return team.name() + "의 순서입니다.";
    }

    public void move(Map<String, Object> model, Board board, String gameId, String sourceName, String targetName) {
        Position source = Position.ofPositionName(sourceName);
        Position target = Position.ofPositionName(targetName);
        PieceColor currentTeam = board.getTeamColor();

        board.move(source, target);

        pieceDao.updatePiece(gameId, source, board.getPieceByPosition(source));
        pieceDao.updatePiece(gameId, target, board.getPieceByPosition(target));
        moveHistoryDao.addMoveHistory(gameId, currentTeam, source, target);

        model.put("game_id", gameId);
        model.put("pieces", convertPieces(board));

        if (board.isGameOver()) {
            deleteSavedBoardStatus(gameId);
            model.put("end", winningMsg(board));
            return;
        }

        model.put("turn", turnMsg(board));
        model.put("white_score", calculateScore(board, PieceColor.WHITE));
        model.put("black_score", calculateScore(board, PieceColor.BLACK));
    }

    public List<String> convertPieces(Board board) {
        List<Piece> pieces = board.showBoard();
        return convertView(pieces);
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

    public String winningMsg(Board board) {
        PieceColor winner = board.getTeamColor().changeTeam();
        return winner + "이/가 이겼습니다.";
    }

    public double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }
}
