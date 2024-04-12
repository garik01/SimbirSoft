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
import static org.example.utils.expected_objects.ExpectedErrorsBuilder.*;
import static org.example.utils.TestDataHelper.*;
import static org.example.utils.TestObjectBuilder.getAddNewOrderModel;
import static org.example.utils.TestObjectBuilder.getAddNewPetModel;

/**
 * Тест сьют метода DELETE /store/order
 */
@Epic("Order контроллер")
@Feature("Delete order to store")
public class DeleteOrderById extends BaseTest {

    @Test
    @DisplayName("Delete order by id. Positive case")
    @Story("Удаление заявки на приобритение домашнего животного по id, позитивный сценарий")
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

        step("Выполнение запроса DELETE /store/order/{orderId}", () -> {
            responseWrapper = orderGrud.delete(responseWrapper.as(OrderModel.class).getId());
        });

        step("Выполнение чтения только что удаленного order", () -> {
            responseWrapper = orderGrud.read(orderRequest.getId());
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            ErrorResponse response = responseWrapper.as(ErrorResponse.class);

            System.out.println(response);
            System.out.println();

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_NOT_FOUND);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(getOrderNotFoundResponse());
                    }
            );
        });
    }

    @Test
    @DisplayName("Delete order by id. Negative case")
    @Story("Удаление заявки на приобритение домашнего животного по id, негативный сценарий")
    public void testDeletePetByIdNegative() {
        step("Создание тела запроса с невалидным ID", () -> {
            orderRequest = getAddNewOrderModel(NOT_VALID_PET_ID, NOT_VALID_PET_ID, VALID_QUANTITY);
        });

        step("Выполнение запроса DELETE /pet/{petId}", () -> {
            responseWrapper = orderGrud.delete(orderRequest.getId());
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            ErrorResponse response = responseWrapper.as(ErrorResponse.class);

            System.out.println(response.toString());
            System.out.println(getUnknownErrorResponse());

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_NOT_FOUND);
                    }
            );
        });
    }
}
