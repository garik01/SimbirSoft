package org.example.GRUD;

import io.restassured.specification.RequestSpecification;
import org.example.GRUD.base.BaseGrud;
import org.example.dto.PetModel;
import org.example.utils.ResponseWrapper;

import static io.restassured.RestAssured.given;

/**
 * Класс с реализацией всех Rest шагов
 */
public class PetGrud extends BaseGrud<PetModel> {

    /**
     * Часть URL для запросов /pet
     */
    private static final String PET_PATH = "pet";

    /**
     * Часть URL для запросов /findByStatus
     */
    private static final String FIND_BY_STATUS_PATH = "pet/findByStatus";

    /**
     * Параметр status, get запроса findByStatus
     */
    private static final String STATUS_PARAMETER = "status";

    /**
     * Конструктор для создания экземпляра класса
     *
     * @param requestSpecification спецификация RestAssured
     */
    public PetGrud(RequestSpecification requestSpecification) {
        super(requestSpecification);
    }

    /**
     * Метод создания питомца
     *
     * @param request тело запроса
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper create(PetModel request) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(PET_PATH)
                .andReturn());
    }

    /**
     * Метод поиска питомца по id
     *
     * @param id id домашнего питомца
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper read(String id) {
        String petByIdPath = PET_PATH + "/" + id;
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .get(petByIdPath)
                .andReturn());
    }

    /**
     * Метод обновления питомца по id
     *
     * @param request тело запроса
     * @param id id питомца
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper update(String id, PetModel request) {
        request.setId(id);
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .body(request)
                .post(PET_PATH)
                .andReturn());
    }

    /**
     * Метод удаления питомца по id
     *
     * @param id id питомца
     * @return оболочка для работы с ответом
     */
    @Override
    public ResponseWrapper delete(String id) {
        String petByIdPath = PET_PATH + "/" + id;
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .delete(petByIdPath)
                .andReturn());
    }

    /**
     * Метод поиска питомцев по статусу
     *
     * @param status статус
     * @return оболочка для работы с ответом
     */
    public ResponseWrapper getPetByStatus(String status) {
        return new ResponseWrapper(given(requestSpecification)
                .when()
                .param(STATUS_PARAMETER, status)
                .get(FIND_BY_STATUS_PATH)
                .andReturn());
    }
}
