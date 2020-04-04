package chess;

import chess.dao.BoardDAO;
import chess.dao.SQLBoardDAO;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.judge.Judge;
import chess.domain.judge.WoowaJudge;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.exceptions.InvalidInputException;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.*;

import static java.util.stream.Collectors.toMap;
import static spark.Spark.*;

//할 것들:
// 1. JS에서 addEventListener()
// 2. addEventLister()안에서 submit()으로 form post하기
// 3. form 안보이게 css로 숨기기
public class WebUIChessApplication {
    private static BoardDAO boardDao = new SQLBoardDAO();
    //private static BoardDAO boardDao = FakeBoardDAO.initialFakeBoardDAO();
    private static Board board = new Board(boardDao);

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = generateBlankModel();
            model.putAll(generateMovableModel(new ArrayList<>()));
            return render(model, "index.html");
        });

        post("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            if (req.queryParams("command").equals("새 게임")) {
                boardDao.placeInitialPieces();
                board = new Board(boardDao);
                model = parseBoard(board);
            }

            if (req.queryParams("command").equals("불러오기")) {
                board = new Board(boardDao);
                model = parseBoard(board);
            }

            if (req.queryParams("command").equals("현재점수")) {
                Judge judge = new WoowaJudge(board);
                model = parseBoard(board);
                model.put("player1_info", "사용자1" + "(White) : " + judge.calculateScore(Side.WHITE));
                model.put("player2_info", "사용자2" + "(Black) : " + judge.calculateScore(Side.BLACK));
            }

            if (req.queryParams("command").equals("이동")) {
                Position start = Position.of(req.queryParams("start"));
                Position end = Position.of(req.queryParams("end"));
                tryMove(start, end, board);
                model = parseBoard(board);
            }

            if (req.queryParams("command").equals("이동체크")) {
                Position start = Position.of(req.queryParams("start"));
                List<Position> movablePositions = tryFindMovablePositions(start, board);
                model = parseBoard(board, movablePositions);
                if (movablePositions.size() != 0) {
                    model.put("start", req.queryParams("start"));
                }
            }

            return render(model, "index.html");
        });
    }

    private static void tryMove(Position start, Position end, Board board) throws SQLException {
        try {
            board.move(start, end);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Position> tryFindMovablePositions(Position start, Board board) throws SQLException {
        try {
            return board.findMovablePositions(start);
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Map<String, Object> generateBlankModel() {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(Position::toString, position -> "blank"));
    }

    private static Map<String, Object> parseBoard(Board board) throws SQLException {
        Map<String, Object> output = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            Optional<Piece> piece = board.findPieceOn(position);
            output.put(position.toString(), parsePiece(piece));
        }

        output.putAll(generateMovableModel(new ArrayList<>()));

        return output;
    }

    private static Map<String, Object> parseBoard(Board board, List<Position> movablePlaces) throws SQLException {
        Map<String, Object> output = new HashMap<>();

        for (Position position : Position.getAllPositions()) {
            Optional<Piece> piece = board.findPieceOn(position);
            output.put(position.toString(), parsePiece(piece));
        }

        output.putAll(generateMovableModel(movablePlaces));

        return output;
    }

    private static String parsePiece(Optional<Piece> piece) {
        if (piece.isPresent()) {
            return piece.get().toString();
        }

        return "blank";
    }

    private static Map<String, Object> generateMovableModel(List<Position> markingPlaces) {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(position -> parseMarkPosition(position), position -> parseMovable(position, markingPlaces)));
    }

    private static String parseMarkPosition(Position position) {
        return position.toString() + "_mark";
    }

    private static String parseMovable(Position position, List<Position> positions) {
        if (positions.contains(position)) {
            return "movable";
        }
        return "blank";
    }
}
