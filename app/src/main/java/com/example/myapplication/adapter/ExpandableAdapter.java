package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.model.MenuModel;
import com.example.myapplication.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<MenuModel> listDataHeader;
    private HashMap<MenuModel, List<MenuModel>> listDataChild;

    public ExpandableAdapter(Context context, List<MenuModel> listDataHeader, HashMap<MenuModel, List<MenuModel>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null) {
            return 0;

        } else {
            return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
        }
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public MenuModel getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ImageView img_header;

        String headertitle = getGroup(groupPosition).menuName;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_header, null);
        }
        TextView lbllistheader = convertView.findViewById(R.id.lbllistheader);
        img_header = (ImageView) convertView.findViewById(R.id.img_header);
        lbllistheader.setTypeface(null, Typeface.BOLD);
        lbllistheader.setText(headertitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ImageView img_child;

        final String child_title = getChild(groupPosition, childPosition).menuName;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_child, null);
        }
        TextView txt_listchild = convertView.findViewById(R.id.lbllistitem);
        img_child = (ImageView) convertView.findViewById(R.id.img_child);
        txt_listchild.setTypeface(null, Typeface.BOLD);
        txt_listchild.setText(child_title);

        if (groupPosition == 1) {
            if (childPosition == 0) {
                img_child.setImageResource(R.drawable.add);
            } else if (childPosition == 1) {
                img_child.setImageResource(R.drawable.add);
            }else if (childPosition==2){
                img_child.setImageResource(R.drawable.add);
            }else if (childPosition==3){
                img_child.setImageResource(R.drawable.add);
            }
        } else if (groupPosition == 2) {
            img_child.setImageResource(R.drawable.add);
        } else if (groupPosition == 3) {
            img_child.setImageResource(R.drawable.add);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
