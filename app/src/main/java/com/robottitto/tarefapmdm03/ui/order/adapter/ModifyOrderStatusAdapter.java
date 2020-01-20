package com.robottitto.tarefapmdm03.ui.order.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.robottitto.tarefapmdm03.R;
import com.robottitto.tarefapmdm03.api.order.OrderModelService;
import com.robottitto.tarefapmdm03.api.order.enums.Status;
import com.robottitto.tarefapmdm03.api.order.model.Order;

import java.util.List;

public class ModifyOrderStatusAdapter extends RecyclerView.Adapter<ModifyOrderStatusAdapter.MyViewHolder> {

    private List<Order> mDataset;
    private OrderModelService orderModelService;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView tvId;
        public TextView tvProduct;
        public TextView tvCategory;
        public TextView tvQuantity;
        public Button btAcceptOrder;
        public Button btRejectOrder;

        public MyViewHolder(View v) {
            super(v);
            tvId = v.findViewById(R.id.tvId);
            tvProduct = v.findViewById(R.id.tvProduct);
            tvCategory = v.findViewById(R.id.tvCategory);
            tvQuantity = v.findViewById(R.id.tvQuantity);
            btAcceptOrder = v.findViewById(R.id.btAcceptOrder);
            btRejectOrder = v.findViewById(R.id.btRejectOrder);

            btAcceptOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Order selectedOrder = (Order) v.getTag();
                    orderModelService.updateOrderStatus(selectedOrder.getOrderId(), Status.ACCEPTED.getStatus());
                }
            });

            btRejectOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Order selectedOrder = (Order) v.getTag();
                    orderModelService.updateOrderStatus(selectedOrder.getOrderId(), Status.REJECTED.getStatus());
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ModifyOrderStatusAdapter(List<Order> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ModifyOrderStatusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        orderModelService.get(parent.getContext());
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_order, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.setTag(mDataset.get(position));
        holder.tvId.setText(String.valueOf(mDataset.get(position).getOrderId()));
        holder.tvProduct.setText(mDataset.get(position).getProduct());
        holder.tvCategory.setText(mDataset.get(position).getCategory());
        holder.tvQuantity.setText(String.valueOf(mDataset.get(position).getQuantity()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
