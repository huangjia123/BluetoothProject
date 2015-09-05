package com.future.bluetoothnamesystem.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.future.bluetoothnamesystem.R;


/**
 * 自定义title栏
 * 可以自定义title栏的标题文字
 * 并且该标题栏自带返回功能
 * Created by baiju on 2015/8/21.
 */
public class FutureTitleBar extends RelativeLayout{

    private  static final String NAMESPACE="http://schemas.android.com/apk/res-auto";

    //private ImageButton ibBack;
    private TextView tvTitle;
    private String mTitle;

    /**
     * 有属性的时候走这个方法
     * @param context 上下文,
     * @param attrs 布局文件中的属性集合
     */
    public FutureTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性，activity_title 的值
        mTitle=attrs.getAttributeValue(NAMESPACE,"activity_title");

        initView();
    }

    public FutureTitleBar(Context context) {
        super(context);
        initView();
    }

    public FutureTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(){
        View view =View.inflate(getContext(), R.layout.my_title_view,this);
        //ibBack= (ImageButton) view.findViewById(R.id.ib_back);
        tvTitle= (TextView) view.findViewById(R.id.tv_title);

        setTitle(mTitle);//设置标题
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }



}
