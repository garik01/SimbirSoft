package GRUD.pet;

import GRUD.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.dto.PetModel;
import org.example.dto.response.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.utils.expected_objects.ExpectedErrorsBuilder.*;
import static org.example.utils.TestDataHelper.*;
import static org.example.utils.TestObjectBuilder.getAddNewPetModel;

/**
 * Тест сьют метода DELETE /pet
 */
@Epic("Pet контроллер")
@Feature("Delete pet to store by id")
public class DeletePetById extends BaseTest {
    @Test
    @DisplayName("Delete pet by id. Positive case")
    @Story("Удаление питомца по id, позитивный сценарий")
    public void testDeletePetByIdPositive() {
        step("Создание тела запроса с валидным ID", () -> {
            petRequest = getAddNewPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса POST /pet", () -> {
            responseWrapper = petGrud.create(petRequest);
        });

        step("Выполнение запроса DELETE /pet/{petId}", () -> {
            petRequest.setId(responseWrapper.as(PetModel.class).getId());
            responseWrapper = petGrud.delete(petRequest.getId());
        });

        step("Выполнение чтения только что удаленного pet", () -> {
            responseWrapper = petGrud.read(responseWrapper.as(ErrorResponse.class).getMessage());
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            ErrorResponse response = responseWrapper.as(ErrorResponse.class);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_NOT_FOUND);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(getPetNotFoundResponse());
                    }
            );
        });
    }

    @Test
    @DisplayName("Delete pet by id. Negative case")
    @Story("Удаление питомца по id, негативный сценарий")
    public void testDeletePetByIdNegative() {
        step("Создание тела запроса с невалидным ID", () -> {
            petRequest = getAddNewPetModel(NOT_VALID_PET_ID);
        });

        step("Выполнение запроса DELETE /pet/{petId}", () -> {
            responseWrapper = petGrud.delete(petRequest.getId());
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
