package chess.controller.web;

import chess.controller.web.dto.MoveRequestDto;
import chess.controller.web.dto.MoveResponseDto;
import chess.controller.web.dto.StartResponseDto;
import com.google.gson.Gson;
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
import spark.Spark;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

class WebControllerTest {
    private Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        port(8081);
        staticFileLocation("static");
        WebController.start();
    }

    @AfterEach
    void shutDown(){
        Spark.stop();
        Spark.awaitStop();
    }

    @DisplayName("체스게임을 시작하는 /game/start 요청 테스트")
    @Test
    void whenCallStartRestApi() throws IOException {
        // Given
        // When
        StartResponseDto startResponseDto = callStartRestApi();

        // Then
        assertThat(startResponseDto).isEqualTo(new StartResponseDto(true));
    }

    private StartResponseDto callStartRestApi() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8081/game/start");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String responseEntity = EntityUtils.toString(response.getEntity());
        return gson.fromJson(responseEntity, StartResponseDto.class);
    }

    @DisplayName("체스말을 움직이는 /game/move 요청 테스트")
    @Test
    void whenCallMoveRestApi() throws IOException {
        // Given
        callStartRestApi();
        HttpPost request = new HttpPost("http://localhost:8081/game/move");
        request.setHeader("Content-Type", "application/json;charset=utf-8");

        MoveRequestDto moveRequestDto = new MoveRequestDto("a2", "a3", "1");
        String jsonValue = gson.toJson(moveRequestDto);
        HttpEntity httpEntity = new StringEntity(jsonValue, "utf-8");
        request.setEntity(httpEntity);

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        String responseEntity = EntityUtils.toString(response.getEntity());
        System.out.println(responseEntity);
        MoveResponseDto moveResponseDto = gson.fromJson(responseEntity, MoveResponseDto.class);

        // Then
        assertThat(moveResponseDto).isEqualTo(new MoveResponseDto(true));
    }
}