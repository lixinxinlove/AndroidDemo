package com.lee.androiddemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.lee.androiddemo.R;

import java.util.ArrayList;
import java.util.List;

public class MPAndroidChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {


    private PieChart mChart;

    private HorizontalBarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);
        mChart = (PieChart) findViewById(R.id.pie_chart);

        initBarChart();
        initView();
    }

    private void initBarChart() {

        mBarChart = (HorizontalBarChart) findViewById(R.id.bar_chart);

        XAxis xl = mBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setLabelRotationAngle(0f);


        //  xl.setTypeface(mTfLight);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(true);
        xl.setGranularity(10f);
        xl.setDrawLabels(false);
        // xl.setAxisLineWidth(0);
        //  xl.setAxisLineColor(0xffffffff);

        //xl.setCenterAxisLabels(false);
        //  xl.setLabelCount(0);

        mBarChart.setDescription(null);


        YAxis yl = mBarChart.getAxisLeft();
        //     yl.setTypeface(mTfLight);
        yl.setDrawAxisLine(false);
        yl.setDrawGridLines(false);
        yl.setDrawLabels(false);
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yl.setInverted(true);
        yl.setDrawTopYLabelEntry(false);

        YAxis yr = mBarChart.getAxisRight();
        //   yr.setTypeface(mTfLight);
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        yr.setInverted(true);

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, 1f, "男"));
        barEntries.add(new BarEntry(2f, 2f, "女"));
        barEntries.add(new BarEntry(3f, 3f, "X"));

        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setStackLabels(new String[]{"男", "女", "X"});

        BarData barData = new BarData();
        barData.setBarWidth(0.35f);

        List<Integer> color = new ArrayList<>();
        color.add(0xffff0000);
        color.add(0xffff00ff);
        color.add(0xffffff00);

        // barData.setValueTextColors(color);

        barDataSet.setValueTextColors(color);

        barDataSet.setColors(color);

        barData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (String) entry.getData();
            }
        });

        barDataSet.setValueFormatter(new IValueFormatter() {     //格式化数据
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (String) entry.getData();
            }
        });

        barDataSet.setValueTextSize(16f);
        barData.addDataSet(barDataSet);
        mBarChart.setData(barData);
    }

    private void initView() {
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        //  mChart.setCenterTextTypeface(mTfLight);
        //  mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);


        setData(4, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);


        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        //  mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
    }

    ArrayList<PieEntry> entries;

    private void setData(int count, float range) {

        float mult = range;

        entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), i + "lee" + i));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //  data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.e("LEE", e.getX() + "");
        Log.e("LEE", e.getY() + "");
//        Log.e("LEE", e.getData().toString() + "");

        h.getDataIndex();

        PieEntry pieEntry = (PieEntry) e;

        Log.e("LEE", pieEntry.getValue() + "");
        Log.e("LEE", pieEntry.getLabel() + "");


    }

    @Override
    public void onNothingSelected() {

    }
}
