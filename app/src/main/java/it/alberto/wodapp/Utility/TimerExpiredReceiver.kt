package it.alberto.wodapp.Utility

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import it.alberto.wodapp.Utility.util.NotificationUtil
import it.alberto.wodapp.Utility.util.PrefUtil


class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(TimerActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}



