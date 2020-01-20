package com.robottitto.tarefapmdm03.ui.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robottitto.tarefapmdm03.R;
import com.robottitto.tarefapmdm03.api.core.AppSharedPreferences;
import com.robottitto.tarefapmdm03.api.user.UserModelService;
import com.robottitto.tarefapmdm03.api.user.enums.Role;
import com.robottitto.tarefapmdm03.api.user.model.User;
import com.robottitto.tarefapmdm03.ui.core.ActivityUtil;
import com.robottitto.tarefapmdm03.ui.order.AcceptedOrdersActivity;
import com.robottitto.tarefapmdm03.ui.order.MakeOrderActivity;
import com.robottitto.tarefapmdm03.ui.order.OrdersInProcessActivity;
import com.robottitto.tarefapmdm03.ui.order.PendingOrdersActivity;
import com.robottitto.tarefapmdm03.ui.order.PurchasesMadeActivity;
import com.robottitto.tarefapmdm03.ui.order.RejectedOrdersActivity;

public class ProfileActivity extends AppCompatActivity {

    private final static String TAG = "ProfileActivity";
    private Context context;
    private User user;
    private UserModelService userModelService;

    private LinearLayout clientLayout;
    private TextView tvName;
    private Button btMakeOrder;
    private Button btSeeOrders;
    private Button btSeePurchases;
    private Button btLogout;

    private LinearLayout adminLayout;
    private Button btSeePendingOrders;
    private Button btSeeAcceptedOrders;
    private Button btSeeRejectedOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        context = getApplicationContext();

        // View elements
        // Client
        clientLayout = findViewById(R.id.clientLayout);
        tvName = findViewById(R.id.tvName);
        btMakeOrder = findViewById(R.id.btMakeOrder);
        btSeeOrders = findViewById(R.id.btSeeOrders);
        btSeePurchases = findViewById(R.id.btSeePurchases);
        btLogout = findViewById(R.id.btLogout);
        // Admin
        adminLayout = findViewById(R.id.adminLayout);
        btSeePendingOrders = findViewById(R.id.btSeePendingOrders);
        btSeeAcceptedOrders = findViewById(R.id.btSeeAcceptedOrders);
        btSeeRejectedOrders = findViewById(R.id.btSeeRejectedOrders);

        // Initialization
        userModelService = UserModelService.get(this);
        try {
            user = userModelService.findUserById(getUserDetails("UID"));
            if (null != user) {
                tvName.setText(user.getName().concat(" ").concat(user.getSurname()));
                if (user.getRole() == Role.ADMINISTRADOR.getRole()) {
                    clientLayout.setVisibility(View.INVISIBLE);
                    adminLayout.setVisibility(View.VISIBLE);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            ActivityUtil.showToast(context, getString(R.string.error) + ": " + e.getMessage());
        }

        // Events
        //Client
        btMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.go(context, MakeOrderActivity.class);
            }
        });

        btSeeOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.go(context, OrdersInProcessActivity.class);
            }
        });

        btSeePurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.go(context, PurchasesMadeActivity.class);
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.go(context, LoginActivity.class);
            }
        });

        // Admin
        btSeePendingOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.go(context, PendingOrdersActivity.class);
            }
        });

        btSeeAcceptedOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.go(context, AcceptedOrdersActivity.class);
            }
        });

        btSeeRejectedOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.go(context, RejectedOrdersActivity.class);
            }
        });

    }

    private int getUserDetails(String key) {
        SharedPreferences sharedPref = getSharedPreferences(AppSharedPreferences.PREFS_NAME, Context.MODE_PRIVATE);
        int uid = sharedPref.getInt(key, 0);
        return uid;
    }
}
