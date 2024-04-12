package org.example.GRUD;

import io.restassured.specification.RequestSpecification;
import org.example.GRUD.base.BaseGrud;
import org.example.dto.OrderModel;
import org.example.utils.ResponseWrapper;

import static io.restassured.RestAssured.given;

public class OrderGrud extends BaseGrud<OrderModel> {

    /**
     * Часть URL для запросов /store/order
     */
    private static final String STORE_ORDER_PATH = "store/order";

    /**
     * Часть URL для запросов /store/order/
     */
    private static final String STORE_ORDER_PATH_WITH_SLASH = STORE_ORDER_PATH + "/";

    /**
     * Конструктор для создания экземпляра класса
     *
     * @param requestSpecification спецификация RestAssured
     */
    public OrderGrud(RequestSpecification requestSpecification) {
        super(requestSpecification);
    }

    /**
     * Метод создания заявки
     *
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper create(OrderModel request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(STORE_ORDER_PATH)
                .andReturn());
    }

    /**
     * Метод поиска заявки по id
     *
     * @param id id заявки
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper read(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .get(STORE_ORDER_PATH_WITH_SLASH)
                .andReturn());
    }

    /**
     * Метод обновления заявки по id
     *
     * @param id id записи
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper update(String id, OrderModel request) {
        request.setId(id);
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(STORE_ORDER_PATH)
                .andReturn());
    }

    /**
     * Метод удаления заявки по id
     *
     * @param id id записи
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper delete(String id) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .delete(STORE_ORDER_PATH_WITH_SLASH)
                .andReturn());
    }
}
