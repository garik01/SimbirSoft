package org.example.utils.expected_objects;

import org.example.dto.response.ErrorResponse;

import static org.example.utils.TestDataHelper.STATUS_CODE_ERROR_500;
import static org.example.utils.TestDataHelper.STATUS_CODE_OK;

public class ExpectedErrorsBuilder {

    /**
     * Тип неизвестной ошибки
     */
    private static final String UNKNOWN_TYPE = "unknown";

    private static final String ERROR_TYPE = "error";

    /**
     * Сообщение неизвестной ошибки
     */
    private static final String UNKNOWN_MESSAGE = "something bad happened";

    private static final String PET_NOT_FOUND_MESSAGE = "Pet not found";

    private static final String ORDER_NOT_FOUND_MESSAGE = "Order not found";

    /**
     * Метод формирования ожидаемого результата неизвестой ошибки
     *
     * @return тело ошибки
     */
    public static ErrorResponse getUnknownErrorResponse(){
        return ErrorResponse.builder()
                .code(STATUS_CODE_ERROR_500)
                .type(UNKNOWN_TYPE)
                .message(UNKNOWN_MESSAGE)
                .build();
    }

    /**
     * Метод формирования ожидаемого результата не найденного домашнего питомца
     *
     * @return тело ошибки
     */
    public static ErrorResponse getPetNotFoundResponse() {
        return ErrorResponse.builder()
                .code(1)
                .type(ERROR_TYPE)
                .message(PET_NOT_FOUND_MESSAGE)
                .build();
    }

    /**
     * Метод формирования ожидаемого результата успешного удаления
     *
     * @param petId id домашнего питомца
     * @return тело ошибки
     */
    public static ErrorResponse getDeleteOkResponse(String petId) {
        return ErrorResponse.builder()
                .code(STATUS_CODE_OK)
                .type(UNKNOWN_TYPE)
                .message(petId)
                .build();
    }

    /**
     * Метод формирования ожидаемого результата не найденной записи
     *
     * @return тело ошибки
     */
    public static ErrorResponse getOrderNotFoundResponse() {
        return ErrorResponse.builder()
                .code(1)
                .type(ERROR_TYPE)
                .message(ORDER_NOT_FOUND_MESSAGE)
                .build();
    }
}
