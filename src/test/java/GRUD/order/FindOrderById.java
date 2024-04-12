package GRUD.order;

import GRUD.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.dto.OrderModel;
import org.example.dto.PetModel;
import org.example.dto.response.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.utils.expected_objects.ExpectedErrorsBuilder.getUnknownErrorResponse;
import static org.example.utils.TestDataHelper.*;
import static org.example.utils.TestObjectBuilder.getAddNewOrderModel;
import static org.example.utils.TestObjectBuilder.getAddNewPetModel;

/**
 * Тест сьют метода GET /store/order
 */
@Epic("Order контроллер")
@Feature("Add new order to store")
public class FindOrderById extends BaseTest {

    @Test
    @DisplayName("Find order by id. Positive case")
    @Story("Поиск заявки на приобритение домашнего животного по id, позитивный сценарий")
    public void testFindOrderByIdPositive() {
        step("Создание тела запроса для создания нового пета", () -> {
            petRequest = getAddNewPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса POST /pet", () -> {
            responseWrapper = petGrud.create(petRequest);
        });

        step("Создание тела запроса с валидным ID", () -> {
            orderRequest = getAddNewOrderModel(VALID_PET_ID, responseWrapper.as(PetModel.class).getId(), VALID_QUANTITY);
        });

        step("Выполнение запроса POST /order", () -> {
            responseWrapper = orderGrud.create(orderRequest);
        });

        step("Выполнение чтения только что созданного order ", () -> {
            responseWrapper = orderGrud.read(responseWrapper.as(OrderModel.class).getId());
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            OrderModel response = responseWrapper.as(OrderModel.class);
            orderRequest.setId(response.getId());
            orderRequest.setShipDate(response.getShipDate());

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(orderRequest);
                    }
            );
        });
    }

    @Test
    @DisplayName("Find order by id. Negative case")
    @Story("Поиск заявки на приобритение домашнего животного по id, негативный сценарий")
    public void testAddNewOrderNegative() {
        step("Создание тела запроса для создания нового пета", () -> {
            petRequest = getAddNewPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса POST /pet", () -> {
            responseWrapper = petGrud.create(petRequest);
        });

        step("Создание тела запроса с невалидным ID", () -> {
            orderRequest = getAddNewOrderModel(NOT_VALID_PET_ID, responseWrapper.as(PetModel.class).getId(), VALID_QUANTITY);
        });

        step("Выполнение запроса POST /order", () -> {
            responseWrapper = orderGrud.create(orderRequest);
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            ErrorResponse response = responseWrapper.as(ErrorResponse.class);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_ERROR_500);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(getUnknownErrorResponse());
                    }
            );
        });
    }
}
