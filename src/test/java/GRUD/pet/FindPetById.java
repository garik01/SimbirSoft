package GRUD.pet;

import GRUD.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.dto.PetModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.utils.TestDataHelper.*;
import static org.example.utils.TestObjectBuilder.getAddNewPetModel;

/**
 * Тест сьют метода GET /pet
 */
@Epic("Pet контроллер")
@Feature("Find pet to store by id")
public class FindPetById extends BaseTest {
    @Test
    @DisplayName("Find pet by id. Positive case")
    @Story("Поиск питомцев по id, позитивный сценарий")
    public void testFindPetByIdPositive() {
        step("Создание тела запроса с валидным ID", () -> {
            petRequest = getAddNewPetModel(VALID_PET_ID);
        });

        step("Выполнение запроса POST /pet", () -> {
            responseWrapper = petGrud.create(petRequest);
        });

        petRequest.setId(responseWrapper.as(PetModel.class).getId());

        step("Выполнение запроса GET /pet/{petId}", () -> {
            responseWrapper = petGrud.read(petRequest.getId());
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();
            PetModel response = responseWrapper.as(PetModel.class);

            assertSoftly(
                    softAssertions -> {
                        softAssertions
                                .assertThat(statusCode)
                                .withFailMessage("Status code doesn't match")
                                .isEqualTo(STATUS_CODE_OK);
                        softAssertions
                                .assertThat(response)
                                .withFailMessage("Response body doesn't match")
                                .isEqualTo(petRequest);
                    }
            );
        });
    }

    @Test
    @DisplayName("Find pet by id. Negative case with not valid id")
    @Story("Поиск питомца с не валидным id, негативный сценарий")
    public void testFindPetByIdNegative() {
        step("Создание тела запроса с не валидным ID", () -> {
            petRequest = getAddNewPetModel(NOT_VALID_PET_ID);
        });

        step("Выполнение запроса GET /pet/{petId}", () -> {
            responseWrapper = petGrud.read(petRequest.getId());
        });

        step("Проверка результата", () -> {
            int statusCode = responseWrapper.getStatusCode();

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
