package GRUD.base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.example.GRUD.OrderGrud;
import org.example.config.BaseConfig;
import org.example.dto.OrderModel;
import org.example.dto.PetModel;
import org.example.GRUD.PetGrud;
import org.example.utils.ResponseWrapper;

public class BaseTest {

    /**
     * Экземпляр интерфейса с конфигурацией
     */
    private final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getenv());

    /**
     * Экземпляр класса с REST шагами
     */
    protected final PetGrud petGrud = new PetGrud(getRequestSpecification());
    protected final OrderGrud orderGrud = new OrderGrud(getRequestSpecification());

    /**
     * Экземпляр класса с телом запроса
     */
    protected PetModel petRequest;
    protected OrderModel orderRequest;

    /**
     * Экземпляр класса с оболочкой ответа
     */
    protected ResponseWrapper responseWrapper;

    /**
     * Метод для получения спецификации RestAssured
     * <p>
     * baseUrl - базовый URL
     * contentType - параметр в header со значением JSON
     * accept - параметр в header со значением JSON
     * filter - создает фильтр для allure
     * log - логирование всех деталей
     *
     * @return спецификация
     */
    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }
}
