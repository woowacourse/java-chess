package chess.controller;

import chess.dao.BoardDao;
import chess.dao.CommandHistoryDao;
import chess.domains.CommandHistory;
import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.domains.position.Position;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class WebController {
    public static final String GAME_END_MESSAGE = "게임이 종료되었습니다.";
    public static final String TURN_MESSAGE = "의 순서입니다.";
    public static final String WINNER_MESSAGE = "의 승리";
    public static final String EMPTY_STRING = "";

    private final BoardDao boardDAO;
    private final CommandHistoryDao commandHistoryDAO;
    private Board board;

    public WebController() {
        this.boardDAO = new BoardDao();
        this.commandHistoryDAO = new CommandHistoryDao();
        this.board = new Board();
    }

    public String game() {
        board.initialize();
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public String startGame() {
        board.initialize();

        boardDAO.deleteBoard();
        boardDAO.createBoard(board.getBoard());

        commandHistoryDAO.deleteRecord();
        commandHistoryDAO.createRecord(new CommandHistory(Command.START.getCommand(), EMPTY_STRING, EMPTY_STRING, EMPTY_STRING));

        Map<String, Object> model = new HashMap<>();
        model.put("records", commandHistoryDAO.readRecords());
        model.put("pieces", boardDAO.readBoard());
        model.put("turn", printTurn(turn()));
        model.put("white_score", calculateScore(PieceColor.WHITE));
        model.put("black_score", calculateScore(PieceColor.BLACK));

        return render(model, "index.html");
    }

    public String move(Request req) {
        String source = req.queryParams("source");
        String target = req.queryParams("target");
        movePiece(board, source, target);
        endGame();

        Map<String, Object> model = new HashMap<>();
        model.put("records", commandHistoryDAO.readRecords());
        model.put("end", board.isGameOver());
        model.put("pieces", boardDAO.readBoard());
        model.put("turn", printTurn(turn()));
        model.put("white_score", calculateScore(PieceColor.WHITE));
        model.put("black_score", calculateScore(PieceColor.BLACK));

        return render(model, "index.html");
    }

    private void movePiece(Board board, String source, String target) {
        CommandHistory move = new CommandHistory(Command.MOVE.getCommand(), source, target, "");

        try {
            Position sourcePosition = Position.ofPositionName(source);
            Position targetPosition = Position.ofPositionName(target);
            board.move(sourcePosition, targetPosition);
            boardDAO.updateBoard(source, board.findPieceByPosition(source));
            boardDAO.updateBoard(target, board.findPieceByPosition(target));
        } catch (Exception e) {
            move.setErrorMsg(e.getMessage());
        }

        commandHistoryDAO.createRecord(move);
    }

    public String resumeGame() {
        board.initialize();
        Map<String, String> previousBoard = boardDAO.readBoard();
        board.recoverBoard(previousBoard);

        Map<String, Object> model = new HashMap<>();
        model.put("commandHistories", commandHistoryDAO.readRecords());
        model.put("pieces", boardDAO.readBoard());
        model.put("turn", printTurn(turn()));
        model.put("white_score", calculateScore(PieceColor.WHITE));
        model.put("black_score", calculateScore(PieceColor.BLACK));

        return render(model, "index.html");
    }

    private void endGame() {
        if (board.isGameOver()) {
            String winner = board.getTeamColor().changeTeam().name();
            commandHistoryDAO.createRecord(new CommandHistory(GAME_END_MESSAGE, EMPTY_STRING, EMPTY_STRING, winner + WINNER_MESSAGE));
        }
    }

    public String printTurn(String turn) {
        return turn + TURN_MESSAGE;
    }

    public String turn() {
        return board.getTeamColor().name();
    }

    public double calculateScore(PieceColor pieceColor) {
        return board.calculateScore(pieceColor);
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}