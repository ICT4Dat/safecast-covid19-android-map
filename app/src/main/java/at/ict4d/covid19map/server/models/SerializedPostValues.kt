package at.ict4d.covid19map.server.models

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime

const val SERIALIZED_POST_VALUES_AGE_1 = "32aa3169-3e67-4087-b77f-ddf29ef1b840"
const val SERIALIZED_POST_VALUES_AGE_2 = "3386fae9-6a7c-42ef-8bd0-938816892677"
const val SERIALIZED_POST_VALUES_AGE_3 = "f44f4246-e069-4269-9b74-d9b85143f942"
const val SERIALIZED_POST_VALUES_AGE_4 = "07f2ca22-85aa-482e-ad1e-e6e3f5bfa39a"

const val SERIALIZED_POST_VALUES_DATE_OF_TEST_1 = "8ec985f0-b0be-4df8-8601-5bdcd81dcda1"
const val SERIALIZED_POST_VALUES_DATE_OF_TEST_2 = "605cc707-d69b-4a0d-8ec2-011c74210ed7"
const val SERIALIZED_POST_VALUES_DATE_OF_TEST_3 = "eb0f1516-2bb2-4dc2-b10a-130832f2c2f9"
const val SERIALIZED_POST_VALUES_DATE_OF_TEST_4 = "b5b949da-63e1-4f3a-8486-27114a1d5c30"

const val SERIALIZED_POST_VALUES_POSITION_1 = "c1b8ce55-55de-4ada-88b6-4e056625e956"
const val SERIALIZED_POST_VALUES_POSITION_2 = "c65ef80a-6cbd-43fb-9eba-278d205e99d6"
const val SERIALIZED_POST_VALUES_POSITION_3 = "1415e2e6-ad0f-410d-831a-b40f31ae010f"
const val SERIALIZED_POST_VALUES_POSITION_4 = "ca38b40f-66dc-4acc-9679-49cf6493d6c4"

const val SERIALIZED_POST_VALUES_MEASURES_TAKEN_1 = "b08062ba-c3ca-4811-8102-12257415072a"
const val SERIALIZED_POST_VALUES_MEASURES_TAKEN_2 = "0ab5adcd-a60e-4683-a226-f4141f9c512c"

data class SerializedPostValues(

    @SerializedName(
        value = SERIALIZED_POST_VALUES_AGE_1,
        alternate = [
            SERIALIZED_POST_VALUES_AGE_2,
            SERIALIZED_POST_VALUES_AGE_3,
            SERIALIZED_POST_VALUES_AGE_4
        ]
    )
    val age: List<Int>?,

    @SerializedName(
        value = SERIALIZED_POST_VALUES_DATE_OF_TEST_1,
        alternate = [
            SERIALIZED_POST_VALUES_DATE_OF_TEST_2,
            SERIALIZED_POST_VALUES_DATE_OF_TEST_3,
            SERIALIZED_POST_VALUES_DATE_OF_TEST_4
        ]
    )
    val dateOfTest: List<LocalDateTime>,

    @SerializedName(
        value = SERIALIZED_POST_VALUES_POSITION_1,
        alternate = [
            SERIALIZED_POST_VALUES_POSITION_2,
            SERIALIZED_POST_VALUES_POSITION_3,
            SERIALIZED_POST_VALUES_POSITION_4
        ]
    )
    val position: List<SerializedPostPosition>,

    @SerializedName(
        value = SERIALIZED_POST_VALUES_MEASURES_TAKEN_1,
        alternate = [
            SERIALIZED_POST_VALUES_MEASURES_TAKEN_2
        ]
    )
    val measuresTaken: List<String>?
)
