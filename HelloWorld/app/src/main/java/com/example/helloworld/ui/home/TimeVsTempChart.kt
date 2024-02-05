package com.example.helloworld.ui.home

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.R
import com.example.helloworld.TankViewModel
import com.example.helloworld.TimeTemperaturePair
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.Locale

class TimeVsTempChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LineChart(context, attrs, defStyleAttr) {
    inner class MyXAxisFormatter : ValueFormatter() {
        private val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())

        override fun getFormattedValue(value: Float): String {
            return sdf.format(Date(value.toLong() * 1000L))
        }
    }

    init {
        setupChart()
    }

    private fun setupChart() {
        apply {
            // Enable touch gestures
            setTouchEnabled(true)

            // Enable scaling and dragging
            isDragEnabled = true
            setScaleEnabled(true)
            setDrawGridBackground(false)

            // Enable pinch zoom to avoid scaling x and y axis separately
            setPinchZoom(true)

            // Set an alternative background color
            setBackgroundColor(Color.LTGRAY)
            description.isEnabled = false

            // X-axis settings
            xAxis.apply {
                enableGridDashedLine(10f, 10f, 0f)
                position = XAxis.XAxisPosition.BOTTOM
                valueFormatter =
                    MyXAxisFormatter() // Custom formatter to convert timestamps to readable dates
                setAvoidFirstLastClipping(true)
            }

            // Y-axis settings
            axisRight.isEnabled = false // Disable right Y-axis
            axisLeft.enableGridDashedLine(10f, 10f, 0f)
            axisLeft.setDrawZeroLine(false)

            // Add an empty data object
            data = LineData()
            data.setValueTextColor(Color.WHITE)
        }
    }

    fun addEntry(point: TimeTemperaturePair) {
        addEntries(listOf(point))
    }

    fun addEntries(pairs: List<TimeTemperaturePair>?) {
        if (data == null || pairs == null) return;
        var set = data.getDataSetByIndex(0)
        // Create a set if none exists
        if (set == null) {
            set = createSet()
            data.addDataSet(set)
        }
        for (timeTemperaturePair in pairs) {
            // Assuming your TimeTemperaturePair.time is a LocalDateTime object, convert it to a suitable format (e.g., timestamp in seconds)
            val timestamp =
                timeTemperaturePair.datetime.atZone(ZoneId.systemDefault()).toEpochSecond()
            data.addEntry(
                Entry(timestamp.toFloat(), timeTemperaturePair.temperature.toFloat()),
                0
            )
        }
        data.notifyDataChanged()

        // Let the chart know it's data has changed
        notifyDataSetChanged()

        // Limit the number of visible entries
        setVisibleXRangeMaximum(120f) // Adjust this value as needed

        // Move to the latest entry
        moveViewToX(data.entryCount.toFloat())
    }

    private fun createSet(): LineDataSet {
        return LineDataSet(null, "Dynamic Data").apply {
            axisDependency = YAxis.AxisDependency.LEFT

        }
    }
}