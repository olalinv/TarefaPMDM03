package com.robottitto.tarefapmdm03.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.robottitto.tarefapmdm03.R;
import com.robottitto.tarefapmdm03.api.core.AppSharedPreferences;
import com.robottitto.tarefapmdm03.api.order.OrderModelService;
import com.robottitto.tarefapmdm03.api.order.enums.Status;
import com.robottitto.tarefapmdm03.api.order.model.Order;
import com.robottitto.tarefapmdm03.ui.core.ActivityUtil;
import com.robottitto.tarefapmdm03.ui.order.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class AcceptedOrdersActivity extends AppCompatActivity {

    private final static String TAG = "AcceptedOrdersActivity";
    private Context context;
    private OrderModelService orderModelService;
    private List<Order> orders = new ArrayList<>();

    private RecyclerView rvOrders;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_orders);
        context = getApplicationContext();

        // View
        rvOrders = findViewById(R.id.rvOrders);
        rvOrders.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvOrders.setLayoutManager(layoutManager);

        // Initialization
        orderModelService = OrderModelService.get(this);
        try {
            orders = orderModelService.findOrdersByStatus(Status.ACCEPTED.getStatus());
            // Adapter
            mAdapter = new OrderAdapter(orders);
            rvOrders.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
            ActivityUtil.showToast(context, getString(R.string.error) + ": " + e.getMessage());
        }

    }

}
