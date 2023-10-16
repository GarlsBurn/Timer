package com.example.timerbet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.timerbet.databinding.ActivityMainBinding
import android.os.CountDownTimer
import android.os.Parcel
import android.os.Parcelable
import java.sql.Time
import kotlin.concurrent.timer

class MainActivity() : AppCompatActivity(), Parcelable {
    private val initialTime: String = "00.00.00"
    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.Start.setOnClickListener { val timeInMinutes = binding.entreTime.text.toString().toIntOrNull()
            var shouldStop = false
            if (timeInMinutes != null && timeInMinutes > 0 ){
                val timeMillis: Int = timeInMinutes * 60 * 1000
                StartB(timeMillis)
            }
            else {
                Toast.makeText(this, "Пожалуйста проаерьте правильность введеного числа в минутах" , Toast.LENGTH_SHORT).show()
            }

        }

        binding.Reset.setOnClickListener {
            binding.entreTime.text.clear()
            timer?.cancel()
            binding.Time.text = initialTime



            }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    private fun StartB(duration: Int) {
        timer?.cancel()
        timer = object : CountDownTimer(duration.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hours = (millisUntilFinished / 3600000).toInt()
                val minutes = ((millisUntilFinished % 3600000) / 60000).toInt()
                val seconds = ((millisUntilFinished % 60000) / 1000).toInt()
                val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                binding.Time.text = timeString
            }

            override fun onFinish() {
                binding.Time.text = "Таймер завершен"

            }
        }.start()
    }
}


