package chess.controller;

import chess.dao.BoardDAO;
import chess.dao.RecordDAO;
import chess.domains.Record;
import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebController {

    public static final String START_COMMAND = "start";
    public static final String MOVE_COMMAND = "move ";
    public static final String GAME_END_MESSAGE = "게임이 종료되었습니다.";
    public static final String TURN_MESSAGE = "의 순서입니다.";
    public static final String WINNER_MESSAGE = "의 승리";

    public static void startGame(Board board, BoardDAO boardDAO, RecordDAO recordDAO) throws SQLException {
        board.initialize();

        boardDAO.clearBoard();
        boardDAO.addBoard(board.getBoard());

        recordDAO.clearRecord();
        recordDAO.addRecord(new Record(START_COMMAND, ""));
    }

    public static void movePiece(Board board, String source, String target) throws SQLException {
        BoardDAO boardDAO = new BoardDAO();
        RecordDAO recordDAO = new RecordDAO();
        Record move = new Record(MOVE_COMMAND + source + " " + target, "");

        try {
            Position sourcePosition = Position.ofPositionName(source);
            Position targetPosition = Position.ofPositionName(target);
            board.move(sourcePosition, targetPosition);
            boardDAO.updateBoard(source, board.findPieceByPosition(source));
            boardDAO.updateBoard(target, board.findPieceByPosition(target));
        } catch (Exception e) {
            move.setErrorMsg(e.getMessage());
        }

        recordDAO.addRecord(move);
    }

    public static void resumeGame(Board board, RecordDAO recordDAO) throws SQLException {
        board.initialize();
        List<Record> records = recordDAO.readRecords();
        board.recoverRecords(records);
    }

    public static void endGame(Board board) throws SQLException {
        RecordDAO recordDAO = new RecordDAO();
        if (board.isGameOver()) {
            String winner = board.getTeamColor().changeTeam().name();
            recordDAO.addRecord(new Record(GAME_END_MESSAGE, winner + WINNER_MESSAGE));
        }
    }


    public static Map<String, String> convertView(Map<String, String> board) {
        Map<String, String> pieces = new HashMap<>();
        for (String position : board.keySet()) {
            switch (board.get(position)) {
                case "r":
                    pieces.put(position, "♖");
                    break;
                case "n":
                    pieces.put(position, "♘");
                    break;
                case "b":
                    pieces.put(position, "♗");
                    break;
                case "k":
                    pieces.put(position, "♔");
                    break;
                case "q":
                    pieces.put(position, "♕");
                    break;
                case "p":
                    pieces.put(position, "♙");
                    break;
                case "R":
                    pieces.put(position, "♜");
                    break;
                case "N":
                    pieces.put(position, "♞");
                    break;
                case "B":
                    pieces.put(position, "♝");
                    break;
                case "K":
                    pieces.put(position, "♚");
                    break;
                case "Q":
                    pieces.put(position, "♛");
                    break;
                case "P":
                    pieces.put(position, "♟");
                    break;
                case ".":
                    pieces.put(position, "");
                    break;
            }
        }
        return pieces;
    }

    public static String printTurn(String turn) {
        return turn + TURN_MESSAGE;
    }

    public static String turn(Board board) {
        return board.getTeamColor().name();
    }

    public static double calculateScore(Board board, PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }
}
