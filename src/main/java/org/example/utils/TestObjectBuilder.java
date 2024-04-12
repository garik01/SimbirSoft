package org.example.utils;

import org.example.dto.OrderModel;
import org.example.dto.PetModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.example.utils.TestDataHelper.*;

public class TestObjectBuilder {

    /**
     * Метод для формирования тела запроса создания питомца
     *
     * @param id идентификатор питомца
     * @return тело запроса
     */
    public static PetModel getAddNewPetModel(String id) {
//        return PetModel.builder()
//                .id(id)
//                .category(
//                        PetModel.CategoryAndTagsItem.builder()
//                                .id(VALID_CATEGORY_ID)
//                                .name(getRandomCategoryName())
//                                .build()
//                )
//                .name(getRandomPetName())
//                .photoUrls(
//                        List.of(
//                                getRandomUrl()
//                        )
//                )
//                .tags(
//                        List.of(
//                                PetModel.CategoryAndTagsItem.builder()
//                                        .id(VALID_TAG_ID)
//                                        .name(VALID_TAG_NAME)
//                                        .build()
//                        )
//                )
//                .status(VALID_PET_STATUS)
//                .build();

        return new PetModel
                (
                        id, new PetModel.CategoryAndTagsItem(
                        VALID_CATEGORY_ID,
                        getRandomCategoryName()
                ),
                        getRandomPetName(),
                        List.of(
                                getRandomUrl()
                        ),
                        List.of(
                                new PetModel.CategoryAndTagsItem(
                                        VALID_TAG_ID,
                                        VALID_TAG_NAME
                                )
                        ),
                        VALID_PET_STATUS
                );
    }

    /**
     * Метод для формирования тела запроса создания заявки на продажу домашнего питомца
     *
     * @param id идентификатор заявки
     * @param petId идентификатор домашнего питомца
     * @param quantity количество
     * @return тело запроса
     */
    public static OrderModel getAddNewOrderModel(String id, String petId, int quantity) {
        Date dateTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(dateTime);
        return new OrderModel
                (
                        id,
                        petId,
                        quantity,
                        formattedDate,
                        VALID_ORDER_STATUS,
                        VALID_COMPLETE_STATUS
                );
    }
}
