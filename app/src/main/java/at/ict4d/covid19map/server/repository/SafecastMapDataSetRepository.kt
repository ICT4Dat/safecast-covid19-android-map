package at.ict4d.covid19map.server.repository

import at.ict4d.covid19map.models.SafecastMapDataSet
import at.ict4d.covid19map.server.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.threeten.bp.ZonedDateTime

class SafecastMapDataSetRepository {

    // TODO: delete and make real API call
    private val dummyList = listOf<SafecastMapDataSet>(
        SafecastMapDataSet(
            77,
            "https://covid19map.safecast.org/posts/77",
            "#717171",
            "Called NHS 111 hotline",
            "Very obvious COVID-19 symptoms: temperature went up to 38.8 degrees, dry persistent cough, very tight chest. Wife had similar chest tightness and coughing and also lost her sense of smell and taste. Calling NHS 111 they said we should self isolate at home as only very severe cases (gasping, not being able to get words out, choking or lips turning blue) should go into the hospital. And the only testing available is for people in the hospital for these reasons. After 5-6 days the symptoms ended apart from feeling weak and tired.",
            "slug 1",
            "en_en",
            51.52,
            -0.16,
            ZonedDateTime.now().minusDays(1),
            ZonedDateTime.now().minusDays(3),
            42
        ),
        SafecastMapDataSet(
            76,
            "https://covid19map.safecast.org/posts/76",
            "#717171",
            "Clinic",
            "Immuno-compromised adult w/ elevated-risk family (asthma). Sick for 2 weeks with symptoms that come and go. Low fever, aches, runny nose, congestion, cough, first ever trouble with athsma-like symptoms (used spouse's Albuterol inhaler and it resolved quickly). UCLA approved testing on 3/20. Waited 3 days to get test appointment. Tested on 3/25. Results negative on 3/27.",
            "slug 2",
            "en_en",
            34.03,
            -118.48,
            ZonedDateTime.now().minusDays(2),
            ZonedDateTime.now().minusDays(5),
            39
        )
    )

    fun getSafecastMapDataSets(): Flow<Resource<List<SafecastMapDataSet>>> = flow {

        emit(Resource.loading(dummyList))

        delay(2000)

        val newData = dummyList.toMutableList()
        newData.add(
            SafecastMapDataSet(
                75,
                "https://covid19map.safecast.org/posts/75",
                "#717171",
                "Northshore Immediate Care Center",
                "Called the Northshore Covid-19 hotline and screened over the phone by a nurse who said I was at moderate risk of being infected given my symptoms and potential exposure 11 days prior. Was advised to visit the Northshore Immediate Care facility in Gurnee where I would be screened again. I checked in to the clinic and waited for a call from the triage nurse. When they rang they asked similar questions and determined I was at moderate risk which did not qualify for a test. They informed me that I probably have the disease and should continue to self-quarantine until I am completely symptom free. They also said even if I did get a test that due to my limited symptoms (persistent cough, no fever, shortness of breath) that I might get a false negative as 20% of the tests they are running are producing false negatives. The nurse also said if my symptoms get worse to go to the hospital immediately.",
                "slug 2",
                "en_en",
                42.38,
                -88.0,
                ZonedDateTime.now().minusDays(4),
                ZonedDateTime.now().minusDays(10),
                38
            )
        )

        emit(Resource.success(newData, 200))
    }
}