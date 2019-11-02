package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class LiniendiagrammFrau extends AppCompatActivity {

    LineChartView lineChartView;
    DatabaseHelperFrau mDatabaseHelperFrau;
    ArrayList<Integer> listData;
    int[] yAxisData;
    String[] xAxisData;
    int i = 0;
    int topWert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liniendiagramm_frau);


        mDatabaseHelperFrau = new DatabaseHelperFrau(this);
        kfaInGraph();



        lineChartView = findViewById(R.id.chart);

        topWert = mDatabaseHelperFrau.getMAXkfa();
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

        for (int i = 0; i < xAxisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(xAxisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Kröperfettanteil in %");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(22);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = topWert+5;
        viewport.bottom = 0;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
    }

    private void kfaInGraph() {
        Cursor data = mDatabaseHelperFrau.getData();
        int rows = mDatabaseHelperFrau.getRowsCount();
        yAxisData = new int[rows];
        xAxisData = new String[rows];

        while (data.moveToNext()){
            yAxisData[i] = data.getInt(5);
            xAxisData[i] = (""+(i+1));
            i++;
        }
    }
}
