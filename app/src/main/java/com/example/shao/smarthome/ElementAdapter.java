package com.example.shao.smarthome;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shao on 2018/3/25.
 */

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ViewHolder>{

    private List<Element>mElementList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView eleImage;
        TextView eleName;
        public ViewHolder(View view){
            super(view);
            eleImage=(ImageView)view.findViewById(R.id.ele_image);
            eleName=(TextView)view.findViewById(R.id.ele_name);
        }
    }

    public ElementAdapter(List<Element>elementList){
        mElementList=elementList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.element_item,
                parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public  void onBindViewHolder(ViewHolder holder,int position){
        Element element=mElementList.get(position);
        holder.eleImage.setImageResource(element.getImageId());
        holder.eleName.setText(element.getName());
    }

    @Override
    public int getItemCount(){
        return mElementList.size();
    }

}
