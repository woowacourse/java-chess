package chess.controller;

import chess.dao.BoardDao;
import chess.dao.RecordDao;
import chess.domains.Record;
import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebController {
    public static final String START_COMMAND = "start";
    public static final String MOVE_COMMAND = "move ";
    public static final String GAME_END_MESSAGE = "게임이 종료되었습니다.";
    public static final String TURN_MESSAGE = "의 순서입니다.";
    public static final String WINNER_MESSAGE = "의 승리";

    private static final BoardDao boardDAO = new BoardDao();
    private static final RecordDao recordDAO = new RecordDao();

    public static String game(Board board) {
        board.initialize();
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public static String startGame(Board board) {
        board.initialize();

        boardDAO.clearBoard();
        boardDAO.addBoard(board.getBoard());

        recordDAO.clearRecord();
        recordDAO.addRecord(new Record(START_COMMAND, ""));

        Map<String, Object> model = new HashMap<>();
        model.put("records", recordDAO.readRecords());
        model.put("pieces", boardDAO.showPieces());
        model.put("turn", WebController.printTurn(WebController.turn(board)));
        model.put("white_score", WebController.calculateScore(board, PieceColor.WHITE));
        model.put("black_score", WebController.calculateScore(board, PieceColor.BLACK));

        return render(model, "index.html");
    }

    public static String move(Board board, String source, String target) {
        movePiece(board, source, target);
        endGame(board);

        Map<String, Object> model = new HashMap<>();
        model.put("records", recordDAO.readRecords());
        model.put("end", board.isGameOver());
        model.put("pieces", boardDAO.showPieces());
        model.put("turn", WebController.printTurn(WebController.turn(board)));
        model.put("white_score", WebController.calculateScore(board, PieceColor.WHITE));
        model.put("black_score", WebController.calculateScore(board, PieceColor.BLACK));

        return render(model, "index.html");
    }

    private static void movePiece(Board board, String source, String target) {
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

    public static String resumeGame(Board board) {
        board.initialize();
        List<Record> records = recordDAO.readRecords();
        board.recoverRecords(records);

        Map<String, Object> model = new HashMap<>();
        model.put("records", recordDAO.readRecords());
        model.put("pieces", boardDAO.showPieces());
        model.put("turn", WebController.printTurn(WebController.turn(board)));
        model.put("white_score", WebController.calculateScore(board, PieceColor.WHITE));
        model.put("black_score", WebController.calculateScore(board, PieceColor.BLACK));

        return render(model, "index.html");
    }

    private static void endGame(Board board) {
        if (board.isGameOver()) {
            String winner = board.getTeamColor().changeTeam().name();
            recordDAO.addRecord(new Record(GAME_END_MESSAGE, winner + WINNER_MESSAGE));
        }
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

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}