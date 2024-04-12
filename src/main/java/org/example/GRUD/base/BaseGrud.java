package org.example.GRUD.base;

import io.restassured.specification.RequestSpecification;
import org.example.utils.ResponseWrapper;

public abstract class BaseGrud<T> {
    /**
     * Экземпляр спецификации RestAssured
     */
    protected final RequestSpecification requestSpecification;

    /**
     * Конструктор для создания экземпляра класса
     *
     * @param requestSpecification спецификация RestAssured
     */
    public BaseGrud(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    /**
     * Создание записи в базе данных
     *
     * @param request тело запроса
     * @return тело ответа
     */
    public abstract ResponseWrapper create(T request);

    /**
     * Чтение записи по id
     *
     * @param id id
     * @return тело ответа
     */
    public abstract ResponseWrapper read(String id);

    /**
     * Обновление записи по id
     *
     * @param id id записи
     * @param request тело запроса
     * @return тело ответа
     */
    public abstract ResponseWrapper update(String id, T request);

    /**
     * Удаление записи по id
     *
     * @param id id записи
     * @return тело ответа
     */
    public abstract ResponseWrapper delete(String id);

}
