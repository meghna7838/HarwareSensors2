package meghna.c.harwaresensors

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensors = sm.getSensorList(Sensor.TYPE_ALL)

        sensors.forEach {

            Log.i("Sensors",""''${it.name},${it.vendor}""".trimIndent())

            val acceSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val sensorListener = object : SensorEventListener {
                override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                }

                override fun onSensorChanged(p0: SensorEvent?) {
                    Log.i("SENSORS","""
                    values:
                    ax: ${sensors.values[0]}
                      ay:${sensors.values[1]}
                      az:${sensors.values[2]}
                    """.trimIndent()
                    )

                    val ax = p0!!.values[0]
                    val ay = p0!!.values[1]
                    val az = p0!!.values[2]
                    back.setBackgroundColor(
                        Color.rgb(
                            (((ax + 12) / 24) * 255).toInt(),
                            (((ay + 12) / 24) * 255).toInt(),
                            (((az + 12) / 24) * 255).toInt()
                        )
                    )
                }


            }
            sm.registerListener(sensorListener,acceSensor,1000*60)
        }
    }
}