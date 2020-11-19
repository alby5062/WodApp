package it.alberto.wodapp.test_wod.data

import android.content.res.Resources
import it.alberto.wodapp.R

fun wodList(resources: Resources): List<Wod> {
    return listOf(
        Wod(
            id = 1,
            name = resources.getString(R.string.wod1_name),
            type = resources.getString(R.string.wod1_type),
            description = resources.getString(R.string.wod1_description)
        ),
        Wod(
            id = 2,
            name = resources.getString(R.string.wod2_name),
            type = resources.getString(R.string.wod2_type),
            description = resources.getString(R.string.wod2_description)
        ),
        Wod(
            id = 3,
            name = resources.getString(R.string.wod3_name),
            type = resources.getString(R.string.wod3_type),
            description = resources.getString(R.string.wod3_description)
        ),
        Wod(
            id = 4,
            name = resources.getString(R.string.wod4_name),
            type = resources.getString(R.string.wod4_type),
            description = resources.getString(R.string.wod4_description)
        ),
        Wod(
            id = 5,
            name = resources.getString(R.string.wod5_name),
            type = resources.getString(R.string.wod5_type),
            description = resources.getString(R.string.wod5_description)
        ),
        Wod(
            id = 6,
            name = resources.getString(R.string.wod6_name),
            type = resources.getString(R.string.wod6_type),
            description = resources.getString(R.string.wod7_description)
        ),
        Wod(
            id = 7,
            name = resources.getString(R.string.wod7_name),
            type = resources.getString(R.string.wod7_type),
            description = resources.getString(R.string.wod7_description)
        ),
    )
}
