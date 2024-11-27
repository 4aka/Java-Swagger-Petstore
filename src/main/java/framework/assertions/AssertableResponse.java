package framework.assertions;

import framework.conditions.Condition;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static io.restassured.RestAssured.rootPath;

@Log4j2
@RequiredArgsConstructor
public class AssertableResponse {
    private final Response response;

    public AssertableResponse shouldHave(Condition condition) {
        log.info("About to check condition {}", condition);
        condition.check(response);
        return this;
    }

    public <T> T asPojo(Class<T> tClass){
        log.info("extract pojo");
        return response.as(tClass);
    }

    public <T> List<T> getBodyAsList(Class<T> aClass){
        log.info("extract pojo");
        JsonPath jsonPath = response.getBody().jsonPath();
        return jsonPath.getList(rootPath, aClass);
    }

    public Headers headers(){
        return response.getHeaders();
    }
}
