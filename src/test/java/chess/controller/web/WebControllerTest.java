package chess.controller.web;

import chess.controller.web.dto.MoveRequestDto;
import chess.controller.web.dto.SaveRequestDto;
import chess.dao.MysqlChessDao;
import chess.domain.board.Square;
import chess.domain.manager.ChessGameManager;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import spark.Spark;

import java.io.IOException;

import static chess.domain.piece.attribute.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

class WebControllerTest {
    private final Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        port(8081);
        staticFileLocation("static");
        WebController.start();
    }

    @AfterEach
    void shutDown() {
        Spark.stop();
        Spark.awaitStop();
    }

    @DisplayName("체스게임을 시작하는 /game/start 요청 테스트")
    @Test
    void whenCallStartRestApi() throws IOException {
        // Given
        // When
        JsonObject basicResponseDto = callStartRestApi();
        boolean isError = basicResponseDto.get("isError").getAsBoolean();

        // Then
        assertThat(isError).isFalse();
    }

    private JsonObject callStartRestApi() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8081/game/start");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseEntity = EntityUtils.toString(response.getEntity());

        return gson.fromJson(responseEntity, JsonObject.class);
    }

    @DisplayName("체스말을 움직이는 /game/move 요청 테스트")
    @Test
    void whenCallMoveRestApi() throws IOException {
        // Given
        callStartRestApi();

        // When
        JsonObject basicResponseDto = callMoveRestApi("a2", "a3");
        boolean isError = basicResponseDto.get("isError").getAsBoolean();

        // Then
        assertThat(isError).isFalse();
    }

    private JsonObject callMoveRestApi(String from, String to) throws IOException {
        HttpPost request = new HttpPost("http://localhost:8081/game/move");
        request.setHeader("Content-Type", "application/json;charset=utf-8");

        MoveRequestDto moveRequestDto = new MoveRequestDto(from, to, "1");
        String jsonValue = gson.toJson(moveRequestDto);
        HttpEntity httpEntity = new StringEntity(jsonValue, "utf-8");
        request.setEntity(httpEntity);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        String responseEntity = EntityUtils.toString(response.getEntity());

        return gson.fromJson(responseEntity, JsonObject.class);
    }

    @DisplayName("/game/move API 요청시 이동할 수 없으면 json의 isError가 true가 되는지")
    @ParameterizedTest
    @CsvSource({"a1,a2,동일한 진영의 말이 있어서 행마할 수 없습니다.", "a3,a4,현재 움직일 수 있는 진영의 기물이 아닙니다.", "a2,a5,폰이 움직일 수 있는 범위를 벗어났습니다."})
    void whenCantMoveReturnErrorJson(String from, String to, String expectedErrorMsg) throws IOException {
        // Given
        callStartRestApi();

        // When
        JsonObject errorJson = callMoveRestApi(from, to);

        // Then
        assertThat(errorJson.get("errorMsg").getAsString()).isEqualTo(expectedErrorMsg);
    }

    @DisplayName("/game/score API 요청 시 칼라별 점수를 알 수 있는지 확인")
    @Test
    void whenCallGetScoreApi() throws IOException {
        // given
        callStartRestApi();
        // when
        HttpGet request = new HttpGet("http://localhost:8081/game/score");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseEntity = EntityUtils.toString(response.getEntity());
        JsonObject responseJson = gson.fromJson(responseEntity, JsonObject.class);
        JsonObject scoreResponseJson = responseJson.get("data").getAsJsonObject();

        String matchResult = scoreResponseJson.get("matchResult").getAsString();
        JsonObject colorsScore = scoreResponseJson.get("colorsScore").getAsJsonObject();

        double blackScore = colorsScore.get("BLACK").getAsDouble();
        double whiteScore = colorsScore.get("WHITE").getAsDouble();

        // then
        assertThat(matchResult).isEqualTo("무승부");
        assertThat(blackScore).isEqualTo(38.0);
        assertThat(whiteScore).isEqualTo(38.0);
    }

    @DisplayName("/game/save API 요청 시 저장을 할 수 있는지 확인")
    @Test
    void whenCallSaveApi() throws IOException {
        // given
        callStartRestApi();
        MysqlChessDao dao = new MysqlChessDao();
        dao.deleteAll();

        // when
        HttpPost request = new HttpPost("http://localhost:8081/game/save");
        request.setHeader("Content-Type", "application/json;charset=utf-8");

        SaveRequestDto saveRequestDto = new SaveRequestDto(0,"RKBQKBKRPPPPPPPP........................p........ppppppprkbqkbkr");
        String jsonValue = gson.toJson(saveRequestDto);
        HttpEntity httpEntity = new StringEntity(jsonValue, "utf-8");
        request.setEntity(httpEntity);
        HttpClientBuilder.create().build().execute(request);

        ChessGameManager expectedGameManager = dao.findById(1L).get();
        // then
        assertThat(expectedGameManager.getId()).isEqualTo(1);

        Square a3 = expectedGameManager.getBoard().findByPosition(Position.of("a3"));
        assertThat(a3.getPiece().getClass()).isEqualTo(Pawn.class);
        assertThat(a3.getPiece().getColor()).isEqualTo(WHITE);
    }
}