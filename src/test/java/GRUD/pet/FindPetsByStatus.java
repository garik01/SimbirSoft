package GRUD.pet;

import GRUD.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.dto.PetModel;
import org.example.utils.ResponseWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.utils.TestDataHelper.*;

/**
 * Тест сьют метода POST /pet
 */
@Epic("Pet контроллер")
@Feature("Add new pet to store")
public class FindPetsByStatus extends BaseTest {

    @Test
    @DisplayName("Find pets by status. Positive case")
    @Story("Поиск питомцев по статусу, позитивный сценарий")
    public void testFindPetsByStatusPositive(){
        ResponseWrapper responseWrapper = petGrud.getPetByStatus(VALID_PET_STATUS);

        int statusCode = responseWrapper.getStatusCode();
        List<PetModel> response = responseWrapper.asList(PetModel[].class);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(statusCode)
                            .withFailMessage("Status code doesn't match")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(response)
                            .withFailMessage("Response body is empty")
                            .isNotEmpty();
                }
        );
    }

    @Test
    @DisplayName("Find pets by status. Negative case, if status is not existing")
    @Story("Поиск питомцев с несуществующим статусом, негативный сценарий")
    public void testFindPetsByStatusIfStatusNullNegative(){
        ResponseWrapper responseWrapper = petGrud.getPetByStatus(getRandomNotValidStatus());

        int statusCode = responseWrapper.getStatusCode();
        List<PetModel> response = responseWrapper.asList(PetModel[].class);

        assertSoftly(
                softAssertions -> {
                    softAssertions
                            .assertThat(statusCode)
                            .withFailMessage("Status code doesn't match")
                            .isEqualTo(STATUS_CODE_OK);
                    softAssertions
                            .assertThat(response)
                            .withFailMessage("Response body is not empty")
                            .isEmpty();
                }
        );
    }
}
